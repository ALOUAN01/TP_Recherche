package org.example.services;

import jakarta.persistence.EntityNotFoundException;
import org.example.entitys.Chambre;
import org.example.entitys.Reservation;
import org.example.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ChambreService chambreService;

    public Reservation createReservation(Reservation reservation) {
        if (reservation.getChambre() == null) {
            throw new IllegalArgumentException("Room cannot be null");
        }
        Chambre chambre = chambreService.getChambreById(reservation.getChambre().getId());
        if (!chambre.isDisponible()) {
            throw new IllegalStateException("Room is not available");
        }
        chambre.setDisponible(false); // Mark the room as booked
        chambreService.updateChambre(chambre.getId(), chambre);
        return reservationRepository.save(reservation);
    }
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));
    }
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Reservation reservation = getReservationById(id);
        if (!reservation.getChambre().getId().equals(updatedReservation.getChambre().getId())) {
            Chambre oldChambre = chambreService.getChambreById(reservation.getChambre().getId());
            oldChambre.setDisponible(true);
            chambreService.updateChambre(oldChambre.getId(), oldChambre);
            Chambre newChambre = chambreService.getChambreById(updatedReservation.getChambre().getId());
            if (!newChambre.isDisponible()) {
                throw new IllegalStateException("New Room is not available");
            }
            newChambre.setDisponible(false);
            chambreService.updateChambre(newChambre.getId(), newChambre);
            reservation.setChambre(newChambre);
        }
        reservation.setClient(updatedReservation.getClient());
        reservation.setDateDebut(updatedReservation.getDateDebut());
        reservation.setDateFin(updatedReservation.getDateFin());
        reservation.setPreferences(updatedReservation.getPreferences());
        return reservationRepository.save(reservation);
    }
    public void deleteReservation(Long id) {
        Reservation reservation = getReservationById(id);
        Chambre chambre = chambreService.getChambreById(reservation.getChambre().getId());
        chambre.setDisponible(true);
        chambreService.updateChambre(chambre.getId(), chambre);
        reservationRepository.deleteById(id);
    }
}
