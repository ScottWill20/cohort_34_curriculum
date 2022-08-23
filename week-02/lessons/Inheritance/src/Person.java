public class Person {

    private String firstName;
    private String lastName;
    // NEW CODE: composition
    private Student studentRecord;
    private Instructor instructorRecord;

    // getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // NEW CODE
    public Student getStudentRecord() {
        return studentRecord;
    }

    public void setStudentRecord(Student studentRecord) {
        this.studentRecord = studentRecord;
    }

    public Instructor getInstructorRecord() {
        return instructorRecord;
    }

    public void setInstructorRecord(Instructor instructorRecord) {
        this.instructorRecord = instructorRecord;
    }

    // constructor
    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFullName() {

        StringBuilder buffer = new StringBuilder(100);

        // am I an instructor?
        if (instructorRecord != null) {
            buffer.append(instructorRecord.getTitle());
            buffer.append(" ");
        }

        // plain old person
        buffer.append(firstName);
        buffer.append(" ");
        buffer.append(lastName);

        // am I a student?
        if (studentRecord != null) {
            buffer.append(", ID: ");
            buffer.append(studentRecord.getStudentId());
        }

        return buffer.toString();
    }

}
