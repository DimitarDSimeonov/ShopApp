package bg.softuni.shop_app.web;

import bg.softuni.shop_app.model.DTO.comment.CommentViewDTO;
import bg.softuni.shop_app.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping("comments/view/{id}")
    public ResponseEntity<List<CommentViewDTO>> showComments(@PathVariable("id") Long id) {

        return ResponseEntity.ok(commentService.getCommentsByProductId(id));
    }
}
