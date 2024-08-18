package net.vrakin;

import lombok.extern.slf4j.Slf4j;
import net.vrakin.exception.GroupOverflowException;
import net.vrakin.group.Group;
import net.vrakin.group.GroupFileStorage;
import net.vrakin.group.GroupFileStorageImpl;

import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * Hello world!
 *
 */
@Slf4j
public class AppFile
{
    public static void main( String[] args ) throws GroupOverflowException, IOException {
        String groupName = "group1";
        Group group = new Group(groupName);

        Random rand = new Random();
        for (int i = 0; i < Group.MAX_ARRAY_ELEMENT; i++) {
            Student student = new Student(
                    String.format("name%02d", Group.MAX_ARRAY_ELEMENT-i), String.format("lastname%02d", i),
                    Gender.values()[rand.nextInt(2)], i, groupName
            );

            group.addStudent(student);
        }

        log.info(group.toString());

        GroupFileStorage fileStorage = new GroupFileStorageImpl();

        fileStorage.saveGroupToCSV(group);

        File workingDir = new File(GroupFileStorage.WORKDIR);

        File file = fileStorage.findFileByGroupName(group.getGroupName(), workingDir);

        Group newGroup = fileStorage.loadGroupFromCSV(file);

        System.out.println(newGroup);
    }
}
