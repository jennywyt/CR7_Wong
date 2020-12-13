import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class StudentRepo {
    private String url;

    public StudentRepo(String url) {
        this.url = url;
    }

    public void displayAllStudents() {

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

    public void createAllStudentsReport(File outputFilePath) {

        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");

        try (Connection connection = DriverManager.getConnection(url, properties)) {

            String sql = "SELECT NAME, Surname,Email,Class_ID  FROM student";
            PreparedStatement statement = connection.prepareStatement(sql);
            try (ResultSet resultSet = statement.executeQuery();
                 FileWriter writer = new FileWriter(outputFilePath)) {
                while (resultSet.next()) {
                    String Name = resultSet.getString("NAME");
                    String Surname = resultSet.getString("Surname");
                    String Email = resultSet.getString("Email");
                    String Class_ID = resultSet.getString("Class_ID");
                    writer.write(Name + "||" + Surname + "||" + Email + "||" + Class_ID + '\n');
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
