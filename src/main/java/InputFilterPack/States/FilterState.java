package InputFilterPack.States;


import student.Student;

import java.util.List;
import java.util.function.Function;

public interface FilterState {
    public Function<List<Student>,List<Student>> getRequestPart();
    public void setValueField(String valueField);
}
