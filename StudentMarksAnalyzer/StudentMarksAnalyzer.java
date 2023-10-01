import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class StudentMarksAnalyzer {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        String unitName = "";
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the file name: ");
            String fileName = scanner.nextLine();

            BufferedReader reader = new BufferedReader(new FileReader(fileName));
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
                    unitName = line;
                    System.out.println("Unit Name: " + unitName);
                }
            }

            // Display student data and calculate total marks
            for (Student student : students) {
                System.out.println("Last Name: " + student.getLastName());
                System.out.println("First Name: " + student.getFirstName());
                System.out.println("Student ID: " + student.getStudentID());
                System.out.println("Assignment 1: " + student.getAssignment1());
                System.out.println("Assignment 2: " + student.getAssignment2());
                System.out.println("Assignment 3: " + student.getAssignment3());

                // Calculate and display the total mark
                double totalMark = student.calculateTotalMark();
                System.out.println("Total Mark: " + totalMark);

                System.out.println();
            }

            // Prompt the user to enter the threshold for total marks
            System.out.print("Enter the threshold for total marks: ");
            double threshold = scanner.nextDouble();

            // Print students below the threshold
            System.out.println("Students with Total Marks Below Threshold:");
            for (Student student : students) {
                double totalMark = student.calculateTotalMark();
                if (totalMark < threshold) {
                    System.out.println("Last Name: " + student.getLastName());
                    System.out.println("First Name: " + student.getFirstName());
                    System.out.println("Student ID: " + student.getStudentID());
                    System.out.println("Total Mark: " + totalMark);
                    System.out.println();
                }
            }

            // Sort students by total marks (highest to lowest)
            students.sort((s1, s2) -> Double.compare(s2.calculateTotalMark(), s1.calculateTotalMark()));

            // Print the top 5 students with the highest total marks
            System.out.println("Top 5 Students with Highest Total Marks:");
            for (int i = 0; i < Math.min(5, students.size()); i++) {
                Student student = students.get(i);
                System.out.println("Last Name: " + student.getLastName());
                System.out.println("First Name: " + student.getFirstName());
                System.out.println("Student ID: " + student.getStudentID());
                System.out.println("Total Mark: " + student.calculateTotalMark());
                System.out.println();
            }

            // Print the top 5 students with the lowest total marks
            System.out.println("Top 5 Students with Lowest Total Marks:");
            for (int i = students.size() - 1; i >= Math.max(0, students.size() - 5); i--) {
                Student student = students.get(i);
                System.out.println("Last Name: " + student.getLastName());
                System.out.println("First Name: " + student.getFirstName());
                System.out.println("Student ID: " + student.getStudentID());
                System.out.println("Total Mark: " + student.calculateTotalMark());
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the scanner in a finally block to ensure it's always closed
            scanner.close();
        }
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
}
