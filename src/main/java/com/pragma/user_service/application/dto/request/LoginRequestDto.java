package com.pragma.user_service.application.dto.request;

import com.pragma.user_service.application.dto.utils.constants.LoginRequestConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequestDto {

    @Schema(
            description = LoginRequestConstants.USERNAME_DESCRIPTION,
            example = LoginRequestConstants.USERNAME_EXAMPLE
    )
    @NotBlank(message = LoginRequestConstants.USERNAME_REQUIRED)
    private String email;
    @Schema(
            description = LoginRequestConstants.PASSWORD_DESCRIPTION,
            example = LoginRequestConstants.PASSWORD_EXAMPLE
    )
    @NotBlank(message = LoginRequestConstants.PASSWORD_REQUIRED)
    private String password;

}
