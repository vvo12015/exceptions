package net.vrakin;

import net.vrakin.exception.GroupOverflowException;
import net.vrakin.exception.IdExistException;

import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws GroupOverflowException {
        String groupName = "group1";
        Group group = new Group(groupName);
        Random rand = new Random();
        for (int i = 0; i < Group.MAX_ARRAY_ELEMENT; i++) {
            Gender gender = Gender.values()[rand.nextInt(Gender.values().length)];

            Student student = new Student(
                    "Test" + String.format("%02d", Group.MAX_ARRAY_ELEMENT-i),
                    "Test" + String.format("%02d", Group.MAX_ARRAY_ELEMENT-i),
                    gender,
                    i,
                    groupName
            );

            group.addStudent(student);
        }

        System.out.println(group);

        int id = 0;

        if (group.removeStudentById(id)){
            System.out.printf("Remove student with id %d%n%s%n", id, group.toString());
            group.addStudent(new Student("ATest1", "ATest2", Gender.FEMALE, 0, groupName));
            System.out.printf("Add new student with id %d%n%s%n", id, group.toString());
        }

        id = 6;

        if (group.removeStudentById(id)){
            System.out.printf("Remove student with id %d%n%s%n", id, group.toString());
            group.addStudent(new Student("ZTest1", "ZTest2", Gender.MALE, id, groupName));
            System.out.printf("Add new student with id %d%n%s%n", id, group.toString());
        }

        try{
            group.addStudent(new Student("ZTest1", "ZTest2", Gender.MALE, id, groupName));
        }catch (GroupOverflowException e){
            System.out.printf("Group overflow exception: %s%n", e);
        }

        id = 9;
        int existId = 6;
        System.out.println("Remove some student with id " + id);
        group.removeStudentById(id);
        try{
            group.addStudent(new Student("ExistID", "ExistID", Gender.MALE, existId, groupName));
        }catch (IdExistException e){
            System.out.printf("Id %s exists.%n%s", existId, e);
        }

        for (int i = 0; i < Group.MAX_ARRAY_ELEMENT; i++) {
            group.removeStudentById(i);
        }

        System.out.printf("Removed group with name %s%n%s", groupName, group.toString());
    }
}
