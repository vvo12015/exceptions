package net.vrakin.group;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import net.vrakin.Student;
import net.vrakin.exception.GroupOverflowException;

import java.io.*;
import java.util.List;

public class GroupFileStorageImpl implements GroupFileStorage {

    private final CsvMapper mapper = new CsvMapper();

    public GroupFileStorageImpl() {
        mapper.findAndRegisterModules();
    }

    @Override
    public void saveGroupToCSV(Group group) throws IOException {
        File file = getGroupFile(group);
        file.getParentFile().mkdirs();

        CsvSchema schema = mapper.schemaFor(Student.class).withHeader();
        mapper.writer(schema).writeValuesAsArray(file).writeAll(group.getStudents());

    }

    private static File getGroupFile(Group group) {
        String filename = GroupFileStorage.WORKDIR + "/" + group.getGroupName() + ".csv";
        return new File(filename);
    }

    @Override
    public Group loadGroupFromCSV(File file) throws IOException, GroupOverflowException {
        Group group = new Group();

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader();

        String groupName;

        ObjectReader oReader = csvMapper.reader(Student.class).with(schema);
        try (Reader reader = new FileReader(file)) {
            MappingIterator<Student> mi = oReader.readValues(reader);
            List<Student> students = mi.readAll();
            Student studentName = students.get(0);

            groupName = studentName.getGroupName();

            for (Student student : students) {
                group.addStudent(student);
            }
        }

        group.setGroupName(groupName);

        return group;
    }

    @Override
    public File findFileByGroupName(String groupName, File workFolder) throws FileNotFoundException {
        String filename = GroupFileStorage.WORKDIR + "/" + groupName + ".csv";

        File groupFile = new File(filename);
        if (groupFile.exists()) {
            return groupFile;
        }else throw new FileNotFoundException(groupName);
    }
}
