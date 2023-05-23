package dat3.grocery_delivery.configuration;

import dat3.grocery_delivery.entity.Product;
import dat3.grocery_delivery.entity.Van;
import dat3.grocery_delivery.repository.ProductRepository;
import dat3.grocery_delivery.repository.VanRepository;
import dat3.security.entity.Role;
import dat3.security.entity.UserWithRoles;
import dat3.security.repository.UserWithRolesRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.Arrays;
import java.util.List;

@Controller
public class SetupDevelopmentData implements ApplicationRunner {

  UserWithRolesRepository userWithRolesRepository;
  ProductRepository productRepository;
  VanRepository vanRepository;
  String passwordUsedByAll;

  @Value("${spring.jpa.hibernate.ddl-auto}")
  private String dllChoice;



  public SetupDevelopmentData(UserWithRolesRepository userWithRolesRepository,
                              ProductRepository productRepository,
                              VanRepository vanRepository,
                              PasswordEncoder passwordEncoder) {
    this.userWithRolesRepository = userWithRolesRepository;
    this.productRepository = productRepository;
    this.vanRepository = vanRepository;
    passwordUsedByAll = passwordEncoder.encode("test12");
  }

  public void setUpDataForExamProject() {

    List<Product> products = Arrays.asList(
            Product.builder().name("Havregryn").price(25).weight(1000).build(),
            Product.builder().name("IPA").price(14.57).weight(500).build(),
            Product.builder().name("Æg").price(18.45).weight(450).build(),
            Product.builder().name("Mælk").price(11.45).weight(1000).build(),
            Product.builder().name("Yougurt").price(19.75).weight(100).build(),
            Product.builder().name("Ost").price(48.00).weight(700).build()
    );
    productRepository.saveAll(products);

    List<Van> vans = Arrays.asList(
            Van.builder().brand("Ford").model("Transit Van").capacity(2250).build(),
            Van.builder().brand("Ford").model("Transit Connect").capacity(982).build(),
            Van.builder().brand("Mercedes").model("Mercedes Citan").capacity(782).build(),
            Van.builder().brand("Mercedes").model("Mercedes Vito").capacity(1198).build()
    );
    vanRepository.saveAll(vans);

  }

  @Override
  public void run(ApplicationArguments args) {
    //setUpDataForExamProject();
    //setupUserWithRoleUsers();
  }

  /*****************************************************************************************
   NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL
   iT'S ONE OF THE TOP SECURITY FLAWS YOU CAN DO
   *****************************************************************************************/
  private void setupUserWithRoleUsers() {
    System.out.println("******************************************************************************");
    System.out.println("******* NEVER  COMMIT/PUSH CODE WITH DEFAULT CREDENTIALS FOR REAL ************");
    System.out.println("******* REMOVE THIS BEFORE DEPLOYMENT, AND SETUP DEFAULT USERS DIRECTLY  *****");
    System.out.println("**** ** ON YOUR REMOTE DATABASE                 ******************************");
    System.out.println("******************************************************************************");
    UserWithRoles user1 = new UserWithRoles("user1", passwordUsedByAll, "user1@a.dk");
    UserWithRoles user2 = new UserWithRoles("user2", passwordUsedByAll, "user2@a.dk");
    UserWithRoles user3 = new UserWithRoles("user3", passwordUsedByAll, "user3@a.dk");
    UserWithRoles user4 = new UserWithRoles("user4", passwordUsedByAll, "user4@a.dk");
    user1.addRole(Role.USER);
    user1.addRole(Role.ADMIN);
    user2.addRole(Role.USER);
    user3.addRole(Role.ADMIN);
    //No Role assigned to user4
    userWithRolesRepository.save(user1);
    userWithRolesRepository.save(user2);
    userWithRolesRepository.save(user3);
    userWithRolesRepository.save(user4);
  }
}
