package net.vrakin;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.vrakin.converter.CSVStringConverter;
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
        StudentReader reader = new CmdStudentReader();

        Student student = reader.readStudent();

        CSVStringConverter converter = new CSVStringConverter();

        try {
            String strStudent = converter.toStringRepresentation(student);
            System.out.println(strStudent);

            Student student1 = converter.fromStringRepresentation(strStudent);
            System.out.println(student1);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        Group group = new Group();
        for (int i = 0; i < Group.MAX_ARRAY_ELEMENT; i++) {
            student = reader.readStudent();

            group.addStudent(student);
        }

        group.sortStudentsByLastName();

        System.out.println(group);
    }
}
