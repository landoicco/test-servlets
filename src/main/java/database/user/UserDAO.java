package database.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<UserDTO> select();

    int insert(UserDTO user);

    int update(UserDTO newUser, UserDTO oldUser);

    int delete(UserDTO user);

}
