
public class App {

    public static void main(String[] args) {

        Person p = new Person("Merilee", "Sheldrick");

        Person s = new Person("Bale", "Packmann");
        s.setStudentRecord(new Student("100-A29-WER"));

        Person i = new Person("Letisha", "Pursey");
        i.setInstructorRecord(new Instructor("INS-COMPSCI-123", "Dr."));

        Person both = new Person("Pembroke", "Andrewartha");
        both.setStudentRecord(new Student("245-GZ4-KLA"));
        both.setInstructorRecord(new Instructor("INS-LIT-532", "Prof."));


        System.out.println(p.getFullName());
        System.out.println(s.getFullName());
        System.out.println(i.getFullName());
    }
}
