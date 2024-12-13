package org.example.service;

import org.example.entities.Reservation;
import org.example.repositories.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }

    public Reservation getReservation(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found for id: " + id));
    }

    public Reservation createReservation(String clientName, String roomType, LocalDate startDate, LocalDate endDate) {
        Reservation reservation = new Reservation();
        reservation.setClientName(clientName);
        reservation.setRoomType(roomType);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        return repository.save(reservation);
    }

    public Reservation updateReservation(Long id, String clientName, String roomType, LocalDate startDate, LocalDate endDate) {
        Reservation reservation = getReservation(id);
        if (clientName != null) reservation.setClientName(clientName);
        if (roomType != null) reservation.setRoomType(roomType);
        if (startDate != null) reservation.setStartDate(startDate);
        if (endDate != null) reservation.setEndDate(endDate);
        return repository.save(reservation);
    }

    public void deleteReservation(Long id) {
        repository.deleteById(id);
    }
}
