package bg.softuni.shop_app.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class HomeControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void index() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/")
        ).andExpect(model().attributeExists("lastOffers"))
                .andExpect(view().name("index"));
    }

//    @Test
//    @WithMockUser( roles = {"ADMIN", "USER"})
//    void home() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.get("/home")
//        ).andExpect(model().attributeExists("myOffers"))
//                .andExpect(view().name("home"));
//        //ToDo: How to mock userService for empty DB
//    }
}