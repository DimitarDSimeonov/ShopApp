package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.entity.Picture;
import bg.softuni.ShopApp.model.entity.Product;
import bg.softuni.ShopApp.repository.PictureRepository;
import bg.softuni.ShopApp.service.PictureService;
import bg.softuni.ShopApp.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final ProductService productService;

    public PictureServiceImpl(PictureRepository pictureRepository, ProductService productService) {
        this.pictureRepository = pictureRepository;
        this.productService = productService;
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

    @Override
    public void setProduct(Long id, String url) {
        Product product = productService.getById(id);

        Picture picture = pictureRepository.findByURL(url);
        picture.setProduct(product);

        pictureRepository.saveAndFlush(picture);
    }
}
