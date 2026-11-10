package utility;

import java.time.LocalDate;
import model.User;

/**
 * Checks a user's data before it is saved. Returns an error message, or null if valid.
 */
public class InputValidator {

    public static String validate(User u) {
        if (u.getFirstName().isEmpty() || u.getLastName().isEmpty()) {
            return "First name and last name are required.";
        }
        if (!u.getFirstName().matches("[A-Za-z ]+") || !u.getLastName().matches("[A-Za-z ]+")) {
            return "Names can only contain letters and spaces.";
        }
        if (u.getDob() == null || u.getDob().isAfter(LocalDate.now())) {
            return "Date of birth cannot be in the future.";
        }
        if (u.getAge() < 1 || u.getAge() > 120) {
            return "Age must be between 1 and 120.";
        }
        if (!u.getPhone().matches("\\d{3}-\\d{3}-\\d{4}")) {
            return "Phone number must look like 617-555-0123.";
        }
        if (!u.getEmail().matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$")) {
            return "Please enter a valid email address.";
        }
        if (u.getContinent() == null) {
            return "Please select a continent.";
        }
        return null;
    }
}
