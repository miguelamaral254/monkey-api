package br.com.monkeyapi.domain;

import br.com.monkeyapi.infrastructure.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MonkeyMapper extends BaseMapper<Monkey, MonkeyDTO> { }
