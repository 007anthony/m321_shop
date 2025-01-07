package ch.bbw.ap.shop.productmanager.controllers;

import ch.bbw.ap.shop.productmanager.models.Picture;
import ch.bbw.ap.shop.productmanager.services.PictureService;
import jakarta.ws.rs.Path;
import org.apache.coyote.Response;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/pictures")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getPicture(@PathVariable Long id) throws IOException {

        Picture picture = pictureService.getById(id);

        if(picture == null) {
            return null;
        }

        String filename = picture.getFilename();

        InputStream in = getClass().getResourceAsStream("/static/images/" + filename);

        return in.readAllBytes();

    }
}
