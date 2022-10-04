import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player{
    private int computerDifficultyLevel;
    private String lastSelectedPosition;
    private Random rnd = new Random();
    private ArrayList<String> triedPositions = new ArrayList<String>();

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
    public void setLastSelectedPosition(String lastSelectedPosition){
        this.lastSelectedPosition = lastSelectedPosition;
        triedPositions.clear();
    }
    public String getLastSelectedPosition(){
        return lastSelectedPosition;
    }

    /*
    Method that handles the computer turn depending on
    chosen difficulty level the computer randomizes in the easiest
    and has an improved AI in medium and even more improved in Hard.
     */
    public String ComputerTurn(ExtendableGamingBoard currentBoard, int playedTurns, String opponentSign){
        if (getComputerDifficultyLevel() == 1){
            return "A"+rnd.nextInt(currentBoard.getSize())+"B"+rnd.nextInt(currentBoard.getSize());
        }
        if(getComputerDifficultyLevel() == 2){
            if(getLastSelectedPosition().equals("")){
                return "A"+rnd.nextInt(currentBoard.getSize())+"B"+rnd.nextInt(currentBoard.getSize());
            }
            return bestComputerMoveLev2(getLastSelectedPosition(),currentBoard);
        }
        if(getComputerDifficultyLevel() == 3){
            return bestComputerMoveLev3(currentBoard, playedTurns, opponentSign);
        }
        return "A0B0";
    }
    /*
        This method calculates which positions that
        are next to the last one the computer selected.
     */
    private String bestComputerMoveLev2(String lastSelectedPosition,ExtendableGamingBoard currentBoard){
        int randomRow;
        int randomColumn;
        int row = Integer.parseInt(lastSelectedPosition.substring(1,lastSelectedPosition.lastIndexOf("B")-1));
        int column = Integer.parseInt(lastSelectedPosition.substring(lastSelectedPosition.lastIndexOf("B")+1));

        if ((row-1) < 0){
            //out of bounds, keep existing row as min value for random
            randomRow = rnd.nextInt(row,row+1);
        }else if ((row+1) > currentBoard.getSize()){
            //out of bounds, keep existing row as min value for random
            randomRow = rnd.nextInt(row-1,row);
        }else{
            randomRow = rnd.nextInt(row-1,row+1);
        }
        if ((column-1) < 0){
            //out of bounds, keep existing column as min value for random
            randomColumn = rnd.nextInt(column,column+1);
        }else if ((column+1) > currentBoard.getSize()){
            //keep column as max value
            randomColumn = rnd.nextInt(column-1,column);
        }else{
            randomColumn = rnd.nextInt(column-1,column+1);
        }
        String newRandomizedPosition = "A"+randomRow+"B"+randomColumn;
        triedPositions.add(newRandomizedPosition);
        return newRandomizedPosition;
    }
    private String bestComputerMoveLev3(ExtendableGamingBoard currentBoard, int playedTurns, String opponentSign){
        int rndTempNr;
        int numberToWin = 0;
        int numberToBlock = 0;
        //Best position is middle so begin by returning 5
        if(!triedPositions.contains(5)){
            triedPositions.add(5);
            return 5;
        }
        //second best are corners so try those
        if(playedTurns <= 2){
            if(triedPositions.contains(1) && triedPositions.contains(3) && triedPositions.contains(7) && triedPositions.contains(9)){
                return rnd.nextInt(9) + 1;
            }
            do {
                rndTempNr = rnd.nextInt(9) + 1;
            } while (rndTempNr == 5 || rndTempNr == 2 || rndTempNr == 4 || rndTempNr == 6 || rndTempNr == 8);
            triedPositions.add(rndTempNr);
            return rndTempNr;
        }else{
            //First see if computer can win game
            numberToWin = checkIfComputerCanWin(currentBoard);
            if (numberToWin != 0){
                return numberToWin;
            }
            numberToBlock = checkIfOpponentCanWin(currentBoard,sign);
            if(numberToBlock != 0){
                return numberToBlock;
            }
            return rnd.nextInt(9) + 1;
        }
    }

    private int checkIfOpponentCanWin(char[][] currentBoard, char player1Sign) {
        int winningPosition = 0;
        //Vertical checking
        if(currentBoard[0][0] == player1Sign && currentBoard[0][2] == player1Sign && currentBoard[0][4] == '_'){
            winningPosition = 3;
        } else if (currentBoard[0][0] == player1Sign && currentBoard[0][4] == player1Sign && currentBoard[0][2] == '_') {
            winningPosition = 2;
        }else if (currentBoard[0][2] == player1Sign && currentBoard[0][4] == player1Sign && currentBoard[0][0] == '_') {
            winningPosition = 1;
        }
        if(currentBoard[1][0] == player1Sign && currentBoard[1][2] == player1Sign && currentBoard[1][4] == '_'){
            winningPosition = 6;
        } else if (currentBoard[1][0] == player1Sign && currentBoard[1][4] == player1Sign && currentBoard[1][2] == '_') {
            winningPosition = 5;
        }else if (currentBoard[1][2] == player1Sign && currentBoard[1][4] == player1Sign && currentBoard[1][0] == '_') {
            winningPosition = 4;
        }
        if(currentBoard[2][0] == player1Sign && currentBoard[2][2] == player1Sign && currentBoard[2][4] == ' '){
            winningPosition = 9;
        } else if (currentBoard[2][0] == player1Sign && currentBoard[2][4] == player1Sign && currentBoard[2][2] == ' ') {
            winningPosition = 8;
        }else if (currentBoard[2][2] == player1Sign && currentBoard[2][4] == player1Sign && currentBoard[2][0] == ' ') {
            winningPosition = 7;
        }
        //Horizontal checking
        if(currentBoard[0][0] == player1Sign && currentBoard[1][0] == player1Sign && currentBoard[2][0] == ' '){
            winningPosition = 7;
        } else if (currentBoard[0][0] == player1Sign && currentBoard[2][0] == player1Sign && currentBoard[1][0] == '_') {
            winningPosition = 4;
        }else if (currentBoard[1][0] == player1Sign && currentBoard[2][0] == player1Sign && currentBoard[0][0] == '_') {
            winningPosition = 1;
        }
        if(currentBoard[0][2] == player1Sign && currentBoard[1][2] == player1Sign && currentBoard[2][2] == ' '){
            winningPosition = 8;
        } else if (currentBoard[0][2] == player1Sign && currentBoard[2][2] == player1Sign && currentBoard[1][2] == '_') {
            winningPosition = 5;
        }else if (currentBoard[1][2] == player1Sign && currentBoard[2][2] == player1Sign && currentBoard[0][2] == '_') {
            winningPosition = 2;
        }
        if(currentBoard[0][4] == player1Sign && currentBoard[1][4] == player1Sign && currentBoard[2][4] == ' '){
            winningPosition = 9;
        } else if (currentBoard[0][4] == player1Sign && currentBoard[2][4] == player1Sign && currentBoard[1][4] == '_') {
            winningPosition = 6;
        }else if (currentBoard[1][4] == player1Sign && currentBoard[2][4] == player1Sign && currentBoard[0][4] == '_') {
            winningPosition = 3;
        }
        //DiagonalChecking
        if(currentBoard[0][0] == player1Sign && currentBoard[1][2] == player1Sign && currentBoard[2][4] == ' '){
            winningPosition = 9;
        } else if (currentBoard[0][0] == player1Sign && currentBoard[2][4] == player1Sign && currentBoard[1][2] == '_') {
            winningPosition = 5;
        }else if (currentBoard[1][2] == player1Sign && currentBoard[2][4] == player1Sign && currentBoard[0][0] == '_') {
            winningPosition = 1;
        }
        if(currentBoard[0][4] == player1Sign && currentBoard[1][2] == player1Sign && currentBoard[2][0] == ' '){
            winningPosition = 7;
        } else if (currentBoard[0][4] == player1Sign && currentBoard[2][0] == player1Sign && currentBoard[1][2] == '_') {
            winningPosition = 5;
        }else if (currentBoard[1][2] == player1Sign && currentBoard[2][0] == player1Sign && currentBoard[0][4] == '_') {
            winningPosition = 3;
        }
        return winningPosition;
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
