package org.polimi.nexbuy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // Many-to-One relationship with Category
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // One-to-Many relationship with Product
    @OneToMany(mappedBy = "subCategory")
    private List<Product> products;
}