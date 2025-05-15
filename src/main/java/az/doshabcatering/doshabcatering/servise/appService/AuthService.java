package az.doshabcatering.doshabcatering.servise.appService;

import az.doshabcatering.doshabcatering.documents.Users;
import az.doshabcatering.doshabcatering.dto.response.DtoResponse;
import az.doshabcatering.doshabcatering.randomOtp.GenerateRandomOtp;
import az.doshabcatering.doshabcatering.dto.request.RequestDto;
import az.doshabcatering.doshabcatering.dto.request.RequestLogin;
import az.doshabcatering.doshabcatering.entity.UserEntity;
import az.doshabcatering.doshabcatering.repository.jpaRepo.UserRepository;
import az.doshabcatering.doshabcatering.servise.elasticService.ElasticUsersService;
import az.doshabcatering.doshabcatering.servise.mail.MailService;
import az.doshabcatering.doshabcatering.utils.HtmlTemplates;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final MailService mailService;
    private final ElasticUsersService elasticService;

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity user = getUserByEmail(email);
        return new User(user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRoles().toString())));
    }

    @Transactional
    @SneakyThrows
    public ResponseEntity<?> userRegistration(RequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            log.info("User with email {} already exists", requestDto.getEmail());
            return new ResponseEntity<>(requestDto.getEmail() + " email ilə atrıq qeydiyyatdan keçilib!", HttpStatus.CONFLICT);
        }
        UserEntity userEntity = new UserEntity();
        Users users = new Users();
        BeanUtils.copyProperties(requestDto, userEntity);
        BeanUtils.copyProperties(requestDto, users);

        userEntity.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        GenerateRandomOtp generateRandomOtp = new GenerateRandomOtp();
        String otp = generateRandomOtp.generateOTP();
        userEntity.setOtp(otp);
        userRepository.save(userEntity);
        log.info("User with email {} has been created in db", requestDto.getEmail());
        elasticService.save(users);
        log.info("User with email {} has been created in elasticsearch", users.getEmail());

        mailService.sendMail(requestDto.getEmail(), "istifadəçi qeydiyyatı", HtmlTemplates.userVerification(otp));
        return ResponseEntity.ok().body("uğurlu qeydiyyat!");
    }

    public ResponseEntity<?> userVerification(String otp) {
        UserEntity userEntity = userRepository.findByOtp(otp).orElse(null);
        try {
            if (userEntity == null) {
                throw new UsernameNotFoundException("invalid otp");
            }
            String otpCheck = userEntity.getOtp();
            if (otpCheck.equals(otp)) {
                userEntity.setVerified(true);
                userRepository.save(userEntity);
                log.info("User with otp {} has been verified", otp);

                return ResponseEntity.ok().body(HtmlTemplates.htmlResponse());

            }
            return new ResponseEntity<>("istifadəçi tapilmadi", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info("User with email {} has been not verified: {}", userEntity.getEmail(), e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> login(RequestLogin requestLogin) {
        loadUserByUsername(requestLogin.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestLogin.getEmail(), requestLogin.getPassword()));

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userToken = jwtService.generateToken(userDetails);
            log.info("User with email {} has been login", requestLogin.getEmail());
            UserEntity user = userRepository.findByEmail(requestLogin.getEmail()).orElse(null);
            DtoResponse dtoResponse = new DtoResponse();
            BeanUtils.copyProperties(user, dtoResponse);
            Map<String, Object> claims = new HashMap<>();
            claims.put("token", userToken);
            claims.put("userInfo", dtoResponse);
            return new ResponseEntity<>(claims, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("nəsə düzgün getmədi!", HttpStatus.UNAUTHORIZED);
        }
    }

    @Transactional
    @SneakyThrows
    public ResponseEntity<?> sendOtpForUpdatePassword(String email) {
        UserEntity user = getUserByEmail(email);
        GenerateRandomOtp generateRandomOtp = new GenerateRandomOtp();
        String otp = generateRandomOtp.generateOTP();
        user.setOtp(otp);
        userRepository.save(user);
        mailService.sendMail(email, "parolun yenilənməsi", HtmlTemplates.passwordUpdate(otp));
        log.info("User with email {} has been send otp for update password", email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> upDatePassword(String otp, String newPassword) {
        UserEntity user = userRepository.findByOtp(otp).orElse(null);

        if (user.getOtp().equals(otp)) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            log.info("User with otp {} has been updated", otp);
            return new ResponseEntity<>("parolunuz uğurla yeniləndi!", HttpStatus.OK);
        }
        return new ResponseEntity<>("nəsə doğru olmadı birdə cəhd edin", HttpStatus.BAD_REQUEST);
    }

}
