import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/pbo_tugas";
            String user = "root";
            String password = "";

            return DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
            return null;
        }
    }
}