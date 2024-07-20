package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car carOne = new Car("Honda Torneo", 101);
      Car carTwo = new Car("Honda Accord", 101);
      Car carThree = new Car("Honda Legenda", 101);
      Car carFour = new Car("Honda Civic", 101);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", carOne));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", carTwo));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", carThree));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", carFour));

      User civicUser = userService.getUserByCarParams("Honda Civic", 101);

      System.out.println(civicUser.getFirstName());

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = "+user.getCar().getModel());
         System.out.println("Car series = "+user.getCar().getSeries());
         System.out.println();
      }

      context.close();
   }
}
