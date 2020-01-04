package com.mkolongo.judgesystem.domain.models;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "categories")
public class Category extends BaseModel {

    private String name;
    private Category parentCategory;
    private Set<Category> subcategories;
    private Set<Contest> contests;

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
//    @JoinColumn(name = "parent_category_id", referencedColumnName = "id")
    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }

    @OneToMany(mappedBy = "parentCategory")
    public Set<Category> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(Set<Category> subcategories) {
        this.subcategories = subcategories;
    }

    @OneToMany(mappedBy = "category")
    public Set<Contest> getContests() {
        return contests;
    }

    public void setContests(Set<Contest> contests) {
        this.contests = contests;
    }
}
