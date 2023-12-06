package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.service.PictureService;
import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PictureUploadServiceImplTest {

    private PictureUploadServiceImpl pictureUploadServiceToTest;

    @Mock
    private Cloudinary mockCloudinary;

    @Mock
    private PictureService mockPictureService;

    @BeforeEach
    void setUp() {
        pictureUploadServiceToTest =
                new PictureUploadServiceImpl(mockCloudinary, mockPictureService);
    }

    @Test
    void uploadFile() throws IOException {
        MultipartFile multipartFile = mock(MultipartFile.class);
        when(mockCloudinary.uploader())
                .thenReturn(mock(Uploader.class));

        when(any(Uploader.class).upload(any(Object.class), any(Map.class)).get("url").toString())
                .thenReturn("");

        String result = pictureUploadServiceToTest.uploadFile(multipartFile);

        assertEquals("", result);

        //ToDo: try to mock Cloudinary
    }
}