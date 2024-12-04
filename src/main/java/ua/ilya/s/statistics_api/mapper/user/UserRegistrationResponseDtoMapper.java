package ua.ilya.s.statistics_api.mapper.user;

import org.mapstruct.Mapper;
import ua.ilya.s.statistics_api.dto.user.UserRegistrationResponseDto;
import ua.ilya.s.statistics_api.mapper.Mappable;
import ua.ilya.s.statistics_api.model.User;

@Mapper(componentModel = "spring")
public interface UserRegistrationResponseDtoMapper extends Mappable<User, UserRegistrationResponseDto> {
}
