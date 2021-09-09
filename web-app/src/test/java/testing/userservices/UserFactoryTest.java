package testing.userservices;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import user.pojos.User;

public class UserFactoryTest {

    private static User user;

    @BeforeAll
    static void createSimpleUser(){
        user = User.createEmptyUser();
    }

    @AfterEach
    void showUserInfo(){
        System.out.println("================");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Nombre: " + user.getFirstName());
        System.out.println("Apellido: " + user.getLastName());
    }

    @Test
    void setRandomUser1(){
        user = new User("juanito.perez", "Juan", "Perez", "admin", 40);
    }

    @Test
    void setRandomUser2(){
        user = new User("fer.juarez", "Fernanda", "Juarez", "admin", 40);
    }

    @Test
    void setRandomUser3(){
        user = new User("andres.torres", "Andres", "Torres", "admin", 40);
    }

}
