package com.example.jwt.domain.category;

import com.example.jwt.core.generic.ExtendedEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
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
}
