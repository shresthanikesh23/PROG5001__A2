public class Student {
    private String lastName;
    private String firstName;
    private String studentID;
    private double assignment1;
    private double assignment2;
    private double assignment3;

    // Constructor
    public Student(String lastName, String firstName, String studentID, double assignment1, double assignment2, double assignment3) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.studentID = studentID;
        this.assignment1 = assignment1;
        this.assignment2 = assignment2;
        this.assignment3 = assignment3;
    }

    // Getters
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getStudentID() {
        return studentID;
    }

    public double getAssignment1() {
        return assignment1;
    }

    public double getAssignment2() {
        return assignment2;
    }

    public double getAssignment3() {
        return assignment3;
    }

    // Calculate and return the total mark for the student
    public double calculateTotalMark() {
        return assignment1 + assignment2 + assignment3;
    }
}
