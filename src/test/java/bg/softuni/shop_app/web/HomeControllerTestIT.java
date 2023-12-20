package bg.softuni.shop_app.web;

import bg.softuni.shop_app.service.ProductService;
import bg.softuni.shop_app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class HomeControllerTestIT {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new HomeController(userService, productService)).build();
    }

    @Test
    void index() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/")
        ).andReturn();

        assertEquals("index", result.getModelAndView().getViewName());
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