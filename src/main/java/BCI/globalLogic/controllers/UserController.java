package BCI.globalLogic.controllers;

import BCI.globalLogic.dto.LoginDTO;
import BCI.globalLogic.dto.UserDTO;
import BCI.globalLogic.entities.User;
import BCI.globalLogic.services.CreateUser;
import BCI.globalLogic.services.LoginUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {
    private final CreateUser createUser;
    private final LoginUser loginUser;

    public UserController(CreateUser createUser, LoginUser loginUser) {
        this.createUser = createUser;
        this.loginUser = loginUser;
    }

    @PostMapping("/signUp")
    public User signUp(@RequestBody @Valid UserDTO userDTO) {
        return createUser.create(userDTO);
    }

    @PostMapping("/login")
    public User signUp(@RequestBody @Valid LoginDTO body) {
        String token = body.getToken();
        return loginUser.login(token);
    }

}
