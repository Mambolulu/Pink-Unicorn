package com.example.jwt.domain.location.canton.dto;

import com.example.jwt.domain.location.canton.Canton;
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
public class CantonMapperImpl implements CantonMapper {

    @Override
    public Canton fromDTO(CantonDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Canton canton = new Canton();

        canton.setId( dto.getId() );
        canton.setName( dto.getName() );
        canton.setAbbreviation( dto.getAbbreviation() );

        return canton;
    }

    @Override
    public List<Canton> fromDTOs(List<CantonDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Canton> list = new ArrayList<Canton>( dtos.size() );
        for ( CantonDTO cantonDTO : dtos ) {
            list.add( fromDTO( cantonDTO ) );
        }

        return list;
    }

    @Override
    public Set<Canton> fromDTOs(Set<CantonDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<Canton> set = new LinkedHashSet<Canton>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( CantonDTO cantonDTO : dtos ) {
            set.add( fromDTO( cantonDTO ) );
        }

        return set;
    }

    @Override
    public CantonDTO toDTO(Canton BO) {
        if ( BO == null ) {
            return null;
        }

        CantonDTO cantonDTO = new CantonDTO();

        cantonDTO.setId( BO.getId() );
        cantonDTO.setName( BO.getName() );
        cantonDTO.setAbbreviation( BO.getAbbreviation() );

        return cantonDTO;
    }

    @Override
    public List<CantonDTO> toDTOs(List<Canton> BOs) {
        if ( BOs == null ) {
            return null;
        }

        List<CantonDTO> list = new ArrayList<CantonDTO>( BOs.size() );
        for ( Canton canton : BOs ) {
            list.add( toDTO( canton ) );
        }

        return list;
    }

    @Override
    public Set<CantonDTO> toDTOs(Set<Canton> BOs) {
        if ( BOs == null ) {
            return null;
        }

        Set<CantonDTO> set = new LinkedHashSet<CantonDTO>( Math.max( (int) ( BOs.size() / .75f ) + 1, 16 ) );
        for ( Canton canton : BOs ) {
            set.add( toDTO( canton ) );
        }

        return set;
    }
}
