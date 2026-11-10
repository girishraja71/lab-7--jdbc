package utility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.User;

/**
 * All JDBC communication with the MySQL database (Create, Read, Update, Delete).
 */
public class DatabaseConnector {

    private static final String URL = "jdbc:mysql://localhost:3306/lab7db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "my-secret-pw";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    // CREATE
    public static void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (first_name, last_name, gender, dob, age, phone, email, "
                + "continent, experience, hobbies, photo_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            fillStatement(stmt, user);
            stmt.executeUpdate();
        }
    }

    // READ
    public static ArrayList<User> getAllUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users ORDER BY id";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt("id"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setGender(rs.getString("gender"));
                u.setDob(rs.getDate("dob").toLocalDate());
                u.setAge(rs.getInt("age"));
                u.setPhone(rs.getString("phone"));
                u.setEmail(rs.getString("email"));
                u.setContinent(rs.getString("continent"));
                u.setExperience(rs.getString("experience"));
                u.setHobbies(rs.getString("hobbies"));
                u.setPhotoPath(rs.getString("photo_path"));
                users.add(u);
            }
        }
        return users;
    }

    // UPDATE
    public static void editUser(User user) throws SQLException {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, gender = ?, dob = ?, age = ?, phone = ?, "
                + "email = ?, continent = ?, experience = ?, hobbies = ?, photo_path = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            fillStatement(stmt, user);
            stmt.setInt(12, user.getId());
            stmt.executeUpdate();
        }
    }

    // DELETE
    public static void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    /** MySQL error 1062 = a UNIQUE column (here: email) already has this value. */
    public static boolean isDuplicateEmail(SQLException ex) {
        return ex.getErrorCode() == 1062;
    }

    // Shared by INSERT and UPDATE: puts the user's values into placeholders 1-11
    private static void fillStatement(PreparedStatement stmt, User user) throws SQLException {
        stmt.setString(1, user.getFirstName());
        stmt.setString(2, user.getLastName());
        stmt.setString(3, user.getGender());
        stmt.setDate(4, Date.valueOf(user.getDob()));
        stmt.setInt(5, user.getAge());
        stmt.setString(6, user.getPhone());
        stmt.setString(7, user.getEmail());
        stmt.setString(8, user.getContinent());
        stmt.setString(9, user.getExperience());
        stmt.setString(10, user.getHobbies());
        stmt.setString(11, user.getPhotoPath());
    }
}
