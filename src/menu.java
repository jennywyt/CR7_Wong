import java.io.File;
import java.util.Scanner;

public class menu {
    static StudentRepo studentRepo = new StudentRepo("jdbc:mysql://localhost:3306/cr7_wong");
    private static TeacherRepo teacherRepo = new TeacherRepo("jdbc:mysql://localhost:3306/cr7_wong");
    private static ClassRepo classRepo = new ClassRepo("jdbc:mysql://localhost:3306/cr7_wong");

    public static void main(String[] args) {

        printHeader();

        int userInput;

        do {
            printMenu();
            userInput = getUserInput(7, "your service");
            switch (userInput) {
                case 1:
                    displayAllStudents();
                    break;
                case 2:
                    displayAllTeacher();
                    break;
                case 3:
                    displayAllClasses();
                    break;
                case 4:
                    displayTeacherInfoByID();
                    break;
                case 5:
                    createStudentReport();
                    break;
                case 6:
                    createTeacherReport();
                    break;
                case 7:
                    createStudentPerClassReport();
                    break;

            }
        } while (userInput != 0);
    }

    private static void createStudentPerClassReport() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the path of the output file: ");
        File outputFileName = new File(scanner.nextLine());
        classRepo.createStudentPerClassReport(outputFileName);
    }

    private static void createTeacherReport() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the path of the output file: ");
        File outputFileName = new File(scanner.nextLine());
        teacherRepo.createAllTeachersReport(outputFileName);
    }

    private static void createStudentReport() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input the path of the output file: ");
        File outputFileName = new File(scanner.nextLine());
        studentRepo.createAllStudentsReport(outputFileName);
    }

    private static void displayTeacherInfoByID() {

        Scanner scanner = new Scanner(System.in);
        int teacherId = -1;
        while (teacherId < 0) {
            try {
                System.out.println("Please input a Teacher ID: ");
                teacherId = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input can only be number. ");
            }
        }
        teacherRepo.displayTeacherInfo(teacherId);
    }


    private static void displayAllClasses() {
        classRepo.displayAllClass();
    }

    private static void displayAllTeacher() {
        teacherRepo.displayAllTeachers();
    }

    private static void displayAllStudents() {
        studentRepo.displayAllStudents();
    }

    private static int getUserInput(int maxChoices, String choiceType) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice < 0 || choice > maxChoices) {
            try {
                System.out.println("Please select " + choiceType + ": ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input can only be number. ");
            }
        }
        return choice;
    }

    private static void printHeader() {
        System.out.println("*********************");
        System.out.println("*** Welcome  ********");
        System.out.println("***   to     ********");
        System.out.println("*** School System ***");
        System.out.println("*********************");
    }

    private static void printMenu() {
        System.out.println("How can I help you with?");
        System.out.println("1: Display all students");
        System.out.println("2: Display all teachers");
        System.out.println("3: Display all classes");
        System.out.println("4: Display teacher info by ID");
        System.out.println("5: Create All Student Report");
        System.out.println("6: Create All Teacher Report");
        System.out.println("7: Create Student Per Class Report");
    }
}
