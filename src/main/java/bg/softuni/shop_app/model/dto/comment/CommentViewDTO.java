package bg.softuni.shop_app.model.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentViewDTO {

    private Long id;
    private String content;
    private String author;
}
