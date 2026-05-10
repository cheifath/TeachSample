import java.io.*;
import java.sql.*;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class VulnerableApp {

    // HARD CODED CREDENTIALS
    static final String DB_USER = "admin";
    static final String DB_PASS = "password123";

    public static void main(String[] args) {

        try {

            // SQL INJECTION
            String username = args[0];
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/testdb",
                    DB_USER,
                    DB_PASS
            );

            Statement stmt = conn.createStatement();

            String query = "SELECT * FROM users WHERE username = '"
                    + username + "'";

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println("User Found: " + rs.getString("username"));
            }

            // COMMAND INJECTION
            String cmd = args[1];
            Runtime.getRuntime().exec("cmd /c " + cmd);

            // PATH TRAVERSAL
            String filename = args[2];
            File file = new File("uploads/" + filename);

            BufferedReader reader = new BufferedReader(
                    new FileReader(file)
            );

            System.out.println(reader.readLine());

            // INSECURE DESERIALIZATION
            FileInputStream fis = new FileInputStream("data.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Object obj = ois.readObject();

            System.out.println(obj);

            // WEAK ENCRYPTION (ECB MODE)
            String secret = "SensitiveData";

            SecretKeySpec key = new SecretKeySpec(
                    "1234567812345678".getBytes(),
                    "AES"
            );

            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encrypted = cipher.doFinal(secret.getBytes());

            System.out.println(
                    Base64.getEncoder().encodeToString(encrypted)
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}