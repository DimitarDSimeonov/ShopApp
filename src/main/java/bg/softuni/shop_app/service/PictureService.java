package bg.softuni.shop_app.service;

import bg.softuni.shop_app.model.entity.Picture;

public interface PictureService {

    void createPicture(String url);

    Picture getPictureByURL(String pictureURL);

    void setProduct(Long id, String url);
}
