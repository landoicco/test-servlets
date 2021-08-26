package database.user;

import local.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBC implements UserDAO {

    private static final String SQL_SELECT = "SELECT id_user, username, firstname, lastname, password, age FROM user";
    private static final String SQL_INSERT = "INSERT INTO user (username, firstname, lastname, password, age) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE user SET username=?, firstname=?, lastname=?, password=?, age=? WHERE id_user = ?";
    private static final String SQL_DELETE = "DELETE FROM user WHERE id_user=?";

    @Override
    public List<User> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user;
        List<User> users = new ArrayList<>();

        try {
            conn = ConnectionHandler.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int id_user = rs.getInt("id_user");
                String username = rs.getString("username");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String password = rs.getString("password");
                int age = rs.getInt("age");

                user = new User(id_user, username, firstName, lastName, password, age);

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
    public int insert(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ConnectionHandler.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getAge());

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
    public int update(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConnectionHandler.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getFirstName());
            stmt.setString(3, user.getLastName());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getAge());
            stmt.setInt(6, user.getId_user());

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
    public int delete(User user) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = ConnectionHandler.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
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
