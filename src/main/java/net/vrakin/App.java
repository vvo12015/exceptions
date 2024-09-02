package net.vrakin;

import net.vrakin.exception.GroupOverflowException;
import net.vrakin.exception.StudentNotFoundException;
import net.vrakin.group.Group;

import java.util.ArrayList;
import java.util.List;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws GroupOverflowException {
        StudentReader reader = new CmdStudentReader();

        Student student;

        Group group1 = new Group();
        Group group2 = new Group();
        Group group3 = new Group();
        Group group4 = new Group();

        List<Group> groups = new ArrayList<Group>();
        groups.add(group1);
        groups.add(group2);
        groups.add(group3);
        groups.add(group4);

        for (int i = 0; i < Group.MAX_ARRAY_ELEMENT; i++) {
            student = new Student("Name" + i, "LName" + i, Gender.values()[i%2], i, "GroupName" + ((i / 3)+1));
            groups.get((i / 3)).setGroupName("GroupName" + ((i / 3)+1));
            groups.get((i / 3)).addStudent(student);
        }

        groups.forEach(group -> {
            group.sortStudentsByLastName();
            System.out.println(group);
        });

        groups.forEach(group -> {
            try {
                System.out.println(group.searchStudentByLastName("LName0"));
            } catch (StudentNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
