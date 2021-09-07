package user.database;

import user.pojos.User;

import java.util.List;

public interface UserDAO {

    List<User> select();

    int insert(User user);

    int update(User user);

    int delete(User user);

}
