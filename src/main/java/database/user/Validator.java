package database.user;

import local.user.RegisterRequester;
import local.user.User;

public class Validator {

    static UserDAO dbGate;

    static {
        dbGate = new UserJDBC();
    }

    public static boolean isValidNewUser(RegisterRequester requester) {

        // Verificar que los datos ingresados no sea ninguno nulo
        if ("".equals(requester.getFirstName()) || "".equals(requester.getLastName()) ||
                "".equals(requester.getAge()) || "".equals(requester.getPassword()) ||
                "".equals(requester.getUsername())) {
            throw new IllegalArgumentException("All fields are required");
        }
        if (!isValidAge(requester.getAge())) {
            throw new IllegalArgumentException("Invalid age");
        }
        if (!isValidUsername(requester.getUsername())) {
            throw new IllegalArgumentException("That username already exists");
        }
        return true;
    }

    public static boolean isValidAge(String ageString) {

        int age;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid age", ex);
        }
        return age >= 1 && age <= 99;

    }

    public static boolean isValidUsername(String username) {

        for (User u : dbGate.select()) {
            if (username.equals(u.getUsername())) {
                return false;
            }
        }
        return true;
    }

}
