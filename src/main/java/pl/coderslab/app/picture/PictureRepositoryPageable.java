package pl.coderslab.app.picture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDateTime;

public interface PictureRepositoryPageable extends PagingAndSortingRepository<Picture, Long> {

    Page<Picture> findAllByPublicFlag(Pageable pageable, int publicFlag);
    Page<Picture> findAllByUserLogin(Pageable pageable, String login);
    Page<Picture> findAllByPublicFlagOrderByCreatedDesc(Pageable pageable, int publicFlag);
}