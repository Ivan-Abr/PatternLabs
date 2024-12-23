package InputFilterPack.States;

import student.Student;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class FilterStateYes implements FilterState {
    private final String fieldName;
    private String valueField="";

    public FilterStateYes(String fieldName) {
        this.fieldName = fieldName;
    }

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    public Function<List<Student>,List<Student>> getRequestPart() {
        return this::filter;
    }


    private List<Student> filter(List<Student> list) {
        return list.stream().filter(it-> {
            return Objects.toString(it.propertiesReturn().get(fieldName),"").contains(valueField);
        }).toList();
    }
    @Override
    public String toString() {
        return "FilterStateYes";
    }
}
