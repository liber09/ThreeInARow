import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player{
    private int computerDifficultyLevel;
    private int lastSelectedNumber;
    private Random rnd = new Random();
    private ArrayList<Integer> triedNumbers = new ArrayList<Integer>();

    public Computer(){
        super();
    }

    public void setComputerDifficultyLevel(int difficultyLevel){
        this.computerDifficultyLevel = difficultyLevel;
    }
    public int getComputerDifficultyLevel(){
        return computerDifficultyLevel;
    }
    /*
        when computer is successful with a move we store that number here
        and clear the list of numbers the computer has attempted this turn
        because we store each attempted number to avoid an infinite loop where no
        possible numbers are left for the computer.
     */
    public void setLastSelectedNumber(int lastSelectedNumber){
        this.lastSelectedNumber = lastSelectedNumber;
        triedNumbers.clear();
    }
    public int getLastSelectedNumber(){
        return lastSelectedNumber;
    }

    /*
    Method that handles the computer turn depending on
    chosen difficulty level the computer randomizes in the easiest
    and has an improved AI in medium and even more improved in Hard.
     */
    public int ComputerTurn(char[][] currentBoard, int playedTurns, char sign){
        if (getComputerDifficultyLevel() == 1){
            return rnd.nextInt(9)+1;
        }
        if(getComputerDifficultyLevel() == 2){
            if(getLastSelectedNumber( )== 0){
                return rnd.nextInt(9)+1;
            }
            return bestComputerMoveLev2(getLastSelectedNumber());
        }
        if(getComputerDifficultyLevel() == 3){
            return bestComputerMoveLev3(currentBoard, playedTurns, sign);
        }
        return 0;
    }
    /*
        This method calculates which positions that
        are next to the last one the computer selected.
     */
    private int bestComputerMoveLev2(int lastSelectedNumber){
        int rndTempNr;
        switch (lastSelectedNumber) {
            //1 = top left corner. that means 2, 4 or 5 are
            //possible best options for next move.
            // but a random function makes the choice so that
            //it won't be obvious which one the computer will select.
            case 1:
                if (triedNumbers.contains(2) && triedNumbers.contains(4) && triedNumbers.contains(5)) {
                    return rnd.nextInt(9) + 1;
                }
                do {
                    rndTempNr = rnd.nextInt(9) + 1;
                } while (rndTempNr == 1 || rndTempNr == 3 || rndTempNr == 6 || rndTempNr == 7 || rndTempNr == 8 || rndTempNr == 9);
                triedNumbers.add(rndTempNr);
                return rndTempNr;
            case 2:
                if (triedNumbers.contains(1) && triedNumbers.contains(4) && triedNumbers.contains(5) && triedNumbers.contains(6) && triedNumbers.contains(3)) {
                    return rnd.nextInt(9) + 1;
                }
                do {
                    rndTempNr = rnd.nextInt(9) + 1;
                } while (rndTempNr == 2 || rndTempNr == 7 || rndTempNr == 8 || rndTempNr == 9);
                triedNumbers.add(rndTempNr);
                return rndTempNr;
            case 3:
                if (triedNumbers.contains(2) && triedNumbers.contains(5) && triedNumbers.contains(6)) {
                    return rnd.nextInt(9) + 1;
                }
                do {
                    rndTempNr = rnd.nextInt(9) + 1;
                } while (rndTempNr == 3 || rndTempNr == 1 || rndTempNr == 4 || rndTempNr == 7 || rndTempNr == 8 || rndTempNr == 9);
                triedNumbers.add(rndTempNr);
                return rndTempNr;
            case 4:
                if (triedNumbers.contains(1) && triedNumbers.contains(2) && triedNumbers.contains(5) && triedNumbers.contains(7) && triedNumbers.contains(8)) {
                    return rnd.nextInt(9) + 1;
                }
                do {
                    rndTempNr = rnd.nextInt(9) + 1;
                } while (rndTempNr == 4 || rndTempNr == 3 || rndTempNr == 6 || rndTempNr == 9);
                triedNumbers.add(rndTempNr);
                return rndTempNr;
            case 5:
                do {
                    rndTempNr = rnd.nextInt(9) + 1;
                } while (rndTempNr == 5);
                return rndTempNr;
            case 6:
                if (triedNumbers.contains(2) && triedNumbers.contains(3) && triedNumbers.contains(5) && triedNumbers.contains(8) && triedNumbers.contains(9)) {
                    return rnd.nextInt(9) + 1;
                }
                do {
                    rndTempNr = rnd.nextInt(9) + 1;
                } while (rndTempNr == 6 || rndTempNr == 1 || rndTempNr == 4 || rndTempNr == 7);
                triedNumbers.add(rndTempNr);
                return rndTempNr;
            case 7:
                if (triedNumbers.contains(4) && triedNumbers.contains(5) && triedNumbers.contains(8)) {
                    return rnd.nextInt(9) + 1;
                }
                do {
                    rndTempNr = rnd.nextInt(9) + 1;
                } while (rndTempNr == 7 || rndTempNr == 1 || rndTempNr == 2 || rndTempNr == 3 || rndTempNr == 6 || rndTempNr == 9);
                triedNumbers.add(rndTempNr);
                return rndTempNr;
            case 8:
                if (triedNumbers.contains(4) && triedNumbers.contains(5) && triedNumbers.contains(6) && triedNumbers.contains(7) && triedNumbers.contains(9)) {
                    return rnd.nextInt(9) + 1;
                }
                do {
                    rndTempNr = rnd.nextInt(9) + 1;
                } while (rndTempNr == 8 || rndTempNr == 1 || rndTempNr == 2 || rndTempNr == 3);
                triedNumbers.add(rndTempNr);
                return rndTempNr;
            case 9:
                if (triedNumbers.contains(5) && triedNumbers.contains(6) && triedNumbers.contains(8)) {
                    return rnd.nextInt(9) + 1;
                }
                do {
                    rndTempNr = rnd.nextInt(9) + 1;
                } while (rndTempNr == 9 || rndTempNr == 1 || rndTempNr == 2 || rndTempNr == 3 || rndTempNr == 4 || rndTempNr == 7);
                triedNumbers.add(rndTempNr);
                return rndTempNr;
            default:
                return 0;
        }
    }
    private int bestComputerMoveLev3(char[][] currentBoard, int playedTurns, char sign){
        int rndTempNr;
        int numberToWin;
        //Best position is middle so begin by returning 5
        if(!triedNumbers.contains(5)){
            triedNumbers.add(5);
            return 5;
        }
        //second best are corners so try those
        if(playedTurns <= 2){
            if(triedNumbers.contains(1) && triedNumbers.contains(3) && triedNumbers.contains(7) &&triedNumbers.contains(9)){
                return rnd.nextInt(9) + 1;
            }
            do {
                rndTempNr = rnd.nextInt(9) + 1;
            } while (rndTempNr == 5 || rndTempNr == 2 || rndTempNr == 4 || rndTempNr == 6 || rndTempNr == 8);
            triedNumbers.add(rndTempNr);
            return rndTempNr;
        }else{
            //First see if computer can win game
            numberToWin = checkIfComputerCanWin(currentBoard);
        }
        return numberToWin;
    }

    private int checkIfComputerCanWin(char[][] currentBoard){
        int winningPosition = 0;
        //Vertical checking
        if(currentBoard[0][0] == this.getSign() && currentBoard[0][2] == this.getSign() && currentBoard[0][4] == '_'){
            winningPosition = 3;
        } else if (currentBoard[0][0] == this.getSign() && currentBoard[0][4] == this.getSign() && currentBoard[0][2] == '_') {
            winningPosition = 2;
        }else if (currentBoard[0][2] == this.getSign() && currentBoard[0][4] == this.getSign() && currentBoard[0][0] == '_') {
            winningPosition = 1;
        }
        if(currentBoard[1][0] == this.getSign() && currentBoard[1][2] == this.getSign() && currentBoard[1][4] == '_'){
            winningPosition = 6;
        } else if (currentBoard[1][0] == this.getSign() && currentBoard[1][4] == this.getSign() && currentBoard[1][2] == '_') {
            winningPosition = 5;
        }else if (currentBoard[1][2] == this.getSign() && currentBoard[1][4] == this.getSign() && currentBoard[1][0] == '_') {
            winningPosition = 4;
        }
        if(currentBoard[2][0] == this.getSign() && currentBoard[2][2] == this.getSign() && currentBoard[2][4] == ' '){
            winningPosition = 9;
        } else if (currentBoard[2][0] == this.getSign() && currentBoard[2][4] == this.getSign() && currentBoard[2][2] == ' ') {
            winningPosition = 8;
        }else if (currentBoard[2][2] == this.getSign() && currentBoard[2][4] == this.getSign() && currentBoard[2][0] == ' ') {
            winningPosition = 7;
        }
        //Horizontal checking
        if(currentBoard[0][0] == this.getSign() && currentBoard[1][0] == this.getSign() && currentBoard[2][0] == ' '){
            winningPosition = 7;
        } else if (currentBoard[0][0] == this.getSign() && currentBoard[2][0] == this.getSign() && currentBoard[1][0] == '_') {
            winningPosition = 4;
        }else if (currentBoard[1][0] == this.getSign() && currentBoard[2][0] == this.getSign() && currentBoard[0][0] == '_') {
            winningPosition = 1;
        }
        if(currentBoard[0][2] == this.getSign() && currentBoard[1][2] == this.getSign() && currentBoard[2][2] == ' '){
            winningPosition = 8;
        } else if (currentBoard[0][2] == this.getSign() && currentBoard[2][2] == this.getSign() && currentBoard[1][2] == '_') {
            winningPosition = 5;
        }else if (currentBoard[1][2] == this.getSign() && currentBoard[2][2] == this.getSign() && currentBoard[0][2] == '_') {
            winningPosition = 2;
        }
        if(currentBoard[0][4] == this.getSign() && currentBoard[1][4] == this.getSign() && currentBoard[2][4] == ' '){
            winningPosition = 9;
        } else if (currentBoard[0][4] == this.getSign() && currentBoard[2][4] == this.getSign() && currentBoard[1][4] == '_') {
            winningPosition = 6;
        }else if (currentBoard[1][4] == this.getSign() && currentBoard[2][4] == this.getSign() && currentBoard[0][4] == '_') {
            winningPosition = 3;
        }
        //DiagonalChecking
        if(currentBoard[0][0] == this.getSign() && currentBoard[1][2] == this.getSign() && currentBoard[2][4] == ' '){
            winningPosition = 9;
        } else if (currentBoard[0][0] == this.getSign() && currentBoard[2][4] == this.getSign() && currentBoard[1][2] == '_') {
            winningPosition = 5;
        }else if (currentBoard[1][2] == this.getSign() && currentBoard[2][4] == this.getSign() && currentBoard[0][0] == '_') {
            winningPosition = 1;
        }
        if(currentBoard[0][4] == this.getSign() && currentBoard[1][2] == this.getSign() && currentBoard[2][0] == ' '){
            winningPosition = 7;
        } else if (currentBoard[0][4] == this.getSign() && currentBoard[2][0] == this.getSign() && currentBoard[1][2] == '_') {
            winningPosition = 5;
        }else if (currentBoard[1][2] == this.getSign() && currentBoard[2][0] == this.getSign() && currentBoard[0][4] == '_') {
            winningPosition = 3;
        }
        return winningPosition;
    }
}
