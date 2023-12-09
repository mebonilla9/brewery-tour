package dev.manuel.brewerytour.application.service;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.lasting.EMessage;
import dev.manuel.brewerytour.application.mapper.UserMapper;
import dev.manuel.brewerytour.domain.dto.UserDto;
import dev.manuel.brewerytour.domain.entity.User;
import dev.manuel.brewerytour.domain.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record UserService(
  UserRepository userRepository,
  UserMapper mapper
) {

  public void registerUser(UserDto userDto) {
    User user = mapper.toEntity(userDto);
    userRepository.save(user);
  }

  public List<UserDto> findAllUser(Integer offset, Integer limit) throws BreweryTourException {
    Pageable pageable = PageRequest.of(offset, limit);
    Page<User> breweries = userRepository.findAll(pageable);
    if (breweries.getContent().isEmpty()) {
      throw new BreweryTourException(EMessage.DATA_NOT_FOUND);
    }
    return mapper.toDtoList(breweries.getContent());
  }

  public UserDto findUserById(Integer id) throws BreweryTourException {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    return mapper.toDto(user);
  }

  public void editUser(Integer id, UserDto userDto) throws BreweryTourException {
    userRepository.findById(id)
      .orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    User user = mapper.toEntity(userDto);
    userRepository.save(user);
  }

  public void removeUser(Integer id) throws BreweryTourException {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    userRepository.delete(user);
  }

}
