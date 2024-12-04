package ua.ilya.s.statistics_api.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequestDto {
    @NotBlank
    @Size(min = 4, max = 50)
    private String username;
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    public UserRegistrationRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
