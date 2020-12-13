import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class TeacherRepo {
    private String url;

    public TeacherRepo(String url) {
        this.url = url;
    }

    public void displayAllTeachers() {
        String url = "jdbc:mysql://localhost:3306/cr7_wong";

        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");

        try (Connection connection = DriverManager.getConnection(url, properties)) {

            String sql = "SELECT Teacher_ID, NAME, Surname FROM teacher";
            PreparedStatement statement = connection.prepareStatement(sql);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    String Name = resultSet.getString("NAME");
                    String Surname = resultSet.getString("Surname");
                    int Teacher_ID = resultSet.getInt("Teacher_ID");
                    System.out.println(Teacher_ID + " " + Name + " " + Surname);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public void displayTeacherInfo(int teacherId) {
        String url = "jdbc:mysql://localhost:3306/cr7_wong";

        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");

        try (Connection connection = DriverManager.getConnection(url, properties)) {
            String sql = "SELECT DISTINCT\n" +
                    "    teacher.NAME,\n" +
                    "    teacher.Surname,\n" +
                    "    classes.Class_Name\n" +
                    "FROM\n" +
                    "    teacher\n" +
                    "LEFT JOIN teacher_class tc ON\n" +
                    "    teacher.Teacher_ID = tc.teacher_id\n" +
                    "LEFT JOIN classes ON classes.Class_ID = tc.class_id\n" +
                    "WHERE\n" +
                    "    teacher.teacher_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, teacherId);
            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {
                    String Name = resultSet.getString("NAME");
                    String Surname = resultSet.getString("Surname");
                    System.out.println("ID" + " " + teacherId + ":" + " " + "Teacher" + " " + Name + " " + Surname + " " + "teaches");

                    do {
                        String className = resultSet.getString("Class_Name");
                        if (className == null) {
                            System.out.println("The teacher does not teach any classes");
                            break;
                        }

                        System.out.println(className);
                    } while (resultSet.next());
                } else {
                    System.out.println("This teacher does not exist");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
    }

    public void createAllTeachersReport(File outputFilePath) {

        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");

        try (Connection connection = DriverManager.getConnection(url, properties)) {

            String sql = "SELECT NAME, Surname,Email,Teacher_ID  FROM teacher";
            PreparedStatement statement = connection.prepareStatement(sql);
            try (ResultSet resultSet = statement.executeQuery();
                 FileWriter writer = new FileWriter(outputFilePath)) {
                while (resultSet.next()) {
                    String Name = resultSet.getString("NAME");
                    String Surname = resultSet.getString("Surname");
                    String Class_ID = resultSet.getString("Teacher_ID");
                    writer.write(Class_ID + ":" + Name + ";" + Surname + ";" + '\n');
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(System.err);
        }
    }
}
