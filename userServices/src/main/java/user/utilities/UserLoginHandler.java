package user.utilities;

import user.database.UserDAO;
import user.database.UserJDBC;
import user.pojos.LoginRequester;
import user.pojos.User;
import user.validation.Validator;

public class UserLoginHandler {

    public static User getUser(LoginRequester requester) {

        return Validator.existsUser(requester);

    }

}
