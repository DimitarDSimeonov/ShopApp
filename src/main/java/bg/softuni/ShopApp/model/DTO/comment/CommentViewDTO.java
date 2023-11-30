package bg.softuni.ShopApp.model.DTO.comment;

public class CommentViewDTO {

    private String content;
    private String author;

    public CommentViewDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
