package com.example.jwt.domain.location.canton.dto;

import com.example.jwt.core.generic.ExtendedDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;

public class CantonDTO extends ExtendedDTO {

    @NotNull
    private String name;

    @NotNull
    @Size(max = 2)
    private String abbreviation;

    public CantonDTO() {
    }

    public CantonDTO(UUID id, String name, String abbreviation) {
        super(id);
        this.name = name;
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public CantonDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public CantonDTO setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
        return this;
    }
}
