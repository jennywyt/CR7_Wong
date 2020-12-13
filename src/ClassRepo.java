import java.sql.*;
import java.util.Properties;

public class ClassRepo {
    public void displayAllClass() {

        String url = "jdbc:mysql://localhost:3306/cr7_wong";

        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");

        try (Connection connection = DriverManager.getConnection(url, properties)) {

            String sql = "SELECT Class_ID, Class_NAME FROM Classes";
            PreparedStatement statement = connection.prepareStatement(sql);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String Class_Name = resultSet.getString("Class_NAME");
                    int Class_ID = resultSet.getInt("Class_ID");
                    System.out.println(Class_ID + " " + Class_Name );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }
}
