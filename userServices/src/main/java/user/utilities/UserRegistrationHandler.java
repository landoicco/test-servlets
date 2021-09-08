package user.utilities;

import user.database.UserDAO;
import user.database.UserJDBC;
import user.pojos.RegisterRequester;
import user.pojos.User;
import user.validation.Validator;

public class UserRegistrationHandler {

    private static final UserDAO dbGate;

    static {
        dbGate = new UserJDBC();
    }

    public static User getRegisteredUser(RegisterRequester requester) {
        boolean isValid = Validator.isValidNewUser(requester);
        if (isValid) {
            User user = convertInUser(requester);
            dbGate.insert(user);

            // Return user with id_key from DB
            return getUserByUsername(user.getUsername());
        }
        return null;
    }

    public static User getUpdatedUser(User oldUser, RegisterRequester updatedInfo) {

        boolean usernameWasUpdated = !oldUser.getUsername().equals(updatedInfo.getUsername());
        boolean isValidUpdate = Validator.isValidNewUser(updatedInfo, usernameWasUpdated);

        if (isValidUpdate) {
            User updatedUser = convertInUser(updatedInfo, oldUser.getId_user());

            dbGate.update(updatedUser);

            return updatedUser;
        }
        return null;
    }

    public static void deleteUserRegister(User user) {
        dbGate.delete(user);
    }

    private static User convertInUser(RegisterRequester requester) {
        return new User(requester.getUsername(), requester.getFirstName(), requester.getLastName(),
                requester.getPassword(), Integer.parseInt(requester.getAge()));
    }

    private static User convertInUser(RegisterRequester requester, int idUser) {
        return new User(idUser, requester.getUsername(), requester.getFirstName(), requester.getLastName(),
                requester.getPassword(), Integer.parseInt(requester.getAge()));
    }

    private static User getUserByUsername(String username) {
        for (User u : dbGate.select()) {
            if (u.getUsername().equals(username)) {
                return u;
            }
        }
        return null;
    }

}
