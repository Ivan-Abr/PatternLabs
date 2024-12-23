package InputFilterPack.Models;

import InputFilterPack.Controllers.ContactFieldController;
import InputFilterPack.States.FilterState;
import InputFilterPack.States.FilterStateNo;
import InputFilterPack.States.FilterStateNoMatter;
import InputFilterPack.States.FilterStateYes;
import Main.UpdateDataInterface;
import student.Student;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class ContactFilterModel {
    public ContactFieldController contactFieldController;
    public FilterState filterState;


    public int selectedOptionIndex = 2;
    public String filterInput = "";
    private FilterState[] states;


    public LinkedList<UpdateDataInterface> subs = new LinkedList<>();
    public ContactFilterModel(ContactFieldController contactFieldController,String fieldName){
        this.contactFieldController = contactFieldController;


        this.states = new FilterState[]{new FilterStateYes(fieldName), new FilterStateNo(fieldName), new FilterStateNoMatter(fieldName)};
        this.filterState = this.states[selectedOptionIndex];
    }
    public void subscribe(UpdateDataInterface sub){
        this.subs.add(sub);
    }

    public void setSelectedOption(int selectedOptionIndex) {
        this.selectedOptionIndex = selectedOptionIndex;
        this.filterState = this.states[selectedOptionIndex];
        this.setFilterInput(this.filterInput);
        this.notifySubs();
    }
    public void setFilterInput(String text) {
        this.filterInput = text;
        this.filterState.setValueField(text);
        this.notifySubs();
    }

    public void notifySubs(){
        for(UpdateDataInterface sub:this.subs){
            sub.updatePage();
        }
    }

    public void clearFilterInput(){
        this.setFilterInput("");
        this.selectedOptionIndex = 2;
    }
    public Function<List<Student>,List<Student>> getRequest(){
        return this.filterState.getRequestPart();
    }
}