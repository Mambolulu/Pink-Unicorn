package com.example.jwt.domain.location.place.dto;

import com.example.jwt.domain.location.canton.Canton;
import com.example.jwt.domain.location.canton.dto.CantonDTO;
import com.example.jwt.domain.location.place.Place;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-10T15:55:18+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.5 (Eclipse Adoptium)"
)
@Component
public class PlaceMapperImpl implements PlaceMapper {

    @Override
    public Place fromDTO(PlaceDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Place place = new Place();

        place.setId( dto.getId() );
        place.setName( dto.getName() );
        place.setCanton( cantonDTOToCanton( dto.getCanton() ) );

        return place;
    }

    @Override
    public List<Place> fromDTOs(List<PlaceDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Place> list = new ArrayList<Place>( dtos.size() );
        for ( PlaceDTO placeDTO : dtos ) {
            list.add( fromDTO( placeDTO ) );
        }

        return list;
    }

    @Override
    public Set<Place> fromDTOs(Set<PlaceDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<Place> set = new LinkedHashSet<Place>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( PlaceDTO placeDTO : dtos ) {
            set.add( fromDTO( placeDTO ) );
        }

        return set;
    }

    @Override
    public PlaceDTO toDTO(Place BO) {
        if ( BO == null ) {
            return null;
        }

        PlaceDTO placeDTO = new PlaceDTO();

        placeDTO.setId( BO.getId() );
        placeDTO.setName( BO.getName() );
        placeDTO.setCanton( cantonToCantonDTO( BO.getCanton() ) );

        return placeDTO;
    }

    @Override
    public List<PlaceDTO> toDTOs(List<Place> BOs) {
        if ( BOs == null ) {
            return null;
        }

        List<PlaceDTO> list = new ArrayList<PlaceDTO>( BOs.size() );
        for ( Place place : BOs ) {
            list.add( toDTO( place ) );
        }

        return list;
    }

    @Override
    public Set<PlaceDTO> toDTOs(Set<Place> BOs) {
        if ( BOs == null ) {
            return null;
        }

        Set<PlaceDTO> set = new LinkedHashSet<PlaceDTO>( Math.max( (int) ( BOs.size() / .75f ) + 1, 16 ) );
        for ( Place place : BOs ) {
            set.add( toDTO( place ) );
        }

        return set;
    }

    protected Canton cantonDTOToCanton(CantonDTO cantonDTO) {
        if ( cantonDTO == null ) {
            return null;
        }

        Canton canton = new Canton();

        canton.setId( cantonDTO.getId() );
        canton.setName( cantonDTO.getName() );
        canton.setAbbreviation( cantonDTO.getAbbreviation() );

        return canton;
    }

    protected CantonDTO cantonToCantonDTO(Canton canton) {
        if ( canton == null ) {
            return null;
        }

        CantonDTO cantonDTO = new CantonDTO();

        cantonDTO.setId( canton.getId() );
        cantonDTO.setName( canton.getName() );
        cantonDTO.setAbbreviation( canton.getAbbreviation() );

        return cantonDTO;
    }
}
