package mutsa_aegeodon.aja.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "subcategory")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;

    @Column(length = 50,nullable = false)
    private String subcategoryName;

    @Column(length = 255)
    private String educationContent;

}
