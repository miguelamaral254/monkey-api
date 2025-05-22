package br.com.monkeyapi.domain.monkeys;

import br.com.monkeyapi.domain.Monkey;
import br.com.monkeyapi.domain.MonkeyRepository;
import br.com.monkeyapi.domain.MonkeyService;
import br.com.monkeyapi.domain.monkeys.factories.MonkeyFactory;
import br.com.monkeyapi.infrastructure.ImageConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Consumer;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MonkeyServiceTest {

    @InjectMocks
    private MonkeyService monkeyService;

    @Mock
    private MonkeyRepository monkeyRepository;

    @Mock
    private ImageConfig imageConfig;

    @Mock
    private MultipartFile file;

    @Mock
    private HttpServletRequest request;

    @Test
    void createMonkey_whenMonkeyIsValid_thenCreateSuccessfully() {
        // Arrange
        Monkey validMonkey = MonkeyFactory.validMonkey();
        validMonkey.setImageUrl("https://example.com/images/charlie.jpg");  // Ensure this URL corresponds to a valid image type

        // Mocking a file upload (if necessary)
        MultipartFile mockFile = mock(MultipartFile.class);
        when(mockFile.getContentType()).thenReturn("image/jpeg");
        when(mockFile.isEmpty()).thenReturn(false);

        // Act
        Monkey createdMonkey = monkeyService.createMonkey(validMonkey, mockFile, request);

        // Assert
        assertNotNull(createdMonkey);
        assertEquals("Charlie", createdMonkey.getName());
        assertEquals("https://example.com/images/charlie.jpg", createdMonkey.getImageUrl());
    }

    @Test
    @DisplayName("Should throw RuntimeException when species is not provided")
    void createMonkey_whenSpeciesIsMissing_thenThrowException() {
        Monkey monkey = MonkeyFactory.validMonkey();
        monkey.setSpecies(null);  // Torna a espécie nula

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> monkeyService.createMonkey(monkey, file, request));

        assertEquals("Species is required", exception.getMessage());  // Verifica se a exceção é a esperada
    }

    @Test
    @DisplayName("Should find Monkey by ID successfully when exists")
    void findById_whenMonkeyExists_thenReturnMonkey() {
        Long monkeyId = 1L;
        Monkey expectedMonkey = MonkeyFactory.savedMonkey(monkeyId, "Monkey", "Amazon");

        when(monkeyRepository.findById(monkeyId)).thenReturn(Optional.of(expectedMonkey));

        Monkey foundMonkey = monkeyService.findById(monkeyId);

        verify(monkeyRepository, times(1)).findById(monkeyId);
        assertEquals(expectedMonkey, foundMonkey);  // Verifica se o Monkey encontrado é igual ao esperado
    }

    @Test
    @DisplayName("Should throw RuntimeException when Monkey not found by ID")
    void findById_whenMonkeyNotFound_thenThrowException() {
        Long monkeyId = 999L;

        when(monkeyRepository.findById(monkeyId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> monkeyService.findById(monkeyId));

        assertEquals("Monkey not found", exception.getMessage());  // Verifica a mensagem da exceção
    }


    @Test
    @DisplayName("Should disable Monkey successfully")
    void disableMonkey_whenCalled_thenDisableMonkey() {
        Long monkeyId = 1L;
        Monkey monkey = MonkeyFactory.savedMonkey(monkeyId, "Monkey", "Amazon");
        monkey.setEnabled(true);

        when(monkeyRepository.findById(monkeyId)).thenReturn(Optional.of(monkey));
        when(monkeyRepository.save(any(Monkey.class))).thenReturn(monkey);

        Monkey disabledMonkey = monkeyService.disableMonkey(monkeyId, false);

        verify(monkeyRepository, times(1)).findById(monkeyId);  // Verifica o findById
        verify(monkeyRepository, times(1)).save(any(Monkey.class));  // Verifica o save
        assertFalse(disabledMonkey.getEnabled());  // Verifica se o monkey foi desabilitado corretamente
    }
}
