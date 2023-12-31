package com.example.jwt.domain.category;

import com.example.jwt.core.generic.ExtendedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category extends ExtendedEntity {

    private String name;

    public Category() {}

    public Category(UUID id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
