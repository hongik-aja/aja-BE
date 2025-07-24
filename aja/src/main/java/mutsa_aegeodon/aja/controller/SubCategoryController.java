package mutsa_aegeodon.aja.controller;

import lombok.RequiredArgsConstructor;
import mutsa_aegeodon.aja.entity.SubCategory;
import mutsa_aegeodon.aja.service.CategoryService;
import mutsa_aegeodon.aja.service.SubCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v0/categories/{categoryId}/subcategories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;

    @PostMapping
    public ResponseEntity<SubCategory> createSubCategory(
            @PathVariable Long categoryId,
            @RequestParam String subcategoryName,
            @RequestParam(required = false)String educationContent
    ) {
        SubCategory subCategory = subCategoryService.createSubCategory(categoryId, subcategoryName, educationContent);
        return ResponseEntity.ok(subCategory);
    }

    @GetMapping("/{subCategoryId}")
    public ResponseEntity<SubCategory> getSubCategoryDetail(
            @PathVariable Long subCategoryId,
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(subCategoryService.getSubCategoryDetail(subCategoryId));
    }
}
