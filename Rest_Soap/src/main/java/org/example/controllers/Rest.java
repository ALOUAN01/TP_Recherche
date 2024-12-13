package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.entities.Reservation;
import org.example.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class Rest {
    private final ReservationService service;

    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        return ResponseEntity.ok(service.createReservation(
                reservation.getClientName(),
                reservation.getRoomType(),
                reservation.getStartDate(),
                reservation.getEndDate()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
        return ResponseEntity.ok(service.getReservation(id));
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(service.getAllReservations());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return ResponseEntity.ok(service.updateReservation(
                id,
                reservation.getClientName(),
                reservation.getRoomType(),
                reservation.getStartDate(),
                reservation.getEndDate()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        service.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

}
