package az.doshabcatering.doshabcatering.servise.appService;

import az.doshabcatering.doshabcatering.documents.Users;
import az.doshabcatering.doshabcatering.dto.response.DtoResponse;
import az.doshabcatering.doshabcatering.randomOtp.GenerateRandomOtp;
import az.doshabcatering.doshabcatering.dto.request.RequestDto;
import az.doshabcatering.doshabcatering.dto.request.RequestLogin;
import az.doshabcatering.doshabcatering.entity.UserEntity;
import az.doshabcatering.doshabcatering.repository.jpaRepo.UserRepository;
import az.doshabcatering.doshabcatering.security.jwt.JwtService;
import az.doshabcatering.doshabcatering.servise.elasticService.ElasticUsersService;
import az.doshabcatering.doshabcatering.servise.mail.MailService;
import az.doshabcatering.doshabcatering.utils.HtmlTemplates;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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

import java.time.Duration;
import java.util.*;


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
                () -> new UsernameNotFoundException("User not found with email: " + email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity user = getUserByEmail(email);
        return new User(user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRoles().toString())));
    }

    public Map<String, Object> getUserWithOrders(String email) {
        UserEntity userEntity = userRepository.findByIdWithOrders(email).orElse(null);

        if (userEntity != null) {
            Map<String, Object> details = new HashMap<>();
            DtoResponse dtoResponse = new DtoResponse();
            BeanUtils.copyProperties(userEntity, dtoResponse);
            details.put("userInfo", dtoResponse);
            details.put("orders", userEntity.getOrders());
            return details;
        } else {
            return null;
        }
    }

    @Transactional
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
        users.setId(userEntity.getId());
        elasticService.save(users);
        log.info("User with email {} has been created in elasticsearch", users.getEmail());

        try {
            mailService.sendMail(requestDto.getEmail(), "istifadəçi qeydiyyatı", HtmlTemplates.userVerification(otp));
        } catch (MessagingException ex) {
            log.error(ex.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
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
                userEntity.setOtp(null);
                userRepository.save(userEntity);
                return ResponseEntity.ok().body(HtmlTemplates.htmlResponse());

            }
            return new ResponseEntity<>("istifadəçi tapilmadi", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info("User with email {} has been not verified: {}", userEntity.getEmail(), e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<?> login(RequestLogin requestLogin, HttpServletResponse response) {
        loadUserByUsername(requestLogin.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestLogin.getEmail(), requestLogin.getPassword()));

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userToken = jwtService.generateToken(userDetails);
            log.info("User with email {} has been login", requestLogin.getEmail());
            UserEntity user = userRepository.findByEmail(requestLogin.getEmail()).orElse(null);
            DtoResponse dtoResponse = new DtoResponse();
            BeanUtils.copyProperties(user, dtoResponse);


            ResponseCookie cookie = ResponseCookie.from("token", userToken)
                    .httpOnly(true)
                    .secure(true)
                    .sameSite("none")
                    .domain(".doshabcatering.az")
                    .path("/")
                    .maxAge(Duration.ofHours(4))
                    .build();

            response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

            return ResponseEntity.status(200).body(dtoResponse);
        } else {
            return ResponseEntity.status(401).body("istidafəçi adı və ya şifrə yalnışdır!");
        }
    }


    public ResponseEntity<?> sendOtpForUpdatePassword(String email) {
        UserEntity user = getUserByEmail(email);
        GenerateRandomOtp generateRandomOtp = new GenerateRandomOtp();
        String otp = generateRandomOtp.generateOTP();
        user.setOtp(otp);
        userRepository.save(user);
        try {
            mailService.sendMail(email, "parolun yenilənməsi", HtmlTemplates.passwordUpdate(otp));
        } catch (MessagingException ex) {
            log.error(ex.getMessage());
        }
        log.info("User with email {} has been send otp for update password", email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> upDatePassword(String otp, String newPassword) {
        UserEntity user = userRepository.findByOtp(otp).orElse(null);

        if (user != null) {
            if (user.getOtp().equals(otp)) {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setOtp(null);
                userRepository.save(user);
                log.info("User with otp {} has been updated", otp);
                return new ResponseEntity<>("parolunuz uğurla yeniləndi!", HttpStatus.OK);
            }
        } else {
            throw new UsernameNotFoundException("user not found");
        }
        return new ResponseEntity<>("nəsə doğru olmadı birdə cəhd edin", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> sendOtp(String email) {

        UserEntity user = userRepository.findByEmail(email).orElse(null);
        if (user != null) {
            GenerateRandomOtp generateRandomOtp = new GenerateRandomOtp();
            String otp = generateRandomOtp.generateOTP();
            user.setOtp(otp);
            userRepository.save(user);

            try {
                mailService.sendMail(email, "email təsdiqi", HtmlTemplates.userVerification(otp));

            } catch (MessagingException ex) {
                log.error(ex.getMessage());
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
