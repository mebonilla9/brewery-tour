package dev.manuel.brewerytour.application.mapper.base;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IBaseMapper {
  IBaseMapper INSTANCE = Mappers.getMapper(IBaseMapper.class);
}
