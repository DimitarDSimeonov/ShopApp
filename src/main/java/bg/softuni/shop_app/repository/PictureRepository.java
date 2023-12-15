package bg.softuni.shop_app.repository;

import bg.softuni.shop_app.model.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    Picture findByUrl(String pictureURL);
}
