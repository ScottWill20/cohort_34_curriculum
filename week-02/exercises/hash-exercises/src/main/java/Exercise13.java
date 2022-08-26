import learn.Student;

import java.util.HashMap;

public class Exercise13 {
    public static void main(String[] args) {

        HashMap<Integer, Student> students = new HashMap<>();

        Student one = new Student();
        one.setFirstName("scott");
        one.setLastName("williams");
        one.setStudentId(1234);

        Student two = new Student();
        two.setFirstName("Collin");
        two.setLastName("Shawyer");
        two.setStudentId(5678);

        Student three = new Student();
        three.setFirstName("Jose");
        three.setLastName("Mondragon");
        three.setStudentId(9012);

        // add them to the HashMap

        students.put(one.getStudentId(), one);
        students.put(two.getStudentId(), two);
        students.put(three.getStudentId(), three);

        // get one of the students and display

        Student displayStudent = students.get(one.getStudentId());
        System.out.println(displayStudent);

        // remove a student

        students.remove(three.getStudentId());

        // display all remaining students

        System.out.println("Displaying all remaining students...");
        for (Student s : students.values()){
            System.out.println(s);
        }

    }
}
