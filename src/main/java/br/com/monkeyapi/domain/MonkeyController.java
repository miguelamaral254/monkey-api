package br.com.monkeyapi.domain;

import br.com.monkeyapi.infrastructure.ApplicationResponse;
import br.com.monkeyapi.infrastructure.validation.CreateValidation;
import br.com.monkeyapi.infrastructure.validation.UpdateValidation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/monkeys")
@RequiredArgsConstructor
public class MonkeyController {

    private final MonkeyService monkeyService;
    private final MonkeyMapper monkeyMapper;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<Void> createMonkey(
            @RequestPart("dto") @Validated(CreateValidation.class) MonkeyDTO monkeyDto,
            @RequestPart(value = "file", required = false) MultipartFile file,
            HttpServletRequest request) throws IOException {

        Monkey monkey = monkeyMapper.toEntity(monkeyDto);
        Monkey savedEntity = monkeyService.createMonkey(monkey, file, request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEntity.getId())
                .toUri();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(location)
                .build();
    }

    @GetMapping
    public ResponseEntity<ApplicationResponse<Page<MonkeyDTO>>> searchMonkeys(
            @RequestParam(value = "species", required = false) String species,
            @RequestParam(value = "habitat", required = false) String habitat,
            @RequestParam(value = "enabled", required = false) Boolean enabled,
            Pageable pageable) {

        Specification<Monkey> specification = Specification.where(null);

        if (species != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("species")), "%" + species.toLowerCase() + "%"));
        }
        if (habitat != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("habitat")), "%" + habitat.toLowerCase() + "%"));
        }
        if (enabled != null) {
            specification = specification.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("enabled"), enabled));
        }

        Page<Monkey> monkeyPage = monkeyService.searchMonkeys(specification, pageable);
        Page<MonkeyDTO> monkeyDTOPage = monkeyMapper.toDto(monkeyPage);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApplicationResponse.ofSuccess(monkeyDTOPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApplicationResponse<MonkeyDTO>> findById(
            @PathVariable Long id) {

        Monkey monkey = monkeyService.findById(id);
        MonkeyDTO monkeyDTO = monkeyMapper.toDto(monkey);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApplicationResponse.ofSuccess(monkeyDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApplicationResponse<MonkeyDTO>> updateMonkey(
            @PathVariable Long id,
            @Validated(UpdateValidation.class)
            @RequestBody MonkeyDTO monkeyDtoUpdates) {
        Monkey updatedMonkey = monkeyService.updateMonkey(id, monkey -> monkeyMapper.mergeNonNull(monkeyDtoUpdates, monkey));
        MonkeyDTO updatedMonkeyDto = monkeyMapper.toDto(updatedMonkey);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ApplicationResponse.ofSuccess(updatedMonkeyDto));
    }

    @PatchMapping("/{id}/enabled")
    public ResponseEntity<ApplicationResponse<String>> updateEnabled(
            @PathVariable Long id,
            @RequestParam String enabled) {

        Boolean status = Boolean.valueOf(enabled);
        Monkey updatedMonkey = monkeyService.disableMonkey(id, status);

        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
                .body(ApplicationResponse.ofSuccess(updatedMonkey.getEnabled().toString()));
    }
}
