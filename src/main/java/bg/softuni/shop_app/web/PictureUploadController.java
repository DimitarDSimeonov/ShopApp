package bg.softuni.shop_app.web;

import bg.softuni.shop_app.service.PictureUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
public class PictureUploadController {

    private final PictureUploadService pictureUploadService;

    public PictureUploadController(PictureUploadService pictureUploadService) {
        this.pictureUploadService = pictureUploadService;
    }


    @PostMapping("/upload")
    public String upload(@RequestParam("image")MultipartFile multipartFile, RedirectAttributes redirectAttributes) throws IOException {

        String url = pictureUploadService.uploadFile(multipartFile);

        redirectAttributes.addFlashAttribute("url", url);

        return "redirect:/products/add";
    }
}
