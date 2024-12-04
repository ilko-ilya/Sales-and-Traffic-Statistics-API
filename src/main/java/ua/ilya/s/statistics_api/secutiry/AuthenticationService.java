package ua.ilya.s.statistics_api.secutiry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ua.ilya.s.statistics_api.dto.user.UserLoginRequestDto;
import ua.ilya.s.statistics_api.dto.user.UserLoginResponseDto;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public UserLoginResponseDto authenticate(UserLoginRequestDto requestDto) {
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDto.getUsername(),
                            requestDto.getPassword()));
            log.info("User {} authenticated successfully", requestDto.getUsername());
            String token = jwtUtil.generateToken(authentication.getName());
            return new UserLoginResponseDto(token);
        } catch (BadCredentialsException e) {
            log.error("Authentication failed for user {}", requestDto.getUsername());
            throw new BadCredentialsException("Invalid username or password");
        }
    }
}
