package pl.coderslab.app.picture;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PictureRepositoryPageable extends PagingAndSortingRepository<Picture, Long> {
}
