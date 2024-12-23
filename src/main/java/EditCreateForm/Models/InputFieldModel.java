package EditCreateForm.Models;

import Main.UpdateDataInterface;

public class InputFieldModel {
    public String inputText = "";
    public UpdateDataInterface subscriber;

    public boolean optional = false;
    public InputFieldModel(boolean optional){
        this.optional = optional;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
        this.notifySub();
    }

    public void setSubscriber(UpdateDataInterface updateDataInterface){
        this.subscriber = updateDataInterface;
    }
    public void notifySub(){
        this.subscriber.updatePage();
    }
}
