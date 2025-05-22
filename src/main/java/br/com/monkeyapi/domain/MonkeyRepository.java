package br.com.monkeyapi.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MonkeyRepository extends JpaRepository<Monkey, Long>, JpaSpecificationExecutor<Monkey> {
    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsByName(String name);

    boolean existsBySpecies(String species);
}
