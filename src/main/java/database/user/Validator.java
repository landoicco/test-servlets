package database.user;

import local.user.LoginRequester;
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
        if (invalidAge(requester.getAge())) {
            throw new IllegalArgumentException("Invalid age");
        }
        if (existingUsername(requester.getUsername())) {
            throw new IllegalArgumentException("That username already exists");
        }
        return true;
    }

    public static boolean isValidDataUpdate(RegisterRequester updatedData) {

        //Verificar que al menos hay un valor por actualizar
        if ("".equals(updatedData.getFirstName()) && "".equals(updatedData.getLastName()) &&
                "".equals(updatedData.getPassword()) && "".equals(updatedData.getAge()) &&
                "".equals(updatedData.getUsername())) {
            throw new IllegalArgumentException("No data to update");
        }
        if (!("".equals(updatedData.getAge())) && invalidAge(updatedData.getAge())) {
            throw new IllegalArgumentException("Invalid age");
        }
        if (existingUsername(updatedData.getUsername())) {
            throw new IllegalArgumentException("That username already exists");
        }
        return true;

    }

    public static boolean existsUser(LoginRequester requester) {

        if ("".equals(requester.getUsername()) || "".equals(requester.getPassword())) {
            throw new IllegalArgumentException("Both fields are required");
        }

        for (User u : dbGate.select()) {
            if (u.getUsername().equals(requester.getUsername()) &&
                    u.getPassword().equals(requester.getPassword())) {
                return true;
            } else if (u.getUsername().equals(requester.getUsername()) &&
                    !u.getPassword().equals(requester.getPassword())) {
                throw new IllegalArgumentException("Wrong password");
            }
        }
        throw new IllegalArgumentException("That user doesn't exists");
    }

    private static boolean invalidAge(String ageString) {

        int age;
        try {
            age = Integer.parseInt(ageString);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid age", ex);
        }
        return age < 1 || age > 99;

    }

    private static boolean existingUsername(String username) {

        for (User u : dbGate.select()) {
            if (username.equals(u.getUsername())) {
                return true;
            }
        }
        return false;
    }

}
