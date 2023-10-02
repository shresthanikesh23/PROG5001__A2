import java.util.Scanner;
//F5 Menu stystem Requirement
import java.util.List;

public class MenuSystem {
    private Scanner scanner;
    private List<Student> students;

    public MenuSystem(List<Student> students) {
        scanner = new Scanner(System.in);
        this.students = students;
    }

    public void displayMenu() {
        int choice;

        do {
            System.out.println("Menu:");
            System.out.println("1. Calculate total marks for all students");
            System.out.println("2. Print students with total marks below a threshold");
            System.out.println("3. Print top 5 students with highest total marks");
            System.out.println("4. Print top 5 students with lowest total marks");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    calculateTotalMarks(students);
                    break;
                case 2:
                    printStudentsBelowThreshold(students);
                    break;
                case 3:
                    printTop5Students(students, true);
                    break;
                case 4:
                    printTop5Students(students, false);
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);
    }

    private void calculateTotalMarks(List<Student> students) {
        // Implement F2 functionality here
        for (Student student : students) {
            double totalMarks = student.calculateTotalMark(); // Use the method here
            System.out.println(
                    student.getFirstName() + " " + student.getLastName() + ", ID: " + student.getStudentID() +
                            "\nTotal Marks: " + totalMarks
            );
        }
    }

    private void printStudentsBelowThreshold(List<Student> students) {
        // Implement F3 functionality here
        System.out.print("Enter the threshold: ");
        double threshold = scanner.nextDouble();
        System.out.println("Students with total marks below " + threshold + ":");
        for (Student student : students) {
            double totalMarks = student.calculateTotalMark(); // Use the method here
            if (totalMarks < threshold) {
                System.out.println(
                        student.getFirstName() + " " + student.getLastName() + ", ID: " + student.getStudentID() +
                                "\nTotal Marks: " + totalMarks
                );
            }
        }
    }

    private void printTop5Students(List<Student> students, boolean highest) {
        // Implement F4 functionality here
        students.sort((s1, s2) -> {
            double totalMarks1 = s1.calculateTotalMark(); // Use the method here
            double totalMarks2 = s2.calculateTotalMark(); // Use the method here
            return highest ? Double.compare(totalMarks2, totalMarks1) : Double.compare(totalMarks1, totalMarks2);
        });

        int count = 5;
        if (!highest) {
            count = Math.min(count, students.size());
        }

        System.out.println("Top 5 students with " + (highest ? "highest" : "lowest") + " total marks:");
        for (int i = 0; i < count; i++) {
            Student student = students.get(i);
            double totalMarks = student.calculateTotalMark(); // Use the method here
            System.out.println(
                    student.getFirstName() + " " + student.getLastName() + ", ID: " + student.getStudentID() +
                            "\nTotal Marks: " + totalMarks
            );
        }
    }
}
