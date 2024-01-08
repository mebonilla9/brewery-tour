package dev.manuel.brewerytour.application.service;

import dev.manuel.brewerytour.application.exception.BreweryTourException;
import dev.manuel.brewerytour.application.lasting.EMessage;
import dev.manuel.brewerytour.application.mapper.BookingMapper;
import dev.manuel.brewerytour.domain.dto.BookingDto;
import dev.manuel.brewerytour.domain.entity.Booking;
import dev.manuel.brewerytour.domain.repository.BookingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record BookingService(BookingRepository bookingRepository, BookingMapper mapper) {

  public void registerBooking(BookingDto bookingDto) {
    Booking booking = mapper.toEntity(bookingDto);
    bookingRepository.save(booking);
  }

  public List<BookingDto> findAllBooking(Integer offset, Integer limit) throws BreweryTourException {
    Pageable pageable = PageRequest.of(offset, limit);
    Page<Booking> breweries = bookingRepository.findAll(pageable);
    if (breweries.getContent().isEmpty()) {
      throw new BreweryTourException(EMessage.DATA_NOT_FOUND);
    }
    return mapper.toDtoList(breweries.getContent());
  }

  public BookingDto findBookingById(Integer id) throws BreweryTourException {
    Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    return mapper.toDto(booking);
  }

  public void editBooking(Integer id, BookingDto bookingDto) throws BreweryTourException {
    bookingRepository.findById(id).orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    Booking booking = mapper.toEntity(bookingDto);
    bookingRepository.save(booking);
  }

  public void removeBooking(Integer id) throws BreweryTourException {
    Booking booking = bookingRepository.findById(id).orElseThrow(() -> new BreweryTourException(EMessage.DATA_NOT_FOUND));
    bookingRepository.delete(booking);
  }

}
