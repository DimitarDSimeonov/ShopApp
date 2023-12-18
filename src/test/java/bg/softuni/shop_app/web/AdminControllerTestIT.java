package bg.softuni.shop_app.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AdminControllerTestIT {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(username = "username", roles={"ADMIN"})
    void allComments() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin/comments")
                        .with(csrf())
        ).andExpect(model().attributeExists("comments"));
    }

//    @Test
//    @WithMockUser(username = "username", roles={"ADMIN"})
//    void deleteCommentById() throws Exception {
//        mockMvc.perform(
//                MockMvcRequestBuilders.delete("/comment/delete/{id}",1)
//                        .with(csrf())
//        ).andExpect(redirectedUrl("/admin/comments"));
//
//        //ToDo: read more for path variable url and refactor!!!
//    }

    @Test
    @WithMockUser(username = "username", roles={"ADMIN"})
    void allProducts() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin/products")
                        .with(csrf())
        ).andExpect(model().attributeExists("products"));
    }

    @Test
    @WithMockUser(username = "username", roles={"ADMIN"})
    void allUsers() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/admin/users")
                        .with(csrf())
        ).andExpect(model().attributeExists("users"));
    }
}