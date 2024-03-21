package extdotcomgame;

import dotcomobservers.DamageRate;
import dotcomobservers.SurvivingCells;

import java.util.ArrayList;

public class DotComBust {
    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    public void setupGame(){
        ShortDotCom one = new ShortDotCom();
        LongDotCom two = new LongDotCom();
        HeavyDotCom three = new HeavyDotCom();
        Submarine four = new Submarine();

        one.attach(new SurvivingCells(one));
        two.attach(new SurvivingCells(two));
        three.attach(new SurvivingCells(three));
        four.attach(new SurvivingCells(four));

        one.setName("Pets.com");
        two.setName("eToys.com");
        three.setName("Go2.com");
        four.setName("Dolphin.com");

        dotComsList.add(one);
        dotComsList.add(two);
        dotComsList.add(three);
        dotComsList.add(four);

        ArrayList<String> newLocation;
        newLocation = helper.placeDotCom(one.size());
        one.setLocationCells(newLocation);
        newLocation = helper.placeDotCom(two.size());
        two.setLocationCells(newLocation);
        newLocation = helper.placeDotCom(three.size());
        three.setLocationCells(newLocation);
        newLocation = helper.placeDotCom(four.size());
        four.setLocationCells(newLocation);

        one.attach(new DamageRate(one));
        two.attach(new DamageRate(two));
        three.attach(new DamageRate(three));
        four.attach(new DamageRate(four));

    }

    public void startPlaying(){
        while (!dotComsList.isEmpty()){
            String userGuess = helper.getUserInputRandom("Enter a guess");
            this.checkUserGuess(userGuess);
        }

        this.finishGame();
    }

    public void checkUserGuess(String userGuess){
        numOfGuesses++;
        String result = "";
        for (int x = 0; x < dotComsList.size(); x++){
            result = dotComsList.get(x).checkYourself(userGuess);
            if (result.equals("hit")){
                result += " " + dotComsList.get(x).getName();
                break;
            }

            else if (result.equals("kill")){
                result += " " + dotComsList.get(x).getName();
                dotComsList.remove(x);
                break;
            }

            else if (result.equals("hit, but")){
                result += " " + dotComsList.get(x).getName() + " has been dived";
                break;
            }

        }

        if (!result.equals("miss"))
            System.out.println(result);
    }

    private void finishGame(){
        System.out.println("All Dot Coms are dead!");
        if (numOfGuesses <= 18)
            System.out.println("It only took you " + numOfGuesses + " guesses.");

        else
            System.out.println("Took you long enough. " + numOfGuesses + " guesses.");
    }
}
