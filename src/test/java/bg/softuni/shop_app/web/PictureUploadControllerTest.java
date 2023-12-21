package bg.softuni.shop_app.web;

import bg.softuni.shop_app.service.PictureUploadService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PictureUploadControllerTest {

    private PictureUploadController pictureUploadControllerToTest;

    @Mock
    private PictureUploadService mockPictureUploadService;

    @BeforeEach
    void setUp() {
        pictureUploadControllerToTest = new PictureUploadController(mockPictureUploadService);
    }

    @Test
    void upload() throws IOException {
        MultipartFile multipartFile = mock(MultipartFile.class);
        RedirectAttributes redirectAttributes = mock(RedirectAttributes.class);

        when(mockPictureUploadService.uploadFile(multipartFile))
                .thenReturn("url");

        assertEquals("redirect:/products/add", pictureUploadControllerToTest.upload(multipartFile, redirectAttributes));
    }
}