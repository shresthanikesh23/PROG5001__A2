import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentMarksAnalyzer {
    private List<Student> students;
    private Scanner scanner;

    public StudentMarksAnalyzer() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        StudentMarksAnalyzer analyzer = new StudentMarksAnalyzer();
        analyzer.loadDataFromCSV("prog5001_students_grade_2022.csv");
        analyzer.runMenuSystem();

        // Generate and display the report
        ReportGenerator reportGenerator = new ReportGenerator(analyzer.getStudents());
        reportGenerator.generateReport();
    }

    public void loadDataFromCSV(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean readingStudentData = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("//")) {
                    continue;
                }

                if (readingStudentData) {
                    String[] parts = line.split(",");
                    if (parts.length == 6) {
                        String lastName = parts[0];
                        String firstName = parts[1];
                        String studentID = parts[2];
                        double assignment1 = parseDouble(parts[3]);
                        double assignment2 = parseDouble(parts[4]);
                        double assignment3 = parseDouble(parts[5]);

                        Student student = new Student(lastName, firstName, studentID, assignment1, assignment2, assignment3);
                        students.add(student);
                    }
                } else if (line.startsWith("Unit:")) {
                    readingStudentData = true;
                    System.out.println("Unit Name: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runMenuSystem() {
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
                    calculateTotalMarks();
                    break;
                case 2:
                    printStudentsBelowThreshold();
                    break;
                case 3:
                    printTop5Students(true);
                    break;
                case 4:
                    printTop5Students(false);
                    break;
                case 5:
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        } while (choice != 5);

        scanner.close();
    }

    // Getter method for the students list
    public List<Student> getStudents() {
        return students;
    }

    // Helper method to parse double values, handling empty or non-numeric strings
    private static double parseDouble(String value) {
        try {
            if (value.isEmpty()) {
                return 0.0;
            }
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private void calculateTotalMarks() {
        for (Student student : students) {
            double totalMarks = student.calculateTotalMark();
            System.out.println(
                    student.getFirstName() + " " + student.getLastName() + ", ID: " + student.getStudentID() +
                            "\nTotal Marks: " + totalMarks
            );
        }
    }

    private void printStudentsBelowThreshold() {
        System.out.print("Enter the threshold: ");
        double threshold = scanner.nextDouble();
        System.out.println("Students with total marks below " + threshold + ":");
        for (Student student : students) {
            double totalMarks = student.calculateTotalMark();
            if (totalMarks < threshold) {
                System.out.println(
                        student.getFirstName() + " " + student.getLastName() + ", ID: " + student.getStudentID() +
                                "\nTotal Marks: " + totalMarks
                );
            }
        }
    }
    //thresoldd
    //print top 5 highest student and lowest
    private void printTop5Students(boolean highest) {
        students.sort((s1, s2) -> {
            double totalMarks1 = s1.calculateTotalMark();
            double totalMarks2 = s2.calculateTotalMark();
            return highest ? Double.compare(totalMarks2, totalMarks1) : Double.compare(totalMarks1, totalMarks2);
        });

        int count = 5;
        if (!highest) {
            count = Math.min(count, students.size());
        }
        
        System.out.println("Top 5 students with " + (highest ? "highest" : "lowest") + " total marks:");
        for (int i = 0; i < count; i++) {
            Student student = students.get(i);
            double totalMarks = student.calculateTotalMark();
            System.out.println(
                    student.getFirstName() + " " + student.getLastName() + ", ID: " + student.getStudentID() +
                            "\nTotal Marks: " + totalMarks
            );
        }
    }
}
