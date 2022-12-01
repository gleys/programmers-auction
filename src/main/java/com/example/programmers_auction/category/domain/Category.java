package com.example.programmers_auction.category.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Table(name = "categories")
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = PROTECTED)
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "fk_parent_to_children"))
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory", cascade = PERSIST)
    private Set<Category> childrenCategories = new HashSet<>();

    public Category(String name) {
        this.name = name;
    }

    public void addChild(Category child) {
        this.childrenCategories.add(child);
        child.parentCategory = this;
    }

    public void deleteChild(Category child) {
        this.childrenCategories.remove(child);
        child.parentCategory = null;
    }
}
