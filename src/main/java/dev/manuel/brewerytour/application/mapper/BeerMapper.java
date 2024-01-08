package dev.manuel.brewerytour.application.mapper;

import dev.manuel.brewerytour.application.mapper.base.IBaseMapper;
import dev.manuel.brewerytour.domain.dto.BeerDto;
import dev.manuel.brewerytour.domain.entity.Beer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BeerMapper extends IBaseMapper {

  Beer toEntity(BeerDto dto);

  BeerDto toDto(Beer entity);

  List<Beer> toEntityList(List<BeerDto> dtoList);

  List<BeerDto> toDtoList(List<Beer> entityList);
}
