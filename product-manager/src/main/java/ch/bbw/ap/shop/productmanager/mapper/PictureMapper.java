package ch.bbw.ap.shop.productmanager.mapper;

import ch.bbw.ap.shop.productmanager.models.Picture;
import ch.bbw.ap.shop.productmanager.models.PictureRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PictureMapper {

    Picture map(PictureRequest pictureRequest);
}
