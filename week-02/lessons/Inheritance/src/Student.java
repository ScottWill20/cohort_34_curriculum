public class Student {

    // make studentId read-only by omitted the setter
    private String studentId;

    public String getStudentId() {
        return studentId;
    }

    public Student(String studentId) {
        this.studentId = studentId;
    }
}