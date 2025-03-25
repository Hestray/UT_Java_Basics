package isp.lab6.exercise1;

import java.util.HashMap;
import java.util.Map;

public class Student {
    private String  name;            // nume mic
    private String  surname;         // nume de familie
    private long    id;
    private HashMap<String, Integer> grades = new HashMap<>();  // String key will be subject name

    // methods
    public Student(String name, String surname, long ID) {
        this.name       = name;
        this.surname    = surname;
        this.id         = ID;
    }

    /**
     * This method adds the grades of a student to the student's subjects' performances.
     * @param subject to which the grade should be updated (added)
     * @param grade to add
     */
    public boolean updateGrades(String subject, int grade) {
        if (subject == null || grade == 0) return false;
        grades.put(subject, grade);
        return true;
    }
    public double averageGrade() {
        double  avg = 0;
        int     count = 0;

        for (Map.Entry<String, Integer> grade : grades.entrySet()) {
            avg += grade.getValue();
            count++;
        }

        if (count == 0) return 0;
        return avg / count;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public HashMap<String, Integer> getGrades() {
        return grades;
    }

    public long getId() {
        return id;
    }
}
