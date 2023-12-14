package bg.softuni.shop_app.service;

import bg.softuni.shop_app.model.DTO.comment.CommentAddDTO;
import bg.softuni.shop_app.model.DTO.comment.CommentViewDTO;

import java.util.List;

public interface CommentService {
    void createComment(CommentAddDTO commentAddDTO, Long id, String username);

    List<CommentViewDTO> getCommentsByProductId(Long id);

    List<CommentViewDTO> getAllComments();

    void deleteById(Long id);
}
