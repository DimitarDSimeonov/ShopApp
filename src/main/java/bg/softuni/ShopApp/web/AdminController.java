package bg.softuni.ShopApp.web;

import bg.softuni.ShopApp.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final CommentService commentService;

    public AdminController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public String allComments(Model model) {

        model.addAttribute("comments", commentService.getAllComments());

        return "admin-comments";
    }

    @DeleteMapping("/comment/delete/{id}")
    public String deleteCommentById(@PathVariable("id") Long id) {

        commentService.deleteById(id);

        return "redirect:/admin/comments";
    }
}
