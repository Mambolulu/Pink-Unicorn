package com.example.jwt.domain.product.dto;

import com.example.jwt.domain.product.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-20T10:12:43+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public Product fromDTO(ProductDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( dto.getId() );
        product.setName( dto.getName() );
        product.setOrigin( dto.getOrigin() );
        product.setPurchasePricePer100g( dto.getPurchasePricePer100g() );
        product.setSellingPricePer100g( dto.getSellingPricePer100g() );
        product.setHarvestDate( dto.getHarvestDate() );

        return product;
    }

    @Override
    public List<Product> fromDTOs(List<ProductDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        List<Product> list = new ArrayList<Product>( dtos.size() );
        for ( ProductDTO productDTO : dtos ) {
            list.add( fromDTO( productDTO ) );
        }

        return list;
    }

    @Override
    public Set<Product> fromDTOs(Set<ProductDTO> dtos) {
        if ( dtos == null ) {
            return null;
        }

        Set<Product> set = new LinkedHashSet<Product>( Math.max( (int) ( dtos.size() / .75f ) + 1, 16 ) );
        for ( ProductDTO productDTO : dtos ) {
            set.add( fromDTO( productDTO ) );
        }

        return set;
    }

    @Override
    public ProductDTO toDTO(Product BO) {
        if ( BO == null ) {
            return null;
        }

        UUID id = null;
        String name = null;
        String origin = null;
        BigDecimal purchasePricePer100g = null;
        BigDecimal sellingPricePer100g = null;
        LocalDate harvestDate = null;

        id = BO.getId();
        name = BO.getName();
        origin = BO.getOrigin();
        purchasePricePer100g = BO.getPurchasePricePer100g();
        sellingPricePer100g = BO.getSellingPricePer100g();
        harvestDate = BO.getHarvestDate();

        ProductDTO productDTO = new ProductDTO( id, name, origin, purchasePricePer100g, sellingPricePer100g, harvestDate );

        return productDTO;
    }

    @Override
    public List<ProductDTO> toDTOs(List<Product> BOs) {
        if ( BOs == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( BOs.size() );
        for ( Product product : BOs ) {
            list.add( toDTO( product ) );
        }

        return list;
    }

    @Override
    public Set<ProductDTO> toDTOs(Set<Product> BOs) {
        if ( BOs == null ) {
            return null;
        }

        Set<ProductDTO> set = new LinkedHashSet<ProductDTO>( Math.max( (int) ( BOs.size() / .75f ) + 1, 16 ) );
        for ( Product product : BOs ) {
            set.add( toDTO( product ) );
        }

        return set;
    }
}
