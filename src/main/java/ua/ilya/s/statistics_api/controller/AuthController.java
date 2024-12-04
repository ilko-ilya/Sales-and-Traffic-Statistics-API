package ua.ilya.s.statistics_api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ilya.s.statistics_api.dto.user.UserLoginRequestDto;
import ua.ilya.s.statistics_api.dto.user.UserLoginResponseDto;
import ua.ilya.s.statistics_api.dto.user.UserRegistrationRequestDto;
import ua.ilya.s.statistics_api.dto.user.UserRegistrationResponseDto;
import ua.ilya.s.statistics_api.secutiry.AuthenticationService;
import ua.ilya.s.statistics_api.service.user.UserService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto requestDto) {
        return authenticationService.authenticate(requestDto);
    }

    @PostMapping("/register")
    public UserRegistrationResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto request
    ) {
        return userService.register(request);
    }

}
