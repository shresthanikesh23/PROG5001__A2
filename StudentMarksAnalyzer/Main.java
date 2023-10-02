//Here calling StudentMArkAnalyzer to dispaly final
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentMarksAnalyzer analyzer = new StudentMarksAnalyzer();
        analyzer.loadDataFromCSV("prog5001_students_grade_2022.csv");
        analyzer.runMenuSystem();

        // Get the list of students from the analyzer
        List<Student> students = analyzer.getStudents();

        // Generate and display the report
        ReportGenerator reportGenerator = new ReportGenerator(students);
        reportGenerator.generateReport();
    }
}
