package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.DTO.comment.CommentAddDTO;
import bg.softuni.ShopApp.model.DTO.comment.CommentViewDTO;
import bg.softuni.ShopApp.model.entity.Comment;
import bg.softuni.ShopApp.repository.CommentRepository;
import bg.softuni.ShopApp.service.CommentService;
import bg.softuni.ShopApp.service.ProductService;
import bg.softuni.ShopApp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
                .map(comment -> {
                    CommentViewDTO commentViewDTO = modelMapper.map(comment, CommentViewDTO.class);
                    return commentViewDTO;
                })
                .collect(Collectors.toList());
    }
}
//ToDo:Create comments