package mutsa_aegeodon.aja.repository;

import mutsa_aegeodon.aja.entity.Category;
import mutsa_aegeodon.aja.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    List<SubCategory> findByCategory(Category category);
}
