package com.example.jwt.domain.location.place.dto;

import com.example.jwt.core.generic.ExtendedDTO;
import com.example.jwt.domain.location.canton.dto.CantonDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class PlaceDTO extends ExtendedDTO {

    @NotNull
    private String name;

    @Valid
    private CantonDTO canton;

    public PlaceDTO() {
    }

    public PlaceDTO(UUID id, String name, CantonDTO canton) {
        super(id);
        this.name = name;
        this.canton = canton;
    }

    public String getName() {
        return name;
    }

    public PlaceDTO setName(String name) {
        this.name = name;
        return this;
    }

    public CantonDTO getCanton() {
        return canton;
    }

    public PlaceDTO setCanton(CantonDTO canton) {
        this.canton = canton;
        return this;
    }
}
