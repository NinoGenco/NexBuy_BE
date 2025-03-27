package org.polimi.nexbuy.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    private Integer quantity;

    private boolean available;

    // Relationship with Category (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Relationship with SubCategory (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private SubCategory subCategory;
}