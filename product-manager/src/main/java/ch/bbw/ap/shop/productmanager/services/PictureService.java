package ch.bbw.ap.shop.productmanager.services;

import ch.bbw.ap.shop.productmanager.models.Picture;
import ch.bbw.ap.shop.productmanager.models.PictureRequest;

import java.io.IOException;

public interface PictureService {

    byte[] getPicture(Long id) throws IOException;

    Picture createPicture(PictureRequest pictureRequest);
}
