package mutsa_aegeodon.aja.controller;

import lombok.RequiredArgsConstructor;
import mutsa_aegeodon.aja.entity.Category;
import mutsa_aegeodon.aja.repository.CategoryRepository;
import mutsa_aegeodon.aja.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCateGory(@RequestParam String name) {
        Category category = categoryService.createCategory(name);
        return ResponseEntity.ok(category);
    }
    @GetMapping
    public ResponseEntity<List<Category>> getCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @DeleteMapping
    public ResponseEntity<Void>deleteCateGory(@RequestParam Long categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

}
