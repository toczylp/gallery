package pl.coderslab.app.category;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> readAllCategories() {
        return categoryRepository.findAll();
    }

    public Category readCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    public void save(Category category) {
        categoryRepository.save(category);
    }
}
