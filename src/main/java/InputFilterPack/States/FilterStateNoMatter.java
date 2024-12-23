package InputFilterPack.States;

import student.Student;

import java.util.List;
import java.util.function.Function;

public class FilterStateNoMatter implements FilterState{
    private final String fieldName;
    private String valueField="";

    public FilterStateNoMatter(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    public Function<List<Student>,List<Student>> getRequestPart() {
        return this::filter;
    }


    private List<Student> filter(List<Student> list) {
        return list.stream().filter(it->it.propertiesReturn().get(fieldName)==null || it.propertiesReturn().get(fieldName).toString().contains(valueField)).toList();
    }
    @Override
    public String toString() {
        return "FilterStateNoMatter";
    }
}
