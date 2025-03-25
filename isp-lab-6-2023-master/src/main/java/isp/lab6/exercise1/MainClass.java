package isp.lab6.exercise1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class MainClass {
    public static Set<Student> loadStudents() {
        ArrayList<String>   tempStudents = F2L();
        String[]            component;
        StudentGrade        studentGrade = null;

        for (String tempStudent : tempStudents) { // split all the lines into a line's component
            // FORMAT {ID, Name, Surname, Subject, Grade}; leave all of them to be String
            component = tempStudent.split(", ");

            if (studentGrade == null) {
                // if list is null, create
                studentGrade = new StudentGrade(
                        component[1],
                        component[2],
                        Long.parseLong(component[0]),
                        component[3],
                        Integer.parseInt(component[4])
                );
            }
            else {
                if (studentGrade.findStudent(component[1], component[2]) == null) {
                    // if student is not in the list, add them to the student list
                    studentGrade = new StudentGrade(
                            component[1],
                            component[2],
                            Long.parseLong(component[0]),
                            component[3],
                            Integer.parseInt(component[4])
                    );
                } else {
                    // update grade
                    studentGrade.findStudent(component[1], component[2]).updateGrades(
                            component[3],
                            Integer.parseInt(component[4])
                    );
                }
            }
        }

        return StudentGrade.getStudents();
    }

    // todo: add explanation with param and @ and stuff
    public static ArrayList<String> F2L() { // in truth, this is addStudent function from ReadMe
        ArrayList<String> students = new ArrayList<>();
        Scanner reader = null;

        // handle errors regarding files
        try {
            File studentGradesFile = new File("src\\students ex1.txt");
            reader = new Scanner(studentGradesFile);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File could not be accessed.");
            System.exit(0);
        }

        while (reader.hasNextLine()) { // add all the lines to a list of string
            String attribute = reader.nextLine();
            students.add(attribute);
        }

        return students;
    }

    public static void main(String[] args) {
        loadStudents();
        StudentGrade.classRegister();
    }
}
