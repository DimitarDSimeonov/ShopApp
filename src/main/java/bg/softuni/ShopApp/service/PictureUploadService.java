package bg.softuni.ShopApp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureUploadService {

    String uploadFile(MultipartFile multipartFile) throws IOException;
}
