package br.com.monkeyapi.domain.monkeys.factories;

import br.com.monkeyapi.domain.MonkeyDTO;

import java.time.LocalDateTime;

public class MonkeyDTOFactory {

    public static final String DEFAULT_NAME = "Charlie";
    public static final String DEFAULT_SPECIES = "Chimpanzee";
    public static final Double DEFAULT_AVERAGE_WEIGHT = 60.5;
    public static final String DEFAULT_DIET = "Fruits and Insects";
    public static final String DEFAULT_HABITAT = "Tropical Forest";
    public static final String DEFAULT_IMAGE_URL = "https://example.com/images/charlie.jpg";
    public static final Long DEFAULT_ID = 1L;

    private MonkeyDTOFactory() {}

    public static MonkeyDTO savedMonkeyDto(Long id, String name, String species, String diet) {
        LocalDateTime now = LocalDateTime.now();
        return new MonkeyDTO(
                id,
                name,
                species,
                DEFAULT_AVERAGE_WEIGHT,
                diet,
                DEFAULT_HABITAT,
                DEFAULT_IMAGE_URL,
                true,
                now,
                now
        );
    }

    public static MonkeyDTO savedMonkeyDto() {
        return savedMonkeyDto(DEFAULT_ID, DEFAULT_NAME, DEFAULT_SPECIES, DEFAULT_DIET);
    }
}
