package net.vrakin.group;

import lombok.Getter;
import lombok.Setter;
import net.vrakin.Student;
import net.vrakin.exception.GroupOverflowException;
import net.vrakin.exception.IdExistException;
import net.vrakin.exception.StudentNotFoundException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

@Getter
public class Group {

    public static final int MAX_ARRAY_ELEMENT = 10;
    public static final int START_ARRAY_ELEMENT = 0;

    @Setter
    private String groupName;

    private final Student[] students = new Student[MAX_ARRAY_ELEMENT];
    private int counter;

    public Group() {
        counter = START_ARRAY_ELEMENT;
    }

    public Group(String groupName) {
        counter = START_ARRAY_ELEMENT;
        this.groupName = groupName;
    }

    public void addStudent(Student student) throws GroupOverflowException, IdExistException {
        if (counter == MAX_ARRAY_ELEMENT) {
            throw new GroupOverflowException();
        }

        if (searchStudentByID(student.getId())>0)
            throw new IdExistException();

        students[counter] = student;
        counter++;
    }

    public Student searchStudentByLastName(String lastName) throws StudentNotFoundException {
        for (Student student: students){
            if (Objects.nonNull(student) &&
                    student.getLastName().equals(lastName)){
                return student;
            }
        }
        throw new StudentNotFoundException();
    }

    public void sortStudentsByLastName(){
        Arrays.sort(students, Comparator.comparing(Student::getLastName));
    }


    public boolean removeStudentById(int id){
        int index = 0;
        index = searchStudentByID(id);
        if (index == -1) return false;

        removeStudentByIndex(index);
        return true;
    }

    private int searchStudentByID(int id) {
        for (int i = 0; i < counter; i++) {
            if (students[i].getId() == id){
                return i;
            }
        }
        return -1;
    }

    private void removeStudentByIndex(int index){
        int j = 0;
        for (int i = 0; i < counter; i++) {
            if (i == index) j++;
            if (j == MAX_ARRAY_ELEMENT) students[i] = null;
            else students[i] = students[j];
            j++;
        }
        counter--;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", students=" + Arrays.toString(students) +
                '}';
    }
}
