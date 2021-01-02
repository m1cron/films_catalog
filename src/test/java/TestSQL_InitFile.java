import org.junit.Test;

import java.sql.*;

public class TestSQL_InitFile {
    @Test
    public void TestSQL_InitFile() {
        String url = "jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'classpath:init.sql';DB_CLOSE_DELAY=-1;";
        String sql = "SELECT * FROM films";
        System.out.println("Connecting...");
        try (Connection connection = DriverManager.getConnection(url);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Connection successful!");
            while (rs.next()) {
                System.out.printf("%d\t%s\t%d\t%s\t%b\n",
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("year"),
                        rs.getString("genre"),
                        rs.getBoolean("watched"));
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!" + e);
        }
    }
}
