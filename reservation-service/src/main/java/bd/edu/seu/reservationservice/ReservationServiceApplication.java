package bd.edu.seu.reservationservice;

import bd.edu.seu.reservationservice.Exception.ResourceAlreadyExistException;
import bd.edu.seu.reservationservice.model.RoomType;
import bd.edu.seu.reservationservice.service.ConsoleClint;
import bd.edu.seu.reservationservice.service.DummyData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import static bd.edu.seu.reservationservice.model.RoomType.SUIT;

@SpringBootApplication
public class ReservationServiceApplication {

    public static void main(String[] args) throws ResourceAlreadyExistException {
        ConfigurableApplicationContext run = SpringApplication.run(ReservationServiceApplication.class, args);
//        DummyData bean = run.getBean(DummyData.class);
//        bean.saveData();
        ConsoleClint bean = run.getBean(ConsoleClint.class);
        bean.getRoomByRoomType(SUIT);
        bean.getRoomByDateRange("2019-03-20","2019-04-22");
    }

}
