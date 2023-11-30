package bg.softuni.ShopApp.service;

import bg.softuni.ShopApp.model.DTO.comment.CommentAddDTO;
import bg.softuni.ShopApp.model.DTO.comment.CommentViewDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    void createComment(CommentAddDTO commentAddDTO, Long id, String username);

    List<CommentViewDTO> getCommentsByProductId(Long id);
}
