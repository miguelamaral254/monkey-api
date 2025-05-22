package br.com.monkeyapi.domain.monkeys;

import br.com.monkeyapi.domain.Monkey;
import br.com.monkeyapi.domain.MonkeyDTO;
import br.com.monkeyapi.domain.MonkeyMapper;
import br.com.monkeyapi.domain.MonkeyMapperImpl;
import br.com.monkeyapi.domain.monkeys.factories.MonkeyDTOFactory;
import br.com.monkeyapi.domain.monkeys.factories.MonkeyFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MonkeyMapperTest {

    private final MonkeyMapper monkeyMapper = new MonkeyMapperImpl();

    @Test
    void toDTO_shouldConvertMonkeyToMonkeyDTO() {
        // Arrange
        Monkey monkey = MonkeyFactory.savedMonkey();

        // Act
        MonkeyDTO monkeyDTO = monkeyMapper.toDto(monkey);

        // Assert
        assertNotNull(monkeyDTO);
        assertEquals(monkey.getId(), monkeyDTO.id());
        assertEquals(monkey.getName(), monkeyDTO.name());
        assertEquals(monkey.getSpecies(), monkeyDTO.species());
        assertEquals(monkey.getDiet(), monkeyDTO.diet());
        assertEquals(monkey.getHabitat(), monkeyDTO.habitat());
        assertEquals(monkey.getImageUrl(), monkeyDTO.imageUrl());
        assertEquals(monkey.getEnabled(), monkeyDTO.enabled());
    }

    @Test
    void toEntity_shouldConvertMonkeyDTOToMonkey() {
        // Arrange
        MonkeyDTO monkeyDTO = MonkeyDTOFactory.savedMonkeyDto();

        // Act
        Monkey monkey = monkeyMapper.toEntity(monkeyDTO);

        // Assert
        assertNotNull(monkey);
        assertEquals(monkeyDTO.id(), monkey.getId());
        assertEquals(monkeyDTO.name(), monkey.getName());
        assertEquals(monkeyDTO.species(), monkey.getSpecies());
        assertEquals(monkeyDTO.diet(), monkey.getDiet());
        assertEquals(monkeyDTO.habitat(), monkey.getHabitat());
        assertEquals(monkeyDTO.imageUrl(), monkey.getImageUrl());
        assertEquals(monkeyDTO.enabled(), monkey.getEnabled());
    }
}
