package BCI.globalLogic.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginDTO {
    @NotEmpty(message = "El token no puede estar vacio.")
    private String token;
}
