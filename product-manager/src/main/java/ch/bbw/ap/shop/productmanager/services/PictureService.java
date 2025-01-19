package ch.bbw.ap.shop.productmanager.services;

import ch.bbw.ap.shop.productmanager.models.Picture;
import ch.bbw.ap.shop.productmanager.models.PictureRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {

    byte[] getPicture(Long id) throws IOException;

    Picture getById(Long id);

    Picture createPicture(PictureRequest pictureRequest);

    void uploadPicture(Long pictureId, MultipartFile file) throws IOException;

    boolean deletePicture(Long id);
}
