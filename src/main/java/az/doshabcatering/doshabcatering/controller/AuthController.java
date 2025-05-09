package az.doshabcatering.doshabcatering.controller;

import az.doshabcatering.doshabcatering.dto.PasswordRequestDto;
import az.doshabcatering.doshabcatering.dto.RequestDto;
import az.doshabcatering.doshabcatering.dto.RequestLogin;
import az.doshabcatering.doshabcatering.entity.UserEntity;
import az.doshabcatering.doshabcatering.servise.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RequestDto requestDto) {
        return authService.userRegistration(requestDto);
    }

    @GetMapping("/{email}")
    public UserEntity getUserByEmail(@PathVariable String email) {
        return authService.getUserByEmail(email);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid RequestLogin requestLogin) {
        return authService.login(requestLogin);
    }

    @GetMapping("/verify/{otp}")
    public ResponseEntity<?> verify(@PathVariable String otp) {
        return authService.userVerification(otp);
    }

    @GetMapping("/send-otp/{email}")
    public ResponseEntity<?> updateUser(@PathVariable String email) {
        return authService.sendOtpForUpdatePassword(email);
    }

    @PostMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestBody @Valid PasswordRequestDto passwordRequestDto) {
        return authService.upDatePassword(passwordRequestDto.getOtp(), passwordRequestDto.getPassword());
    }

}