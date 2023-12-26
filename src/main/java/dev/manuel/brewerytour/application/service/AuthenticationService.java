package dev.manuel.brewerytour.application.service;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.lasting.EMessage;
import dev.manuel.brewerytour.application.mapper.UserMapper;
import dev.manuel.brewerytour.domain.dto.AuthenticationDto;
import dev.manuel.brewerytour.domain.dto.UserDto;
import dev.manuel.brewerytour.domain.entity.User;
import dev.manuel.brewerytour.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public record AuthenticationService(
  UserRepository userRepository,
  JwtService jwtService,
  PasswordEncoder passwordEncoder,
  UserMapper mapper,
  AuthenticationManager authenticationManager
) {

  public String register(UserDto userDto) throws BreweryTourException {
    try{
      User user = mapper.toEntity(userDto);
      user.setPassword(passwordEncoder.encode(userDto.password()));
      user.setEnable(true);
      userRepository.save(user);
      return jwtService.generateToken(user);
    } catch (DataIntegrityViolationException e){
      throw new BreweryTourException(EMessage.USER_EXISTS);
    }
  }

  public String authenticate(AuthenticationDto authenticationDto) throws BreweryTourException {
    authenticationManager.authenticate(
      new UsernamePasswordAuthenticationToken(
        authenticationDto.email(),
        authenticationDto.password()
      )
    );
    User user = userRepository.findUserByEmail(authenticationDto.email())
      .orElseThrow(() -> new BreweryTourException(EMessage.INVALID_CREDENTIALS));
    return jwtService.generateToken(user);
  }
}
