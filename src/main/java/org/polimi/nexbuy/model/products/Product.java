package org.polimi.nexbuy.model.products;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private boolean available = true;

    // Relationship with Category (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    // Relationship with SubCategory (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "subcategory_id", nullable = false)
    private SubCategory subCategory;

    @Column(name = "seller", nullable = false)
    private String seller;
}