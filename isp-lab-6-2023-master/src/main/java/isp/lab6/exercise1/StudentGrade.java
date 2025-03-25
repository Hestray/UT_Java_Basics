package isp.lab6.exercise1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class StudentGrade { // no need for inheritance, just aggregation
    static private Set<Student> students = new HashSet<>();

    // methods
    public StudentGrade(String name, String surname, long ID, String subject, int grade) { // adds a student and their grade to a list
        // constructor for individual student
        Student student = new Student(name, surname, ID);
        addStudent(student, subject, grade);
    }

    public boolean addStudent(Student student, String subject, int grade) {
        if (subject == null || student == null || grade == 0) return false;
        student.getGrades().put(subject, grade); // add to hashmap
        students.add(student);                   // add the student to the list
        return true;
    }

    public boolean removeStudent(String name, String surname) {
        for (Student stud : students) {
            Student currStud = findStudent(name, surname);
            if (currStud != null) {
                students.remove(currStud);
                return true;
            }
        }
        return false;
    }

    /**
     * Displays all the students in the class register (classbook)
     */

    public Student findStudent(String name, String surname) {
        for (Student stud : students) {
            if (stud.getName().compareTo(name) == 0 && stud.getSurname().compareTo(surname) == 0)
                return stud;
        }
        return null;
    }

    public static void classRegister() {
        System.out.println("Class register: ");
        for (Student stud : students) {
            System.out.println(stud.getId() + "\t" + stud.getName() + " " + stud.getSurname() + "\t\t" + String.format("%.2f", stud.averageGrade()));
        }
    }

    public static Set<Student> getStudents() {
        return students;
    }
}
