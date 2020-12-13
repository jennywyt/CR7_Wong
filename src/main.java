import java.util.Scanner;

public class main {
    static StudentRepo studentRepo = new StudentRepo();
    private static TeacherRepo teacherRepo = new TeacherRepo();
    private static ClassRepo classRepo = new ClassRepo();

    public static void main(String[] args) {

        printHeader();

        int userInput;

        do {
            printMenu();
            userInput = getUserInput(4, "your service");
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
                    createReport();

            }
        } while (userInput != 0);
    }

    private static void createReport() {
    }

    private static void displayTeacherInfoByID() {

        Scanner scanner = new Scanner(System.in);
        int teacherId= -1;
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
        System.out.println("5: Create Report");

    }
}
