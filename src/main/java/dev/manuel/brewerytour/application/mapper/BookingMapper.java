package dev.manuel.brewerytour.application.mapper;

import dev.manuel.brewerytour.application.mapper.base.IBaseMapper;
import dev.manuel.brewerytour.domain.dto.BookingDto;
import dev.manuel.brewerytour.domain.entity.Booking;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookingMapper extends IBaseMapper {

  Booking toEntity(BookingDto dto);

  BookingDto toDto(Booking entity);

  List<Booking> toEntityList(List<BookingDto> dtoList);

  List<BookingDto> toDtoList(List<Booking> entityList);

}
