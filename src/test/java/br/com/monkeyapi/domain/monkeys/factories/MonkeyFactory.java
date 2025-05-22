package br.com.monkeyapi.domain.monkeys.factories;

import br.com.monkeyapi.domain.Monkey;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MonkeyFactory {

    public static final String DEFAULT_NAME = "Charlie";
    public static final String DEFAULT_SPECIES = "Chimpanzee";
    public static final Double DEFAULT_AVERAGE_WEIGHT = 60.5;
    public static final String DEFAULT_DIET = "Fruits and Insects";
    public static final String DEFAULT_HABITAT = "Tropical Forest";
    public static final String DEFAULT_IMAGE_URL = "https://example.com/images/charlie.jpg";
    public static final Long DEFAULT_ID = 1L;
    public static final Boolean DEFAULT_ENABLED = true;

    private MonkeyFactory() {}

    private static Monkey baseMonkey() {
        Monkey monkey = new Monkey();
        monkey.setName(DEFAULT_NAME);
        monkey.setSpecies(DEFAULT_SPECIES);
        monkey.setAverageWeight(DEFAULT_AVERAGE_WEIGHT);
        monkey.setDiet(DEFAULT_DIET);
        monkey.setHabitat(DEFAULT_HABITAT);
        monkey.setImageUrl(DEFAULT_IMAGE_URL);
        monkey.setEnabled(DEFAULT_ENABLED);

        return monkey;
    }

    public static Monkey validMonkey() {
        return baseMonkey();
    }

    public static Monkey savedMonkey(Long id, String name, String species) {
        Monkey monkey = baseMonkey();
        monkey.setId(id);
        monkey.setName(name);
        monkey.setSpecies(species);
        LocalDateTime now = LocalDateTime.now();
        monkey.setCreatedDate(now);
        monkey.setLastModifiedDate(now);
        return monkey;
    }

    public static Monkey savedMonkey(Long id, String name) {
        return savedMonkey(id, name, DEFAULT_SPECIES);
    }

    public static Monkey savedMonkey(Long id) {
        return savedMonkey(id, DEFAULT_NAME);
    }

    public static Monkey savedMonkey() {
        return savedMonkey(DEFAULT_ID);
    }

    public static Monkey monkeyWithCustomValues(String name, String species, String diet) {
        Monkey monkey = baseMonkey();
        monkey.setName(name);
        monkey.setSpecies(species);
        monkey.setDiet(diet);
        return monkey;
    }

    public static HttpServletRequest createHttpServletRequestMock() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getScheme()).thenReturn("https");
        when(request.getServerName()).thenReturn("localhost");
        when(request.getServerPort()).thenReturn(8080);
        return request;
    }
}
