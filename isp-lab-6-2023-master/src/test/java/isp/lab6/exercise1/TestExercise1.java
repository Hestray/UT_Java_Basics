package isp.lab6.exercise1;

import org.junit.Assert;
import org.junit.Test;

public class TestExercise1 {
    @Test
    public void TestExercise1() {
        StudentGrade yearbook = new StudentGrade("Daniel", "Jackson", 1234, "Calculus I", 9);
        // yearbook now only has one student
        Student stud1 = new Student("Carrie", "Maine", 1113);
        Assert.assertTrue(yearbook.addStudent(stud1, "Geography", 8)); // check if student is added to the list
        // yearbook should now have 2 students
        Student stud2 = new Student("Daniel", "Khershey", 1010);
        yearbook.addStudent(stud2, "Pottery", 10);
        // yearbook should now have 3 students

        double avgGrade = yearbook.findStudent("Daniel", "Jackson").averageGrade(); // should be 9
        Assert.assertNotNull(yearbook.findStudent("Daniel", "Jackson")); // checks if this student exists
        Assert.assertTrue(yearbook.findStudent("Daniel", "Jackson").updateGrades("Linear Algebra", 10)); // check if it updates the grades of Daniel Jackson
        StudentGrade.classRegister();
        Assert.assertEquals(9.5D, yearbook.findStudent("Daniel", "Jackson").averageGrade(), 0); // tests if student was updated

        Assert.assertTrue(yearbook.removeStudent("Daniel", "Jackson")); // tests if it deletes the first student
        // yearbook will only have 2 students left
        StudentGrade.classRegister();
    }
}
