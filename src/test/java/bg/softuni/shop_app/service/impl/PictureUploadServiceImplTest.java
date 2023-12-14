package bg.softuni.shop_app.service.impl;

import bg.softuni.shop_app.service.PictureService;
import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

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

//    @Test
//    void uploadFile() throws IOException {
//        MultipartFile multipartFile = mock(MultipartFile.class);
//        when(mockCloudinary.uploader())
//                .thenReturn(mock(Uploader.class));
//
//        when(any(Uploader.class).upload(any(Object.class), any(Map.class)).get("url").toString())
//                .thenReturn("");
//
//        String result = pictureUploadServiceToTest.uploadFile(multipartFile);
//
//        assertEquals("", result);
//
//        //ToDo: try to mock Cloudinary
//    }
}