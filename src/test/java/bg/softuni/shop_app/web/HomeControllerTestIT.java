package bg.softuni.shop_app.web;

import bg.softuni.shop_app.service.ProductService;
import bg.softuni.shop_app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class HomeControllerTestIT {

    private HomeController homeControllerToTest;


    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        homeControllerToTest = new HomeController(userService, productService);
    }

    @Test
    void index() throws Exception {
         mockMvc.perform(
                MockMvcRequestBuilders.get("/")
                        .with(csrf())
        ).andExpect(view().name("index"))
                 .andExpect(model().attributeExists("lastOffers"));

         assertEquals("index", homeControllerToTest.index(mock(Model.class)));
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