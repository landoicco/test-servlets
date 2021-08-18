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
    public List<UserDTO> select() {
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

                user = new UserDTO(id_user, firstName, lastName, password, age);

                users.add(user);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();

        } finally {
            ConnectionHandler.close(rs);
            ConnectionHandler.close(stmt);
            ConnectionHandler.close(conn);
        }
        return users;
    }

    @Override
    public int insert(UserDTO user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConnectionHandler.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getPassword());
            stmt.setInt(4, user.getAge());

            // Count of modified rows
            rows = stmt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionHandler.close(stmt);
            ConnectionHandler.close(conn);
        }
        return rows;
    }

    @Override
    public int update(UserDTO newUser, UserDTO oldUser) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConnectionHandler.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, newUser.getFirstName());
            stmt.setString(2, newUser.getLastName());
            stmt.setString(3, newUser.getPassword());
            stmt.setInt(4, newUser.getAge());
            stmt.setInt(5, oldUser.getId_user());

            // Count of modified rows
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionHandler.close(stmt);
            ConnectionHandler.close(conn);
        }
        return rows;
    }

    @Override
    public int delete(UserDTO user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConnectionHandler.getConnection();
            stmt.executeQuery(SQL_DELETE);
            stmt.setInt(1, user.getId_user());

            // Count of modified rows
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            ConnectionHandler.close(stmt);
            ConnectionHandler.close(conn);
        }
        return rows;
    }
}
