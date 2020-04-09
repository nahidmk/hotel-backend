package bd.edu.seu.reservationservice.service;

import bd.edu.seu.reservationservice.model.Reservation;
import bd.edu.seu.reservationservice.model.Room;
import bd.edu.seu.reservationservice.model.RoomType;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Service
public class ConsoleClint {
   private RestTemplate template = new RestTemplate();

    public void getRoomByRoomType(RoomType type)
    {
         String BASE_URL = "https://student-crud-api-aj.herokuapp.com/room/room-type/";
        ResponseEntity<Room[]> rooms = template.getForEntity(BASE_URL+"/"+type,Room[].class);
        Room[] rooms1 = rooms.getBody();
//        ResponseEntity<List<Room>> responseEntity = template
//                .exchange(BASE_URL + "/" + type, HttpMethod
//                        .GET,
//                        null,
//                        new ParameterizedTypeReference<List<Room>>() {});
        System.out.println("Rooms are available by type : ");
        for(Room i: rooms1)
        {
            System.out.println(i);
        }
    }

    public void getRoomByDateRange(String fromDate, String toDate)
    {
        String BASE_URL = "https://student-crud-api-aj.herokuapp.com/reservation/between";
        ResponseEntity<Reservation[]> responseEntity = template.getForEntity(BASE_URL+"/"+fromDate+"/to/"+toDate,Reservation[].class);
        Reservation[] reservations = responseEntity.getBody();

        System.out.println(" Rooms are available by reservation : ");
        for(Reservation i : reservations)
        {
            System.out.println(i);
        }
    }
}
