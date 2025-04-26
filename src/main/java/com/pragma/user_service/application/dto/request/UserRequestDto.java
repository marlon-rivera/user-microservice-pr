package com.pragma.user_service.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pragma.user_service.application.dto.utils.constants.UserRequestConstants;
import com.pragma.user_service.application.dto.utils.constants.UserRequestConstantsOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(
        description = UserRequestConstantsOpenApi.USER_DESCRIPTION
)
public class UserRequestDto {

    @Schema(
            description = UserRequestConstantsOpenApi.USER_NAME_DESCRIPTION,
            example = UserRequestConstantsOpenApi.USER_NAME_EXAMPLE,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = UserRequestConstants.NAME_MUST_MANDATORY)
    private String name;
    @Schema(
            description = UserRequestConstantsOpenApi.USER_LAST_NAME_DESCRIPTION,
            example = UserRequestConstantsOpenApi.USER_LAST_NAME_EXAMPLE,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = UserRequestConstants.LAST_NAME_MUST_MANDATORY)
    private String lastName;
    @Schema(
            description = UserRequestConstantsOpenApi.USER_DNI_DESCRIPTION,
            example = UserRequestConstantsOpenApi.USER_DNI_EXAMPLE,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = UserRequestConstants.DNI_MUST_MANDATORY)
    @Pattern(regexp = UserRequestConstants.REGEX_DNI,
            message = UserRequestConstants.DNI_MUST_BE_VALID)
    private String dni;
    @Schema(
            description = UserRequestConstantsOpenApi.USER_PHONE_NUMBER_DESCRIPTION,
            example = UserRequestConstantsOpenApi.USER_PHONE_NUMBER_EXAMPLE,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = UserRequestConstants.PHONE_NUMBER_MUST_MANDATORY)
    @Pattern(regexp = UserRequestConstants.REGEX_PHONE_NUMBER)
    private String phoneNumber;
    @Schema(
            description = UserRequestConstantsOpenApi.USER_DATE_OF_BIRTH_DESCRIPTION,
            example = UserRequestConstantsOpenApi.USER_DATE_OF_BIRTH_EXAMPLE,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = UserRequestConstants.DATE_OF_BIRTH_MUST_MANDATORY)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = UserRequestConstants.DATE_FORMAT)
    private LocalDate dateOfBirth;
    @Schema(
            description = UserRequestConstantsOpenApi.USER_EMAIL_DESCRIPTION,
            example = UserRequestConstantsOpenApi.USER_EMAIL_EXAMPLE,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = UserRequestConstants.EMAIL_MUST_MANDATORY)
    @Pattern(regexp = UserRequestConstants.REGEX_EMAIL)
    private String email;
    @Schema(
            description = UserRequestConstantsOpenApi.USER_PASSWORD_DESCRIPTION,
            example = UserRequestConstantsOpenApi.USER_PASSWORD_EXAMPLE,
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = UserRequestConstants.PASSWORD_MUST_MANDATORY)
    private String password;
}
