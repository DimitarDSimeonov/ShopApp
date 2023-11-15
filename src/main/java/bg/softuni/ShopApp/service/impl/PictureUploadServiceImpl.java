package bg.softuni.ShopApp.service.impl;

import bg.softuni.ShopApp.service.PictureService;
import bg.softuni.ShopApp.service.PictureUploadService;
import com.cloudinary.*;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
public class PictureUploadServiceImpl implements PictureUploadService {

    private final Cloudinary cloudinary;
    private final PictureService pictureService;

    public PictureUploadServiceImpl(Cloudinary cloudinary, PictureService pictureService) {
        this.cloudinary = cloudinary;
        this.pictureService = pictureService;
    }

    @Override
    public String uploadFile(MultipartFile multipartFile) throws IOException {

        String url = cloudinary.uploader()
                .upload(multipartFile.getBytes(),//ToDo make signature
                        ObjectUtils.asMap("public_id", UUID.randomUUID().toString()))
                .get("url")
                .toString();

        pictureService.createPicture(url);

        return url;
    }
}
