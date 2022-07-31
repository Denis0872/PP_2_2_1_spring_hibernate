package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("Vasya", "Pupkin", "vasya@pupkin.com");
      User user2 = new User("Denis", "Korablyov", "denis@korablev.com");
      User user3 = new User("Don", "Karleone", "don@karlwone.com");
      User user4 = new User("Madam", "Broshkina", "madam@broshkina.com");
      User user5 = new User("James", "Bond", "james@bond.com");

      Car car1 = new Car("BMV", 3);
      Car car2 = new Car("Mazda", 323);
      Car car3 = new Car("Volvo", 740);
      Car car4 = new Car("Moskvich", 412);
      Car car5 = new Car("Audi", 6);

      userService.add(user1.setCar(car1).setUser(user1));
      userService.add(user2.setCar(car2).setUser(user2));
      userService.add(user3.setCar(car3).setUser(user3));
      userService.add(user4.setCar(car4).setUser(user4));
      userService.add(user5.setCar(car5).setUser(user5));

 //     userService.add(new User(user5.getFirstName(), user5.getLastName(), user5.getEmail()).setCar(new Car(car5.getModel(), car5.getSeries())).setUser(user5));
//      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
//      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
//      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));
      //List<User> users = userService.listUsers();
      for (User user : userService.listUsers()) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());

         System.out.println();
      }
      try{
         System.out.println("Car: BMV series3:  " + userService.getUserByCar("BMV", 3));
      }catch (Exception e){
         System.out.println("Car: BMV series3:  Пользователь не найден");
      }
      try {
         System.out.println("Car: BMV series2:  " + userService.getUserByCar("BMV", 2));
      }catch (Exception e){
         System.out.println("Car: BMV series2:  Пользователь не найден");
      }
      context.close();
   }
}
