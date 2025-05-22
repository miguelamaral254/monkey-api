package br.com.monkeyapi.domain;



import br.com.monkeyapi.infrastructure.validation.CreateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record MonkeyDTO(

        @Null
        Long id,

        @NotBlank(groups = {CreateValidation.class})
        String name,

        @NotBlank(groups = {CreateValidation.class})
        String species,

        @NotNull(groups = {CreateValidation.class})
        @Positive
        Double averageWeight,

        @NotBlank(groups = {CreateValidation.class})
        String habitat,

        @NotBlank(groups = {CreateValidation.class})
        String imageUrl,

        @NotBlank(groups = {CreateValidation.class})
        String diet,

        @Null
        Boolean enabled,

        @Null
        LocalDateTime createdDate,

        @Null
        LocalDateTime lastModifiedDate

)  {}
