package extdotcomgame;

import java.util.*;

public class GameHelper {
    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private int gridSize = 49;
    private int [] grid = new int[gridSize];

    private int comCount = 0;
    private Random generator = new Random(10);
    public String getUserInput(String prompt){
        String inputLine = null;
        System.out.print(prompt + " ");

        Scanner input = new Scanner(System.in);
        inputLine = input.nextLine();

        return inputLine.toLowerCase();
    }

    public ArrayList<String> placeDotCom(int comSize){
        ArrayList<String> alphaCells = new ArrayList<String>();
        int [] coords = new int[comSize];
        int attempts = 0;
        boolean success = false;
        int location = 0;

        comCount++;
        int incr = ((comCount % 2) == 1) ? gridLength : 1;
        while (!success && attempts++ < 200){
            location = (int)(Math.random() * gridSize);
            int x = 0;
            success = true;
            while (success){
                if (grid[location] == 0){
                    coords[x++] = location;
                    if (x == comSize)
                        break;
                    location += incr;
                    if (location >= gridSize || (incr == 1 && (location % gridLength == 0)))
                        success = false;
                }
                else
                    success = false;
            }
        }

        int x = 0;
        while (x < comSize){
            grid[coords[x]] = 1;
            int row = coords[x] / gridLength;
            int column = coords[x] % gridLength;
            String temp = String.valueOf(alphabet.charAt(column));

            alphaCells.add(temp.concat(Integer.toString(row)));
            x++;
        }

        return alphaCells;
    }

    public String getUserInputRandom(String prompt){
        String inputline = null;

        int row = (int)(generator.nextDouble()*gridLength);
        int col = (int)(generator.nextDouble()*gridLength);

        String temp = String.valueOf(alphabet.charAt(row));
        temp = temp.concat(Integer.toString(col));

        return temp;
    }
}
