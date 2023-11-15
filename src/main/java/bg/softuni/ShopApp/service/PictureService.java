package bg.softuni.ShopApp.service;

import bg.softuni.ShopApp.model.entity.Picture;
import bg.softuni.ShopApp.model.entity.Product;

public interface PictureService {

    void createPicture(String url);

    Picture getPictureByURL(String pictureURL);

}
