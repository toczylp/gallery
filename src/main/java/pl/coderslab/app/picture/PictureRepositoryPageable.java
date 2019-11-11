package pl.coderslab.app.picture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PictureRepositoryPageable extends PagingAndSortingRepository<Picture, Long> {

    Page<Picture> findAllByPublicFlag(Pageable pageable, int publicFlag);
    Page<Picture> findAllByUserLogin(Pageable pageable, String login);
}
