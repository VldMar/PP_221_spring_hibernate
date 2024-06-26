package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class); // получаем чисто для теста

      User user1 = new User("User1", "LastName1", "user1@mail.ru");
      User user2 = new User("User2", "LastName2", "user2@mail.ru");
      User user3 = new User("User3", "LastName3", "user3@mail.ru");
      User user4 = new User("User4", "LastName4", "user4@mail.ru");
      Car car1 = new Car("car1", 1);
      Car car2 = new Car("car2", 2);
      Car car3 = new Car("car3", 3);
      Car car4 = new Car("car4", 4);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.addUser(user1);
      userService.addUser(user2);
      userService.addUser(user3);
      userService.addUser(user4);

      List<User> users = userService.getAllUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      User user = userService.getUserByCar("car2", 2);
      System.out.println(user);

      context.close();
   }
}
