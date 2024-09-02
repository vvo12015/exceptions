package net.vrakin.group;

import lombok.Getter;
import lombok.Setter;
import net.vrakin.Student;
import net.vrakin.exception.StudentNotFoundException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
public class Group {

    public static final int MAX_ARRAY_ELEMENT = 10;
    public static final int START_ARRAY_ELEMENT = 0;

    @Setter
    private String groupName;

    private final List<Student> students = new ArrayList<>(MAX_ARRAY_ELEMENT);

    public Group() {
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public void addStudent(Student student) {
        if (students.size() <= MAX_ARRAY_ELEMENT) {
            students.add(student);
        }
    }

    public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
        Student student = students.stream().filter(s -> s.getLastName().equals(lastName)).findFirst().orElse(null);
        if (student == null) {
            throw new StudentNotFoundException();
        }
        return student;
    }

    public void sortStudentsByLastName(){
        students.sort(Comparator.comparing(Student::getLastName));
    }


    public boolean removeStudentById(int id){
        return students.removeIf(s -> s.getId() == id);
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", students=" + students.toString() +
                '}';
    }
}
