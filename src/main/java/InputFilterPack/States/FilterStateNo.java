package InputFilterPack.States;


import student.Student;

import java.util.List;
import java.util.function.Function;

public class FilterStateNo implements FilterState {

    private final String fieldName;
    private String valueField = "";

    public FilterStateNo(String fieldName) {
        this.fieldName = fieldName;
    }

    public Function<List<Student>,List<Student>> getRequestPart() {
        return this::filter;
    }

    @Override
    public void setValueField(String valueField) {
        this.valueField = valueField;
    }


    private List<Student> filter(List<Student> list) {
        return list.stream().filter(it->it.propertiesReturn().get(fieldName)==null).toList();
    }

    @Override
    public String toString() {
        return "FilterStateNo";
    }
}
