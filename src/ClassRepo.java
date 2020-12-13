import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class ClassRepo {
    private final String url;

    public ClassRepo(String url) {
        this.url = url;
    }

    public void displayAllClass() {

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
                    System.out.println(Class_ID + " " + Class_Name);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public void createStudentPerClassReport(File outputFileName) {
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");

        File outputFilePath;
        try (Connection connection = DriverManager.getConnection(url, properties)) {

            String sql = "SELECT\n" +
                    "    classes.Class_Name,\n" +
                    "    COUNT(student.Student_ID) as student_count\n" +
                    "FROM\n" +
                    "    classes\n" +
                    "LEFT JOIN student ON student.Class_ID = classes.Class_ID\n" +
                    "GROUP BY 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            try (ResultSet resultSet = statement.executeQuery();
                 FileWriter writer = new FileWriter(outputFileName)) {
                while (resultSet.next()) {
                    String Class_Name = resultSet.getString("Class_Name");
                    int studentCount = resultSet.getInt("student_count");
                    writer.write(Class_Name + "|" + studentCount + '\n');
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
