package bg.softuni.ShopApp.config;

import bg.softuni.ShopApp.model.DTO.comment.CommentViewDTO;
import bg.softuni.ShopApp.model.entity.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();

        modelMapper
                .createTypeMap(Comment.class , CommentViewDTO.class)
                .addMapping(Comment::getAuthorFullName, CommentViewDTO::setAuthor);
        return modelMapper;
    }
}
