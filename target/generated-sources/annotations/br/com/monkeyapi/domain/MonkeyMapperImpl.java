package br.com.monkeyapi.domain;

import java.time.LocalDateTime;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-22T20:11:43-0300",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class MonkeyMapperImpl implements MonkeyMapper {

    @Override
    public MonkeyDTO toDto(Monkey entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;
        String species = null;
        Double averageWeight = null;
        String habitat = null;
        String imageUrl = null;
        String diet = null;
        Boolean enabled = null;
        LocalDateTime createdDate = null;
        LocalDateTime lastModifiedDate = null;

        id = entity.getId();
        name = entity.getName();
        species = entity.getSpecies();
        averageWeight = entity.getAverageWeight();
        habitat = entity.getHabitat();
        imageUrl = entity.getImageUrl();
        diet = entity.getDiet();
        enabled = entity.getEnabled();
        createdDate = entity.getCreatedDate();
        lastModifiedDate = entity.getLastModifiedDate();

        MonkeyDTO monkeyDTO = new MonkeyDTO( id, name, species, averageWeight, habitat, imageUrl, diet, enabled, createdDate, lastModifiedDate );

        return monkeyDTO;
    }

    @Override
    public Monkey toEntity(MonkeyDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Monkey monkey = new Monkey();

        monkey.setId( dto.id() );
        monkey.setName( dto.name() );
        monkey.setSpecies( dto.species() );
        if ( dto.averageWeight() != null ) {
            monkey.setAverageWeight( dto.averageWeight() );
        }
        monkey.setHabitat( dto.habitat() );
        monkey.setDiet( dto.diet() );
        monkey.setImageUrl( dto.imageUrl() );
        monkey.setEnabled( dto.enabled() );
        monkey.setCreatedDate( dto.createdDate() );
        monkey.setLastModifiedDate( dto.lastModifiedDate() );

        return monkey;
    }

    @Override
    public void mergeNonNull(MonkeyDTO dto, Monkey entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.id() != null ) {
            entity.setId( dto.id() );
        }
        if ( dto.name() != null ) {
            entity.setName( dto.name() );
        }
        if ( dto.species() != null ) {
            entity.setSpecies( dto.species() );
        }
        if ( dto.averageWeight() != null ) {
            entity.setAverageWeight( dto.averageWeight() );
        }
        if ( dto.habitat() != null ) {
            entity.setHabitat( dto.habitat() );
        }
        if ( dto.diet() != null ) {
            entity.setDiet( dto.diet() );
        }
        if ( dto.imageUrl() != null ) {
            entity.setImageUrl( dto.imageUrl() );
        }
        if ( dto.enabled() != null ) {
            entity.setEnabled( dto.enabled() );
        }
        if ( dto.createdDate() != null ) {
            entity.setCreatedDate( dto.createdDate() );
        }
        if ( dto.lastModifiedDate() != null ) {
            entity.setLastModifiedDate( dto.lastModifiedDate() );
        }
    }
}
