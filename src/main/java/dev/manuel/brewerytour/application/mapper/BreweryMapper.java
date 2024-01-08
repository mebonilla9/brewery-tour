package dev.manuel.brewerytour.application.mapper;

import dev.manuel.brewerytour.application.mapper.base.IBaseMapper;
import dev.manuel.brewerytour.domain.dto.BreweryDto;
import dev.manuel.brewerytour.domain.entity.Brewery;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BreweryMapper extends IBaseMapper {

  Brewery toEntity(BreweryDto dto);

  BreweryDto toDto(Brewery entity);

  List<Brewery> toEntityList(List<BreweryDto> dtoList);

  List<BreweryDto> toDtoList(List<Brewery> entityList);

}
