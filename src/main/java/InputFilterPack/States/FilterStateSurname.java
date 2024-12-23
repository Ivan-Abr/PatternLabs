package InputFilterPack.States;

import student.Student;

import java.util.List;
import java.util.function.Function;

public class FilterStateSurname implements FilterState {

    private String valueField="";

    public void setValueField(String valueField) {
        this.valueField = valueField;
    }

    public Function<List<Student>, List<Student>> getRequestPart() {
        return this::filter;
    }


    private List<Student> filter(List<Student> list) {
        return list.stream().filter(it -> it.getInitials().contains(valueField)).toList();
    }

    @Override
    public String toString() {
        return "FilterStateSurname";
    }

}
