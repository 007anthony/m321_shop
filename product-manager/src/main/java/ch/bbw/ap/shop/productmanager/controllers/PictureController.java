package ch.bbw.ap.shop.productmanager.controllers;

import ch.bbw.ap.shop.productmanager.models.Picture;
import ch.bbw.ap.shop.productmanager.models.PictureRequest;
import ch.bbw.ap.shop.productmanager.services.PictureService;
import jakarta.ws.rs.PathParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/pictures")
public class PictureController {

    private final Logger LOGGER = LoggerFactory.getLogger(PictureController.class);

    @Autowired
    private PictureService pictureService;

    @Value("product-manager.images.path")
    private String path = "/static/images";

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getPicture(@PathVariable Long id) throws IOException {
        byte[] picture = pictureService.getPicture(id);

        if(picture == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(picture);
    }

    @PostMapping
    public Picture createPicture(@RequestBody PictureRequest pictureRequest) {
        return pictureService.createPicture(pictureRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePicture(@PathVariable Long id) {
        boolean deleted = pictureService.deletePicture(id);

        if(!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
