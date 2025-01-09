package ch.bbw.ap.shop.productmanager.services.impl;

import ch.bbw.ap.shop.productmanager.controllers.PictureController;
import ch.bbw.ap.shop.productmanager.mapper.PictureMapper;
import ch.bbw.ap.shop.productmanager.models.Picture;
import ch.bbw.ap.shop.productmanager.models.PictureRequest;
import ch.bbw.ap.shop.productmanager.repositories.PictureRepository;
import ch.bbw.ap.shop.productmanager.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private PictureMapper pictureMapper;


    @Value("${product-manager.pictures.path: /static/images}")
    private String path;


    public byte[] getPicture(Long id) throws IOException {
        Optional<Picture> pictureOptional = pictureRepository.findById(id);

        if(pictureOptional.isEmpty()) {
            return null;
        }

        Picture picture = pictureOptional.get();
        String file = String.format("%s/%s", path.trim(), picture.getFilename());
        InputStream in = getClass().getResourceAsStream(file);

        if(in == null) {
            return null;
        }

        return in.readAllBytes();
    }

    @Override
    public Picture createPicture(PictureRequest pictureRequest) {
        Picture picture = pictureMapper.map(pictureRequest);

        pictureRepository.save(picture);

        return picture;
    }
}
