package ch.bbw.ap.shop.productmanager.services.impl;

import ch.bbw.ap.shop.productmanager.models.Picture;
import ch.bbw.ap.shop.productmanager.repositories.PictureRepository;
import ch.bbw.ap.shop.productmanager.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;


    @Override
    public Picture getById(Long id) {
        Optional<Picture> pictureOptional = pictureRepository.findById(id);

        if(pictureOptional.isEmpty()) {
            return null;
        }

        return pictureOptional.get();
    }
}
