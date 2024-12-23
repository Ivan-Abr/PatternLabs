package InputFilterPack.Controllers;

import student.Student;

import java.util.List;
import java.util.function.Function;

interface InputControllerInterface {
    public void clearInput();

    public void setParams();

    public void changeInputText();

    public Function<List<Student>,List<Student>> getRequest();
}
