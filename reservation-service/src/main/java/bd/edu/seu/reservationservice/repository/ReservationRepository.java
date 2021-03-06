package bd.edu.seu.reservationservice.repository;

import bd.edu.seu.reservationservice.model.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends CrudRepository<Reservation, Integer> {
    public List<Reservation> findReservationByFromDateBetween(LocalDate fromDate,
                                                              LocalDate toDate);
    public List<Reservation> findReservationByToDateBetween(LocalDate fromDate,LocalDate toDate);
}
