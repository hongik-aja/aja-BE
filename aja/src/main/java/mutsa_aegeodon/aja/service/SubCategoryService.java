package mutsa_aegeodon.aja.service;

import lombok.RequiredArgsConstructor;
import mutsa_aegeodon.aja.entity.Category;
import mutsa_aegeodon.aja.entity.SubCategory;
import mutsa_aegeodon.aja.repository.CategoryRepository;
import mutsa_aegeodon.aja.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubCategoryService {
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public SubCategory createSubCategory(Long categoryId, String subCategoryName, String educationContent) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다" ));

        SubCategory subCategory = SubCategory.builder()
                .category(category)
                .subcategoryName(subCategoryName)
                .educationContent(educationContent).build();

        return subCategoryRepository.save(subCategory);

    }

    public List<SubCategory> getSubCategories(Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(()-> new IllegalArgumentException("해당 카테고리가 존재하지 않습니다"));
        return subCategoryRepository.findByCategory(category);
    }

    public SubCategory getSubCategoryDetail(Long subCategoryId) {
        return subCategoryRepository.findById(subCategoryId).orElseThrow(()->new IllegalArgumentException("해당 서브 카테고리가 존재하지 않습니다"));
    }



}
