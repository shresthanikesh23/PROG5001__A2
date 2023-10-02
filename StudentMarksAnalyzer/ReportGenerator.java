//Here in ReportGenerator class in our code is  
//for generating a report based on the data and calculations 
//performed in the StudentMarksAnalyzer class.
import java.util.List;

public class ReportGenerator {
    private List<Student> students;

    public ReportGenerator(List<Student> students) {
        this.students = students;
    }

    public void generateReport() {
        double totalMarksSum = 0.0;
        int studentCount = students.size();

        if (studentCount == 0) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            totalMarksSum += student.calculateTotalMark();
        }

        double averageTotalMarks = totalMarksSum / studentCount;

        System.out.println("----- Student Report -----");
        System.out.println("Total number of students: " + studentCount);
        System.out.println("Average total marks: " + averageTotalMarks);
    }
}
