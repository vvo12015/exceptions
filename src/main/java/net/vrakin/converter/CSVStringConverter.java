package net.vrakin.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import net.vrakin.Student;

public class CSVStringConverter implements StringConverter{

    private final CsvMapper mapper = new CsvMapper();

    public CSVStringConverter() {
        mapper.findAndRegisterModules();
    }

    @Override
    public String toStringRepresentation(Student student) throws JsonProcessingException {
        CsvSchema schema = mapper.schemaFor(Student.class).withHeader();
        return mapper.writer(schema).writeValueAsString(student);
    }

    @Override
    public Student fromStringRepresentation(String str) throws JsonProcessingException {
        CsvSchema schema = mapper.schemaFor(Student.class).withHeader();
        return mapper.readerFor(Student.class).with(schema).readValue(str);
    }
}
