package BCI.globalLogic.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private static final int MIN_SIZE_FOR_PASSWORD = 8;

    private static final int MAX_SIZE_FOR_PASSWORD = 12;

    private String name;
    @Email(message = "Please provide a valid email address")
    private String email;

    @Size(min = MIN_SIZE_FOR_PASSWORD, max = MAX_SIZE_FOR_PASSWORD, message = " Password must have a length between 8 and 12 characters. ")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = " Only letters and digits are allowed in password. ")
    @Pattern(regexp = "^(\\D*\\d\\D*){2}$", message = " The password should have only 2 numbers. ")
    @Pattern(regexp = "^(?=.*[^A-Z])[^A-Z]*[A-Z][^A-Z]*$", message = " The password entered should have only 1 uppercase letter. ")
    @NotNull
    private String password;
    private Set<PhoneDTO> phones;
}
