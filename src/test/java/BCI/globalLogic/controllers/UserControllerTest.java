package BCI.globalLogic.controllers;

import BCI.globalLogic.dto.LoginDTO;
import BCI.globalLogic.dto.PhoneDTO;
import BCI.globalLogic.dto.UserDTO;
import BCI.globalLogic.entities.User;
import BCI.globalLogic.services.CreateUser;
import BCI.globalLogic.services.LoginUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CreateUser createUser;

    @MockBean
    private LoginUser loginUser;

    @Test
    void testSignUp() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .name("name")
                .email("email@email.com")
                .password("a2asfGfdfdf4")
                .phones(new HashSet<>(Arrays.asList(PhoneDTO.builder()
                        .number(543764001122L)
                        .cityCode(33)
                        .countryCode("54")
                        .build())))
                .build();
        when(createUser.create(any())).thenReturn(new User(userDTO));

        ResultActions result = mockMvc.perform(post("/signUp")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userDTO)));

        result.andExpect(status().isOk());
    }

    @Test
    void testLogin() throws Exception {
        UserDTO userDTO = UserDTO.builder()
                .name("name")
                .email("email@email.com")
                .password("a2asfGfdfdf4")
                .phones(new HashSet<>(Arrays.asList(PhoneDTO.builder()
                        .number(543764001122L)
                        .cityCode(33)
                        .countryCode("54")
                        .build())))
                .build();
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setToken("aaw123asd211");

        when(loginUser.login(loginDTO.getToken())).thenReturn(new User(userDTO));

        ResultActions result = mockMvc.perform(post("/login")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(loginDTO)));

        result.andExpect(status().isOk());
    }
}
