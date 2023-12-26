package dev.manuel.brewerytour.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.exception.BreweryTourExceptionHandler;
import dev.manuel.brewerytour.application.lasting.EMessage;
import dev.manuel.brewerytour.application.service.BeerService;
import dev.manuel.brewerytour.domain.dto.BeerDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@WithMockUser
@SpringBootTest
class BeerControllerTest {

  @Autowired
  private BeerController beerController;

  @MockBean
  private BeerService beerService;

  private MockMvc mockMvc;

  @BeforeEach
  public void setup() {
    this.mockMvc = standaloneSetup(beerController)
      .setControllerAdvice(new BreweryTourExceptionHandler())
      .build();
  }

  @Test
  void testRegisterBeer() throws Exception {
    // Given
    BeerDto beerDto = new BeerDto(null, "Poker", 3500.0D, 20, null);

    // When
    this.mockMvc.perform(post("/api/v1/beer")
        .contentType(MediaType.APPLICATION_JSON)
        .content(new ObjectMapper().writeValueAsString(beerDto))
        .accept(MediaType.APPLICATION_JSON))
      .andExpect(status().isCreated());

    // Then
    verify(beerService).registerBeer(beerDto);
  }

  @Test
  void testFindAllBeer() throws Exception {
    // Given
    final Integer offset = 0;
    final Integer limit = 100;

    BeerDto pokerDto = new BeerDto(
      345,
      "Poker",
      3500.0D,
      30,
      null
    );
    BeerDto costenaDto = new BeerDto(
      542,
      "Costena",
      3100.0D,
      30,
      null
    );

    List<BeerDto> beersDto = Arrays.asList(pokerDto, costenaDto);

    // When
    when(beerService.findAllBeer(offset, limit)).thenReturn(beersDto);
    MockHttpServletResponse response = mockMvc.perform(
        get("/api/v1/beer/" + offset + "/" + limit))
      .andExpect(status().isFound())
      .andReturn()
      .getResponse();

    // Then
    verify(beerService).findAllBeer(offset, limit);
    assertThat(response.getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(beersDto));
  }

  @Test
  void testFindAllBeerNotFound() throws Exception {
    // Given
    final Integer offset = 0;
    final Integer limit = 100;

    // When
    when(beerService.findAllBeer(offset, limit))
      .thenThrow(new BreweryTourException(EMessage.DATA_NOT_FOUND));

    BeerController mockedBeerController = mock(BeerController.class);

    doThrow(new BreweryTourException(EMessage.DATA_NOT_FOUND))
      .when(mockedBeerController).findAllBeer(offset, limit);

    // Then
    mockMvc.perform(get("/api/v1/beer/" + offset + "/" + limit))
      .andExpect(status().isNotFound())
      .andExpect(result -> assertInstanceOf(BreweryTourException.class, result.getResolvedException()))
      .andExpect(result -> assertEquals(
        EMessage.DATA_NOT_FOUND.getMessage(), Objects.requireNonNull(result.getResolvedException()).getMessage()
      ));
  }

  @Test
  @SneakyThrows
  void testFindBeerById() {
    // Given
    final Integer id = 345;

    BeerDto pokerDto = new BeerDto(
      345,
      "Poker",
      3500.0D,
      30,
      null
    );

    // When
    when(beerService.findBeerById(id)).thenReturn(pokerDto);
    MockHttpServletResponse response = mockMvc.perform(get("/api/v1/beer/" + id))
      .andExpect(status().isFound())
      .andReturn()
      .getResponse();

    // Then
    verify(beerService).findBeerById(id);
    assertThat(response.getContentAsString()).isEqualTo(new ObjectMapper().writeValueAsString(pokerDto));
  }

  @Test
  void testFindBeerByIdNotFound() throws Exception {
    // Given
    final Integer id = 345;

    // When
    when(beerService.findBeerById(id)).thenThrow(new BreweryTourException(EMessage.DATA_NOT_FOUND));

    BeerController mockedBeerController = mock(BeerController.class);

    doThrow(new BreweryTourException(EMessage.DATA_NOT_FOUND))
      .when(mockedBeerController).findBeerById(id);

    // Then
    mockMvc.perform(get("/api/v1/beer/" + id))
      .andExpect(status().isNotFound())
      .andExpect(result -> assertInstanceOf(BreweryTourException.class, result.getResolvedException()))
      .andExpect(result -> assertEquals(
        EMessage.DATA_NOT_FOUND.getMessage(), Objects.requireNonNull(result.getResolvedException()).getMessage()
      ));
  }

}