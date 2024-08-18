package net.vrakin.group;

import net.vrakin.converter.StringConverter;
import net.vrakin.exception.GroupOverflowException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface GroupFileStorage {

    public static final String WORKDIR = "src/main/resources/groups";

    void saveGroupToCSV(Group group) throws IOException;
    Group loadGroupFromCSV(File file) throws IOException, GroupOverflowException;
    File findFileByGroupName(String groupName, File workFolder) throws FileNotFoundException;
}
