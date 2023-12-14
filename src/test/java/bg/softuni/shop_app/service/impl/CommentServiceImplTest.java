package bg.softuni.shop_app.service.impl;

import bg.softuni.shop_app.model.DTO.comment.CommentAddDTO;
import bg.softuni.shop_app.model.DTO.comment.CommentViewDTO;
import bg.softuni.shop_app.model.entity.Comment;
import bg.softuni.shop_app.model.entity.Product;
import bg.softuni.shop_app.model.entity.User;
import bg.softuni.shop_app.repository.CommentRepository;
import bg.softuni.shop_app.service.ProductService;
import bg.softuni.shop_app.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {

    private CommentServiceImpl commentServiceToTest;

    @Mock
    private CommentRepository mockCommentRepository;

    @Mock
    private ProductService mockProductService;

    @Mock
    private ModelMapper mockModelMapper;

    @Mock
    private UserService mockUserService;

    @BeforeEach
    void setUp() {
        commentServiceToTest =
                new CommentServiceImpl(mockCommentRepository, mockProductService, mockModelMapper, mockUserService);
    }

    @Test
    void createComment() {
        Long id = 1L;
        String username = "username";
        CommentAddDTO commentAddDTO = new CommentAddDTO();
        Comment comment = new Comment();

        when(mockModelMapper.map(any(CommentAddDTO.class), eq(Comment.class)))
                .thenReturn(comment);
        when(mockProductService.getById(id))
                .thenReturn(new Product());
        when(mockUserService.getByUsername(username))
                .thenReturn(new User());

        commentServiceToTest.createComment(commentAddDTO, id, username);

        verify(mockCommentRepository).save(comment);
    }

    @Test
    void getCommentsByProductId() {
        Long id = 1L;
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            comments.add(new Comment());
        }

        CommentViewDTO commentViewDTO = new CommentViewDTO();

        when(mockCommentRepository.findAllByProduct_Id(id))
                .thenReturn(comments);

        when(mockModelMapper.map(any(Comment.class), eq(CommentViewDTO.class)))
                .thenReturn(commentViewDTO);

        List<CommentViewDTO> result = commentServiceToTest.getCommentsByProductId(id);

        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void getAllComments() {
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            comments.add(new Comment());
        }

        CommentViewDTO commentViewDTO = new CommentViewDTO();

        when(mockCommentRepository.findAll())
                .thenReturn(comments);

        when(mockModelMapper.map(any(Comment.class), eq(CommentViewDTO.class)))
                .thenReturn(commentViewDTO);

        List<CommentViewDTO> result = commentServiceToTest.getAllComments();

        assertNotNull(result);
        assertEquals(3, result.size());
    }

    @Test
    void deleteById() {
        Long id = 1L;

        commentServiceToTest.deleteById(id);

        verify(mockCommentRepository).deleteById(id);
    }
}