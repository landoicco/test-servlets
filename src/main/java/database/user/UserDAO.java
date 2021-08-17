package database.user;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    List<UserDTO> select() throws SQLException;

    int insert(UserDTO user) throws SQLException;

    int update(UserDTO user) throws SQLException;

    int delete(UserDTO user) throws SQLException;

}
