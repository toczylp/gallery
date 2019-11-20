package pl.coderslab.app.picture;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PictureRepositoryPageable extends PagingAndSortingRepository<Picture, Long> {

    Page<Picture> findAllByPublicFlag(Pageable pageable, int publicFlag);
    Page<Picture> findAllByUserLogin(Pageable pageable, String login);
    Page<Picture> findAllByPublicFlagOrderByCreatedDesc(Pageable pageable, int publicFlag);
    Page<Picture> findAllByPublicFlagOrderByDirectDisplayQtyDesc(Pageable pageable, int publicFlag);
    Page<Picture> findAllByPublicFlagAndCategoryName(Pageable pageable, int publicFlag, String category);
    Page<Picture> findAllByPublicFlagOrderByRatingDesc(Pageable pageable, int publicFlag);
}