package bg.softuni.ShopApp.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void login() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/login")
        ).andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

//    @Test
//    void loginError() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.post("/users/login-error")
//        ).andExpect(redirectedUrl("/users/login"));
//        //ToDo:read more info and refactor this!!
//    }

    @Test
    void register() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/users/register")
        )
                .andExpect(model().attributeExists("userRegisterDTO"))
                .andExpect(view().name("register"));
    }

    @Test
    void registerConfirm() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/users/register")
                        .param("username", "username")
                        .param("password", "password")
                        .param("confirmPassword", "password")
                        .param("firstName", "firstName")
                        .param("lastName", "lastName")
                        .param("email", "user@email.com")
                        .param("phoneNumber", "0899999999")
                        .with(csrf())
        ).andExpect(redirectedUrl("login"));
    }
}