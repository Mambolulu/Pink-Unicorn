package com.example.jwt.domain.location.zipcode.dto;

import com.example.jwt.domain.location.zipcode.ZipCode;
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
public class ZipCodeMapperImpl implements ZipCodeMapper {

    @Override
    public ZipCode fromDTO(ZipCodeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ZipCode zipCode = new ZipCode();

        zipCode.setId( dto.getId() );
        zipCode.setZipCode( dto.getZipCode() );
        zipCode.setPlace( dto.getPlace() );

        return zipCode;
    }

    @Override
    public List<ZipCode> fromDTOs(List<ZipCodeDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<ZipCode> list = new ArrayList<ZipCode>( dtos.size() );
        for ( ZipCodeDTO zipCodeDTO : dtos ) {
            list.add( fromDTO( zipCodeDTO ) );
        }

        return list;
    }

    @Override
    public Set<ZipCode> fromDTOs(Set<ZipCodeDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<ZipCode> set = new LinkedHashSet<ZipCode>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( ZipCodeDTO zipCodeDTO : dtos ) {
            set.add( fromDTO( zipCodeDTO ) );
        }

        return set;
    }

    @Override
    public ZipCodeDTO toDTO(ZipCode BO) {
        if ( BO == null ) {
            return null;
        }

        ZipCodeDTO zipCodeDTO = new ZipCodeDTO();

        zipCodeDTO.setId( BO.getId() );
        zipCodeDTO.setZipCode( BO.getZipCode() );
        zipCodeDTO.setPlace( BO.getPlace() );

        return zipCodeDTO;
    }

    @Override
    public List<ZipCodeDTO> toDTOs(List<ZipCode> BOs) {
        if ( BOs == null ) {
            return null;
        }

        List<ZipCodeDTO> list = new ArrayList<ZipCodeDTO>( BOs.size() );
        for ( ZipCode zipCode : BOs ) {
            list.add( toDTO( zipCode ) );
        }

        return list;
    }

    @Override
    public Set<ZipCodeDTO> toDTOs(Set<ZipCode> BOs) {
        if ( BOs == null ) {
            return null;
        }

        Set<ZipCodeDTO> set = new LinkedHashSet<ZipCodeDTO>( Math.max( (int) ( BOs.size() / .75f ) + 1, 16 ) );
        for ( ZipCode zipCode : BOs ) {
            set.add( toDTO( zipCode ) );
        }

        return set;
    }
}
