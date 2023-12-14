package bg.softuni.shop_app.service.impl;

import bg.softuni.shop_app.model.dto.comment.CommentAddDTO;
import bg.softuni.shop_app.model.dto.comment.CommentViewDTO;
import bg.softuni.shop_app.model.entity.Comment;
import bg.softuni.shop_app.repository.CommentRepository;
import bg.softuni.shop_app.service.CommentService;
import bg.softuni.shop_app.service.ProductService;
import bg.softuni.shop_app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final ProductService productService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, ProductService productService, ModelMapper modelMapper, UserService userService) {
        this.commentRepository = commentRepository;
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @Override
    public void createComment(CommentAddDTO commentAddDTO, Long id, String username) {
        Comment comment = modelMapper.map(commentAddDTO, Comment.class);
        comment.setProduct(productService.getById(id));
        comment.setAuthor(userService.getByUsername(username));
        comment.setDate(LocalDateTime.now());

        commentRepository.save(comment);
    }

    @Override
    public List<CommentViewDTO> getCommentsByProductId(Long id) {
        return commentRepository.findAllByProduct_Id(id)
                .stream()
                .map(comment -> modelMapper.map(comment, CommentViewDTO.class))
                .toList();
    }

    @Override
    public List<CommentViewDTO> getAllComments() {
        return commentRepository
                .findAll()
                .stream()
                .map(comment -> modelMapper.map(comment, CommentViewDTO.class))
                .toList();
    }

    @Override
    public void deleteById(Long id) {
        commentRepository.deleteById(id);
    }
}
