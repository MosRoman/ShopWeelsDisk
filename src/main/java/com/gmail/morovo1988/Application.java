package com.gmail.morovo1988;

import com.gmail.morovo1988.Entity.User;
import com.gmail.morovo1988.Service.TypeService;
import com.gmail.morovo1988.Service.UserService;
import com.gmail.morovo1988.Entity.Product;
import com.gmail.morovo1988.Entity.Type;
import com.gmail.morovo1988.Entity.UserRole;
import com.gmail.morovo1988.Service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(final UserService userService, final ProductServiceImpl productServiceImpl, TypeService typeService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {


                Type type1 = new Type("Wheel");
                Type type2 = new Type("Disk");
                typeService.addType(type1);
                typeService.addType(type2);

                userService.addUser(new User("admin", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.ADMIN));
                userService.addUser(new User("user", "5baa61e4c9b93f3f0682250b6cf8331b7ee68fd8", UserRole.USER));
                Product product;
                for (int i = 0; i < 15; i++) {
                    product = new Product(type1,"Brand" + i, i, i);

                    productServiceImpl.addProduct(product);
                }
                for (int i = 0; i < 15; i++) {
                    product = new Product(type2,"Brand" + i, i, i);

                    productServiceImpl.addProduct(product);
                }

            }
        };
    }


}