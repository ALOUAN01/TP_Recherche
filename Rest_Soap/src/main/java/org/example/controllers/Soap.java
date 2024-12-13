package org.example.controllers;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import org.example.entities.Reservation;
import org.example.service.ReservationService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@WebService(serviceName = "ReservationWS")
public class Soap {

    private final ReservationService reservationService;

    public Soap(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // Retrieve all reservations
    @WebMethod
    public List<Reservation> getReservations() {
        return reservationService.getAllReservations();
    }

    // Retrieve a reservation by ID
    @WebMethod
    public Reservation getReservationById(@WebParam(name = "id") Long id) {
        return reservationService.getReservation(id);
    }

    // Create a new reservation
    @WebMethod
    public Reservation createReservation(
            @WebParam(name = "clientName") String clientName,
            @WebParam(name = "roomType") String roomType,
            @WebParam(name = "startDate") String startDate,
            @WebParam(name = "endDate") String endDate) {

        return reservationService.createReservation(
                clientName,
                roomType,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate));
    }

    // Update an existing reservation
    @WebMethod
    public Reservation updateReservation(
            @WebParam(name = "id") Long id,
            @WebParam(name = "clientName") String clientName,
            @WebParam(name = "roomType") String roomType,
            @WebParam(name = "startDate") String startDate,
            @WebParam(name = "endDate") String endDate) {

        return reservationService.updateReservation(
                id,
                clientName,
                roomType,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate));
    }

    // Delete a reservation by ID
    @WebMethod
    public boolean deleteReservation(@WebParam(name = "id") Long id) {
        reservationService.deleteReservation(id);
        return true;
    }
}
