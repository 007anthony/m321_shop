package ch.bbw.ap.shop.productmanager.repositories;

import ch.bbw.ap.shop.productmanager.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long>{
}
