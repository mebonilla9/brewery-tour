package dev.manuel.brewerytour.application.mapper;

import dev.manuel.brewerytour.application.mapper.base.IBaseMapper;
import dev.manuel.brewerytour.domain.dto.UserDto;
import dev.manuel.brewerytour.domain.entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends IBaseMapper {

  User toEntity(UserDto dto);

  UserDto toDto(User entity);

  List<User> toEntityList(List<UserDto> dtoList);

  List<UserDto> toDtoList(List<User> entityList);

}
