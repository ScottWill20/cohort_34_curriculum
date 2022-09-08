package learn;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        StudentDataStore ds = new StudentDataStore();
        List<Student> students = ds.all();

        // 0. Print all students
        // iteration solution
//        for (Student student : students) {
//            System.out.println(student);
//        }

        // stream solution
//        students.stream().forEach(System.out::println);

        // 1. Print students from Argentina
//        students.stream().filter(s -> s.getCountry().equalsIgnoreCase("Argentina"))
//                        .forEach(System.out::println);

        // 2. Print students whose last names starts with 'T'.

//        students.stream().filter(s -> s.getLastName().toLowerCase().charAt(0) == 't')
//                .forEach(System.out::println);

        // 3. Print students from Argentina, ordered by GPA
//        List<Student> argentina = students.stream()
//                .filter(s -> s.getCountry().equalsIgnoreCase("Argentina"))
//                        .collect(Collectors.toCollection(ArrayList::new));
//        argentina.stream().sorted(Comparator.comparing(Student::getGpa).reversed())
//                .forEach(System.out::println);

        // 4. Print the bottom 10% (100 students) ranked by GPA.

//        students.stream().sorted(Comparator.comparing(Student::getGpa)).limit(100)
//                .forEach(System.out::println);

        // 5. Print the 4th - 6th ranked students by GPA from Argentina
//        List<Student> argentinaFourthSixth = students.stream()
//                .filter(s -> s.getCountry().equalsIgnoreCase("Argentina"))
//                .collect(Collectors.toCollection(ArrayList::new));
//        argentinaFourthSixth.stream().sorted(Comparator.comparing(Student::getGpa).reversed()).skip(3).limit(3)
//                .forEach(System.out::println);

        // 6. Is anyone from Maldives?
//        boolean anyStudentIsFromMaldives = students.stream()
//                .anyMatch(s -> s.getCountry().equalsIgnoreCase("Maldives"));
//
//        System.out.println("At least one student is from Maldives: " + anyStudentIsFromMaldives);

        // 7. Does everyone have a non-null, non-empty email address?
//        boolean allStudentsHaveGoodEmail = students.stream()
//                .allMatch(s -> !s.getEmailAddress().isBlank() || s.getEmailAddress() != null);
//
//        System.out.println("All students have a good email address: " + allStudentsHaveGoodEmail);

        // 8. Print students who are currently registered for 5 courses.
//        students.stream().filter(s -> s.getRegistrations().size() == 5).forEach(System.out::println);

        // 9. Print students who are registered for the course "Literary Genres".
//        students.stream().filter(s -> s.getRegistrations().stream()
//                        .anyMatch(reg -> reg.getCourse().equalsIgnoreCase("literary genres")))
//                        .forEach(System.out::println);

        // 10. Who has the latest birthday? Who is the youngest?
//        Optional<Student> youngestStudent =  students.stream().sorted(Comparator.comparing(Student::getBirthDate).reversed()).findFirst();
//
//        if (youngestStudent.isPresent()) {
//            Student s = youngestStudent.get();
//            System.out.println("The youngest student is: " + s.getFirstName() + " " + s.getLastName());
//        }

        // 11. Who has the highest GPA? There may be a tie. ?????
//        Optional<Student> highestGPA = students.stream().max(Comparator.comparing(Student::getGpa));
//        if (highestGPA.isPresent()) {
//            Student s = highestGPA.get();
//            System.out.println("The student with the highest GPA is: " + s.getFirstName() + " " + s.getLastName());
//        }

        // 12. Print every course students are registered for, including repeats.


        // 13. Print a distinct list of courses students are registered for.


        // 14. Print a distinct list of courses students are registered for, ordered by name.

        // 15. Count students per country.
//        Map<String, Long> studentsByCountry = students.stream()
//                .collect(Collectors.groupingBy(Student::getCountry,Collectors.counting()));
//        for (String country : studentsByCountry.keySet()) {
//            System.out.println(country + ": " + studentsByCountry.get(country));
//        }

        // 16. Count students per country. Order by most to fewest students.

        // 17. Count registrations per course.

        // 18. How many registrations are not graded (GradeType == AUDIT)?

        // 19. Create a new type, StudentSummary with fields for Country, Major, and IQ.
        //     Map Students to StudentSummary, then sort and limit by IQ (your choice of low or high).

        // 20. What is the average GPA per country (remember, it's random fictional data).

        // 21. What is the maximum GPA per country?

        // 22. Print average IQ per Major ordered by IQ ascending.

        // 23. STRETCH GOAL!
        // Who has the highest pointPercent in "Sacred Writing"?
    }
}