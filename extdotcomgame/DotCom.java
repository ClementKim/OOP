package extdotcomgame;

import java.util.ArrayList;
import dotcomobservers.Observable;

public class DotCom extends Observable {
    protected ArrayList<String> locationCells;
    protected String name;
    protected int size;

    public void setLocationCells(ArrayList<String> loc){
        locationCells = (ArrayList<String>)loc.clone();
        size = loc.size();
    }

    public void setName(String n){
        name = n;
    }

    public String getName(){
        return name;
    }

    public String checkYourself(String userInput){
        String result = "miss";
        int index = locationCells.indexOf(userInput);
        if (index >= 0){
            locationCells.remove(index);
            if (locationCells.isEmpty()){
                result = "kill";
                System.out.println("You sunk " + name + " : ( ");
            }

            else
                result = "hit";
            this.notifyObservers();
        }

        return result;
    }

    public ArrayList<String> getState(){
        return (ArrayList<String>)locationCells.clone();
    }

    public int get_size(){
        return size;
    }
}
