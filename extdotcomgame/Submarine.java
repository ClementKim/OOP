package extdotcomgame;

public class Submarine extends DotCom{
    private boolean underwater = false;

    public int size(){
        return 3;
    }

    public String checkYourself(String userInput){
        String result = "miss";
        int index = locationCells.indexOf(userInput);

        if (index >= 0){
            if (!underwater) {
                locationCells.remove(index);
                if (locationCells.isEmpty()) {
                    result = "kill";
                    System.out.println("You sunk " + name + " : ( ");
                } else {
                    result = "hit";
                    underwater = true;
                    this.notifyObservers();
                }
            }

            else {
                result = "hit, but";
            }
        }

        if ((result.equals("hit, but") && underwater) || (result.equals("miss") && underwater))
            underwater = false;

        return result;
    }
}
