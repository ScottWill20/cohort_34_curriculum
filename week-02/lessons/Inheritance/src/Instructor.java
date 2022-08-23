public class Instructor {

    // employeeId and title are read-only
    private String employeeId;
    private String title;

    public String getEmployeeId() {
        return employeeId;
    }

    public String getTitle() {
        return title;
    }

    public Instructor(String employeeId, String title) {
        this.employeeId = employeeId;
        this.title = title;
    }
}