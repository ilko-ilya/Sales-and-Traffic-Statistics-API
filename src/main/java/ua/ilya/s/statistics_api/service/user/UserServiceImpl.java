package ua.ilya.s.statistics_api.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.ilya.s.statistics_api.dto.user.UserRegistrationRequestDto;
import ua.ilya.s.statistics_api.dto.user.UserRegistrationResponseDto;
import ua.ilya.s.statistics_api.exception.RegistrationException;
import ua.ilya.s.statistics_api.mapper.user.UserRegistrationResponseDtoMapper;
import ua.ilya.s.statistics_api.model.User;
import ua.ilya.s.statistics_api.repository.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRegistrationResponseDtoMapper responseDtoMapper;

    @Override
    public UserRegistrationResponseDto register(UserRegistrationRequestDto requestDto) {
        if (userRepository.findUserByUsername(requestDto.getUsername()).isPresent()) {
            throw new RegistrationException("User by this username: " +
                    requestDto.getUsername() + " is already exists");
        }
        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        user.setRoles(Set.of(User.Role.USER));

        User savedUser = userRepository.save(user);
        return responseDtoMapper.toDto(savedUser);
    }
}
