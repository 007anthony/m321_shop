package ch.bbw.ap.shop.productmanager.services.impl;

import ch.bbw.ap.shop.productmanager.controllers.PictureController;
import ch.bbw.ap.shop.productmanager.mapper.PictureMapper;
import ch.bbw.ap.shop.productmanager.models.Picture;
import ch.bbw.ap.shop.productmanager.models.PictureRequest;
import ch.bbw.ap.shop.productmanager.repositories.PictureRepository;
import ch.bbw.ap.shop.productmanager.services.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private PictureMapper pictureMapper;


    @Autowired
    private KafkaTemplate<String, Picture> kafkaTemplate;


    @Value("${product-manager.pictures.path: /static/images}")
    private String path;


    public byte[] getPicture(Long id) throws IOException {
        Optional<Picture> pictureOptional = pictureRepository.findById(id);

        if(pictureOptional.isEmpty()) {
            return null;
        }

        Picture picture = pictureOptional.get();
        String file = String.format("%s/%s", path.trim(), picture.getFilename());
        Path filePath = Paths.get(file);

        if(!Files.exists(filePath)) {
            return null;
        }

        return Files.readAllBytes(filePath);
    }

    @Override
    public Picture getById(Long id) {
        Optional<Picture> picture = pictureRepository.findById(id);

        if(picture.isEmpty()) {
            return null;
        }

        return picture.get();
    }

    @Override
    public Picture createPicture(PictureRequest pictureRequest) {
        Picture picture = pictureMapper.map(pictureRequest);

        pictureRepository.save(picture);

        return picture;
    }

    public void uploadPicture(Long productId, MultipartFile file) throws IOException {
        Path uploadDirectory = Paths.get(path).resolve(String.valueOf(productId));

        if(!Files.exists(uploadDirectory)) {
            Files.createDirectories(uploadDirectory);
        }

        Path filePath = uploadDirectory.resolve(file.getOriginalFilename());

        if(!Files.exists(filePath)) {
            throw new IOException("The file already exists");
        }

        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Override
    public boolean deletePicture(Long id) {
        Picture picture = this.getById(id);

        if(picture == null) {
            return false;
        }

        pictureRepository.delete(picture);

        kafkaTemplate.send("pictureTopic", picture);

        return true;
    }


}
