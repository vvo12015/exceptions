package net.vrakin.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import net.vrakin.Student;

public interface StringConverter {
    public String toStringRepresentation (Student student) throws JsonProcessingException;
    public Student fromStringRepresentation (String str) throws JsonProcessingException;
}
