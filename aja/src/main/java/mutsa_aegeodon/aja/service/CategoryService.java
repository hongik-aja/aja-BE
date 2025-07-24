package mutsa_aegeodon.aja.service;

import lombok.RequiredArgsConstructor;
import mutsa_aegeodon.aja.entity.Category;
import mutsa_aegeodon.aja.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Category createCategory(String name) {
        categoryRepository.findByCategoryName(name).ifPresent(c ->{
            throw new IllegalArgumentException("이미 존재하는 카테고리입니다.");
        });
        Category category = Category.builder()
                .categoryName(name)
                .build();
        return categoryRepository.save(category);
    }
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public void deleteCategory(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }


}
