package database.user;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBC implements UserDAO {

    private static final String SQL_SELECT = "SELECT id_user, firstname, lastname, password, age FROM user";
    private static final String SQL_INSERT = "INSERT INTO user (firstname, lastname, password, age) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE user SET firstname=?, lastname=?, password=?, age=? WHERE id_user = ?";
    private static final String SQL_DELETE = "DELETE FROM user WHERE id_user=?";

    @Override
    public List<UserDTO> select() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UserDTO user;
        List<UserDTO> users = new ArrayList<>();

        try {
            conn = ConnectionHandler.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id_user = rs.getInt("id_user");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String password = rs.getString("password");
                int age = rs.getInt("age");

                user = new UserDTO(firstName, lastName, password, age);

                users.add(user);
            }

        } finally {
            ConnectionHandler.close(rs);
            ConnectionHandler.close(stmt);
            ConnectionHandler.close(conn);
        }
        return users;
    }

    @Override
    public int insert(UserDTO user) throws SQLException {
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        int rows = 0;
//        try {
//            conn = ConnectionHandler.getConnection();
//            stmt
//        }
        return 0;
    }

    @Override
    public int update(UserDTO user) throws SQLException {
        return 0;
    }

    @Override
    public int delete(UserDTO user) throws SQLException {
        return 0;
    }
}
