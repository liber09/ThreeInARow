import java.util.Random;

public class Computer extends Player{
    private int computerDifficultyLevel;
    private int lastSelectedNumber;
    private Random rnd = new Random();

    public Computer(){
        super();
    }

    public void setComputerDifficultyLevel(int difficultyLevel){
        this.computerDifficultyLevel = difficultyLevel;
    }
    public int getComputerDifficultyLevel(){
        return computerDifficultyLevel;
    }
    public void setLastSelectedNumber(int lastSelectedNumber){
        this.lastSelectedNumber = lastSelectedNumber;
    }
    public int getLastSelectedNumber(){
        return lastSelectedNumber;
    }

    /*
    Method that handles the computer turn depending on
    chosen difficulty level the computer randomizes in the easiest
    and has an improved AI in medium and even more improved in Hard.
     */
    public int ComputerTurn(){
        if (getComputerDifficultyLevel() == 1){
            return rnd.nextInt(1,10);
        }else{
            return rnd.nextInt(1,9); 
        }
    }
}
