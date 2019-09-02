import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db?serverTimezone=EET", "root", "mysql1220");

        Engine engine = new Engine(connection);
        engine.run();
    }
}
