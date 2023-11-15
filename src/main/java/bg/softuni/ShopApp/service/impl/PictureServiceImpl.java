package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.entity.Picture;
import bg.softuni.ShopApp.repository.PictureRepository;
import bg.softuni.ShopApp.service.PictureService;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void createPicture(String url) {
        Picture picture = new Picture();
        picture.setURL(url);
        pictureRepository.save(picture);
    }

    @Override
    public Picture getPictureByURL(String pictureURL) {
        return pictureRepository.findByURL(pictureURL);
    }
}
