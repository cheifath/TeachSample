import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class VulnerableLogin {

    public static void main(String[] args) {

        String username = "admin";
        String password = "admin123";

        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb",
                "root",
                "password"
            );

            Statement stmt = conn.createStatement();

            // SECURITY ISSUE: SQL Injection
            String query =
                "SELECT * FROM users WHERE username = '" +
                username +
                "' AND password = '" +
                password +
                "'";

            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                System.out.println("Login successful");
            } else {
                System.out.println("Invalid credentials");
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}