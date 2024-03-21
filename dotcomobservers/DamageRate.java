package dotcomobservers;

import extdotcomgame.DotCom;
import java.util.ArrayList;

public class DamageRate implements Observer{
    private final DotCom subject;
    private int size;

    public DamageRate(DotCom s){
        subject = s;
        size = subject.getState().size();
    }
    public void damageRate(){
        ArrayList<String> state = subject.getState();
        int survivingCells = subject.getState().size();
        double result = (100.0 * (size - survivingCells) / size);
        String resultS = String.format("%.2f", result);

        System.out.println("Damage rate of " + subject.getName() + " : " + resultS + "%");
    }

    public void update(){}
}
