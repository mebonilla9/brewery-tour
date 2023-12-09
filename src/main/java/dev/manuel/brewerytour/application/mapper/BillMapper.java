package dev.manuel.brewerytour.application.mapper;

import dev.manuel.brewerytour.application.mapper.base.IBaseMapper;
import dev.manuel.brewerytour.domain.dto.BillDto;
import dev.manuel.brewerytour.domain.entity.Bill;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapper extends IBaseMapper {

  Bill toEntity(BillDto dto);

  BillDto toDto(Bill entity);

  List<Bill> toEntityList(List<BillDto> dtoList);

  List<BillDto> toDtoList(List<Bill> entityList);

}
