package br.com.monkeyapi.domain;

import br.com.monkeyapi.infrastructure.ImageConfig;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.function.Consumer;

@Service
public class MonkeyService {

    private final MonkeyRepository monkeyRepository;
    private final ImageConfig imageConfig;

    public MonkeyService(MonkeyRepository monkeyRepository, ImageConfig imageConfig) {
        this.monkeyRepository = monkeyRepository;
        this.imageConfig = imageConfig;
    }

    @Transactional
    public Monkey createMonkey(Monkey monkey, MultipartFile file, HttpServletRequest request) {
        validateCreateMonkeyRules(monkey, file, request);
        validateBusinessRules(monkey);
        return monkeyRepository.save(monkey);
    }

    @Transactional(readOnly = true)
    public Monkey findById(Long id) {
        return monkeyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Monkey not found"));
    }

    @Transactional
    public Monkey updateMonkey(Long id, Consumer<Monkey> mergeNonNull) {
        Monkey monkey = findById(id);
        mergeNonNull.accept(monkey);
        return monkeyRepository.save(monkey);
    }

    @Transactional(readOnly = true)
    public Page<Monkey> searchMonkeys(Specification<Monkey> specification, Pageable pageable) {
        return monkeyRepository.findAll(specification, pageable);
    }

    @Transactional
    public Monkey disableMonkey(Long id, Boolean disable) {
        Monkey monkey = findById(id);
        monkey.setEnabled(disable);
        return monkeyRepository.save(monkey);
    }

    private void validateBusinessRules(Monkey monkey) {
        if (monkey.getSpecies() == null || monkey.getSpecies().trim().isEmpty()) {
            throw new RuntimeException("Species is required");
        }

        if (monkey.getAverageWeight() <= 0) {
            throw new RuntimeException("Average weight must be greater than 0");
        }

        if (monkey.getHabitat() == null || monkey.getHabitat().trim().isEmpty()) {
            throw new RuntimeException("Habitat is required");
        }
    }

    private void validateCreateMonkeyRules(Monkey monkey, MultipartFile file, HttpServletRequest request) {
        try {
            if (file != null && !file.isEmpty()) {
                String imageUrl = imageConfig.saveImage(file, request);
                monkey.setImageUrl(imageUrl);
            }
        } catch (IOException e) {
            throw new RuntimeException("Server error while saving image");
        }
    }

    private void validateUpdate(Monkey monkey) {
        if (monkeyRepository.existsByNameAndIdNot(monkey.getName(), monkey.getId())) {
            throw new RuntimeException("Monkey with this name already exists");
        }
    }
}
