package com.example.jwt.domain.rank.dto;

import com.example.jwt.domain.rank.Rank;
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
public class RankMapperImpl implements RankMapper {

    @Override
    public Rank fromDTO(RankDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Rank rank = new Rank();

        rank.setId( dto.getId() );
        rank.setName( dto.getName() );
        rank.setDiscount( dto.getDiscount() );
        rank.setNeededSeeds( dto.getNeededSeeds() );

        return rank;
    }

    @Override
    public List<Rank> fromDTOs(List<RankDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Rank> list = new ArrayList<Rank>( dtos.size() );
        for ( RankDTO rankDTO : dtos ) {
            list.add( fromDTO( rankDTO ) );
        }

        return list;
    }

    @Override
    public Set<Rank> fromDTOs(Set<RankDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<Rank> set = new LinkedHashSet<Rank>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( RankDTO rankDTO : dtos ) {
            set.add( fromDTO( rankDTO ) );
        }

        return set;
    }

    @Override
    public RankDTO toDTO(Rank BO) {
        if ( BO == null ) {
            return null;
        }

        RankDTO rankDTO = new RankDTO();

        rankDTO.setId( BO.getId() );
        rankDTO.setName( BO.getName() );
        rankDTO.setDiscount( BO.getDiscount() );
        rankDTO.setNeededSeeds( BO.getNeededSeeds() );

        return rankDTO;
    }

    @Override
    public List<RankDTO> toDTOs(List<Rank> BOs) {
        if ( BOs == null ) {
            return null;
        }

        List<RankDTO> list = new ArrayList<RankDTO>( BOs.size() );
        for ( Rank rank : BOs ) {
            list.add( toDTO( rank ) );
        }

        return list;
    }

    @Override
    public Set<RankDTO> toDTOs(Set<Rank> BOs) {
        if ( BOs == null ) {
            return null;
        }

        Set<RankDTO> set = new LinkedHashSet<RankDTO>( Math.max( (int) ( BOs.size() / .75f ) + 1, 16 ) );
        for ( Rank rank : BOs ) {
            set.add( toDTO( rank ) );
        }

        return set;
    }
}
