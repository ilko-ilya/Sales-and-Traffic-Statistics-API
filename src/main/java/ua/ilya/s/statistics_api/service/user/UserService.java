package ua.ilya.s.statistics_api.service.user;

import ua.ilya.s.statistics_api.dto.user.UserRegistrationRequestDto;
import ua.ilya.s.statistics_api.dto.user.UserRegistrationResponseDto;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto);
}
