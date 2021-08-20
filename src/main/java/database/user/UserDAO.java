package database.user;

import local.user.User;

import java.util.List;

public interface UserDAO {

    List<User> select();

    int insert(User user);

    int update(User user);

    int delete(User user);

}
