package az.doshabcatering.doshabcatering.servise;

import az.doshabcatering.doshabcatering.randomOtp.GenerateRandomOtp;
import az.doshabcatering.doshabcatering.dto.JwtResponse;
import az.doshabcatering.doshabcatering.dto.RequestDto;
import az.doshabcatering.doshabcatering.dto.RequestLogin;
import az.doshabcatering.doshabcatering.entity.UserEntity;
import az.doshabcatering.doshabcatering.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final MailService mailService;

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(email));
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity user = getUserByEmail(email);
        return new User(user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRoles().toString())));
    }

    public Page<UserEntity> listAllUsers(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        return userRepository.findAll(pageRequest);
    }


    @SneakyThrows
    public ResponseEntity<?> userRegistration(RequestDto requestDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(requestDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userEntity.setName(requestDto.getName());
        userEntity.setSurname(requestDto.getSurname());
        userEntity.setPhone_number(requestDto.getPhone_number());
        GenerateRandomOtp generateRandomOtp = new GenerateRandomOtp();
        String otp = generateRandomOtp.generateOTP();
        userEntity.setOtp(otp);
        userRepository.save(userEntity);

        mailService.sendMail(requestDto.getEmail(), otp, "istifadəçi qeydiyyatı");
        return ResponseEntity.ok().body("uğurlu qeydiyyat!");
    }

    public ResponseEntity<?> userVerification(String otp) {
        UserEntity userEntity = userRepository.findByOtp(otp).orElse(null);
        if (userEntity == null) {
            throw new UsernameNotFoundException(otp);
        }
        String otpCheck = userEntity.getOtp();
        if (otpCheck.equals(otp)) {
            userEntity.setVerified(true);
            userRepository.save(userEntity);

            String htmlResponse = "<html>" +
                    "<head>" +
                    "<meta http-equiv=\"refresh\" content=\"3;url=https://doshabcatering.az/\" />" +
                    "</head>" +
                    "<body>" +
                    "<h1>Hesabınız uğurla təsdiqləndi!</h1>" +
                    "<p>3 saniyə sonra ana səhifəyə yönləndiriləcəksiniz...</p>" +
                    "</body>" +
                    "</html>";

            return ResponseEntity.ok().body(htmlResponse);

        }
        return new ResponseEntity<>("istifadeci tapilmadi", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> login(RequestLogin requestLogin) {
        loadUserByUsername(requestLogin.getEmail());
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestLogin.getEmail(), requestLogin.getPassword()));

        if (authentication.isAuthenticated()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userToken = jwtService.generateToken(userDetails);
            return new ResponseEntity<>(new JwtResponse(userToken), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("nəsə düzgün getmədi!", HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<?> upDatePassword(String email) throws MessagingException {
        UserEntity user = getUserByEmail(email);
        GenerateRandomOtp generateRandomOtp = new GenerateRandomOtp();
        String otp = generateRandomOtp.generateOTP();
        user.setOtp(otp);
        mailService.sendMail(email,otp,"parolun yenilenmesi");
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
