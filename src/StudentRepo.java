import java.sql.*;
import java.util.Properties;

public class StudentRepo {

    public void displayAllStudents() {
        String url = "jdbc:mysql://localhost:3306/cr7_wong";

        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");

        try (Connection connection = DriverManager.getConnection(url, properties)) {

            String sql = "SELECT NAME, Surname FROM student";
            PreparedStatement statement = connection.prepareStatement(sql);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String Name = resultSet.getString("NAME");
                    String Surname = resultSet.getString("Surname");
                    System.out.println(Name + " " + Surname);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

}
