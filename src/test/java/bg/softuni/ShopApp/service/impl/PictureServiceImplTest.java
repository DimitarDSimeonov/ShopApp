package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.model.entity.Picture;
import bg.softuni.ShopApp.model.entity.Product;
import bg.softuni.ShopApp.repository.PictureRepository;
import bg.softuni.ShopApp.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PictureServiceImplTest {

    private PictureServiceImpl pictureServiceToTest;

    @Mock
    private PictureRepository mockPictureRepository;

    @Mock
    private ProductService mockProductService;

    @BeforeEach
    void setUp() {
        pictureServiceToTest =
                new PictureServiceImpl(mockPictureRepository,mockProductService);
    }

    @Test
    void createPicture() {
        String url = "";

        pictureServiceToTest.createPicture(url);

        verify(mockPictureRepository).save(any(Picture.class));
    }

    @Test
    void getPictureByURL() {
        String url = "";
        Picture picture = new Picture();
        picture.setURL(url);

        when(mockPictureRepository.findByURL(url))
                .thenReturn(picture);

        Picture result = pictureServiceToTest.getPictureByURL(url);

        assertNotNull(result);
        assertEquals(picture.getURL(), result.getURL());
    }

    @Test
    void setProduct() {
        Long id = 1L;
        Product product = new Product();
        String url = "";
        Picture picture = new Picture();

        when(mockProductService.getById(id))
                .thenReturn(product);

        when(mockPictureRepository.findByURL(url))
                .thenReturn(picture);

        pictureServiceToTest.setProduct(id, url);

        verify(mockPictureRepository).saveAndFlush(picture);
    }
}