package user.utilities;

import user.pojos.LoginRequester;
import user.pojos.User;
import user.validation.Validator;

public class UserLoginHandler {

    public static User getUser(LoginRequester requester) {

        return Validator.existsUser(requester);

    }

}
