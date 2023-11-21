package BCI.globalLogic.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDTO {
    @NotEmpty(message = "The token cannot be empty.")
    private String token;
}
