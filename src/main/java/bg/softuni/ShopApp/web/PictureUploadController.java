package bg.softuni.ShopApp.web;

import bg.softuni.ShopApp.service.PictureUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PictureUploadController {

    private final PictureUploadService pictureUploadService;

    public PictureUploadController(PictureUploadService pictureUploadService) {
        this.pictureUploadService = pictureUploadService;
    }


    @PostMapping("/upload")
    public String upload(@RequestParam("image")MultipartFile multipartFile, Model model) throws IOException {

        String url = pictureUploadService.uploadFile(multipartFile);

        model.addAttribute("url", url);

        return "redirect:/products/add";
    }
}
