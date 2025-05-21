package az.doshabcatering.doshabcatering.controller;

import az.doshabcatering.doshabcatering.dto.request.PasswordRequestDto;
import az.doshabcatering.doshabcatering.dto.request.RequestDto;
import az.doshabcatering.doshabcatering.dto.request.RequestLogin;
import az.doshabcatering.doshabcatering.servise.appService.AuthService;
import jakarta.servlet.http.HttpServletResponse;
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


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid RequestLogin requestLogin, HttpServletResponse httpServletResponse) {
        return authService.login(requestLogin, httpServletResponse);
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