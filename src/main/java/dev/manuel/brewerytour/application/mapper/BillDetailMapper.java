package dev.manuel.brewerytour.application.mapper;

import dev.manuel.brewerytour.application.mapper.base.IBaseMapper;
import dev.manuel.brewerytour.domain.dto.BillDetailDto;
import dev.manuel.brewerytour.domain.entity.BillDetail;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillDetailMapper extends IBaseMapper {

  BillDetail toEntity(BillDetailDto dto);

  BillDetailDto toDto(BillDetail entity);

  List<BillDetail> toEntityList(List<BillDetailDto> dtoList);

  List<BillDetailDto> toDtoList(List<BillDetail> entityList);
}
