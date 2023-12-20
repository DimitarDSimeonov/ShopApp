package bg.softuni.shop_app.web;

import bg.softuni.shop_app.service.ProductService;
import bg.softuni.shop_app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(HomeController.class)
class HomeControllerTestIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HomeController(userService, productService)).build();
    }

    @Test
    void index() throws Exception {
         mockMvc.perform(
                MockMvcRequestBuilders.get("/")
                        .with(csrf())
        ).andExpect(view().name("index"));
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