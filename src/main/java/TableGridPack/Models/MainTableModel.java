package TableGridPack.Models;


import Main.UpdateDataInterface;

import java.util.LinkedList;
import java.util.Objects;

public class MainTableModel {
    private int clickCount = 0;

    public int order = 0;
    public String columnName = "";
    public LinkedList<UpdateDataInterface> subscribers = new LinkedList<>();
    public void sortOrder(String columnName){
        if (Objects.equals(this.columnName, columnName)) {
            this.clickCount += 1;
            if(this.clickCount==1)  this.order = -1;
            else if (this.clickCount==2) this.order = 1;
            else {
                this.order = 0;
                this.clickCount = 0;
            }
        }
        else{
            this.clickCount = 1;
            this.order = -1;
            this.columnName = columnName;
        }
        this.notifySubs();
    }
    public void notifySubs(){
        for(UpdateDataInterface in: subscribers){
            in.updatePage();
        }
    }
    public void subscribe(UpdateDataInterface obj){
        this.subscribers.add(obj);
    }

}