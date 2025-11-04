package institute.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {

    Connection connection;
    Statement statement;

    Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Correct JDBC URL
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/universitymanagement",
                    "root",
                    "Saikat12345"
            );
            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
