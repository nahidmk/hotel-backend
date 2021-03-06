package bd.edu.seu.reservationservice.service;

import bd.edu.seu.reservationservice.Exception.RecourceNotFoundException;
import bd.edu.seu.reservationservice.Exception.ResourceAlreadyExistException;
import bd.edu.seu.reservationservice.model.Reservation;
import bd.edu.seu.reservationservice.model.Room;
import bd.edu.seu.reservationservice.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReservationService {
    private ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> findAll()
    {
        List<Reservation> rooms = new ArrayList<>();
        reservationRepository.findAll().forEach(rooms::add);
        return rooms;
    }

    public Reservation findById(int id) throws RecourceNotFoundException
    {
        return reservationRepository.findById(id).orElseThrow(RecourceNotFoundException::new);
    }

    public Reservation deleteReservation(int id) throws RecourceNotFoundException
    {
        if (reservationRepository.existsById(id))
        {
            Reservation reservation = reservationRepository.findById(id).get();
            reservationRepository.deleteById(id);
            return reservation;
        }else {
            throw new RecourceNotFoundException();
        }
    }

    public Reservation updateReservation(Reservation reservation) throws RecourceNotFoundException
    {
        if(reservationRepository.existsById(reservation.getId()))
        {
            reservationRepository.save(reservation);
            return reservationRepository.findById(reservation.getId()).get();
        }else {
            throw new RecourceNotFoundException();
        }
    }

    public Reservation CreateReservation(Reservation reservation) throws ResourceAlreadyExistException
    {
        if(reservationRepository.existsById(reservation.getId()))
        {
            throw new ResourceAlreadyExistException();
        }else {
            return reservationRepository.save(reservation);
        }
    }

    public List<Reservation> findByFromDateAndToDateBetween (LocalDate localDate1 , LocalDate localDate2)
    {
        List<Reservation> reservations1 =  reservationRepository.findReservationByFromDateBetween(localDate1,localDate2);
        List<Reservation> reservations2 = reservationRepository.findReservationByToDateBetween(localDate1,localDate2);
        Set<Reservation> reservations = new HashSet<>(reservations1);
        reservations.addAll(reservations2);
        List<Reservation> reservationList = new ArrayList<>(reservations);

        List<Reservation> inReservation = new ArrayList<>();
        reservationRepository.findAll().forEach(inReservation::add);
        inReservation.removeAll(reservationList);
        return inReservation;
    }
    // TODO add necessary methods here
}
