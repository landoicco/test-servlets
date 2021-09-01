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

        return isValidNewUser(requester, true);

    }

    public static boolean isValidNewUser(RegisterRequester updatedData, boolean usernameWasUpdated) {

        if (someEmptyFields(updatedData)) {
            throw new IllegalArgumentException("All fields are required");
        }
        if (invalidAge(updatedData.getAge())) {
            throw new IllegalArgumentException("Invalid age");
        }
        if (usernameWasUpdated && usernameAlreadyExists(updatedData.getUsername())) {
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

    private static boolean usernameAlreadyExists(String username) {

        for (User u : dbGate.select()) {
            if (username.equals(u.getUsername())) {
                return true;
            }
        }
        return false;
    }

    private static boolean someEmptyFields(RegisterRequester requester) {

        return "".equals(requester.getFirstName()) || "".equals(requester.getLastName()) ||
                "".equals(requester.getAge()) || "".equals(requester.getPassword()) ||
                "".equals(requester.getUsername());

    }

}
