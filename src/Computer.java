import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player{
    private int computerDifficultyLevel;
    private String lastSelectedPosition = "";
    private Random rnd = new Random();
    private ArrayList<String> triedPositions = new ArrayList<String>();
    private int NumberOfAttempts = 0;


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
                String randomPosition = "A"+rnd.nextInt(currentBoard.getSize())+"B"+rnd.nextInt(currentBoard.getSize());
                return randomPosition;
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
        String newRandomizedPosition = "";
        if(NumberOfAttempts < 6){
            int row = Integer.parseInt(lastSelectedPosition.substring(lastSelectedPosition.lastIndexOf("A")+1,lastSelectedPosition.lastIndexOf("B")));
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
            newRandomizedPosition = "A"+randomRow+"B"+randomColumn;
            NumberOfAttempts++;
            return newRandomizedPosition;
        }
        randomRow = rnd.nextInt(currentBoard.getSize());
        randomColumn = rnd.nextInt(currentBoard.getSize());
        newRandomizedPosition = "A"+randomRow+"B"+randomColumn;
        NumberOfAttempts = 0;
        return newRandomizedPosition;

    }
    /*
    This is the hardest computer level. In this level the computer
    will try to block the opponent from winning the game.
    First the AI will try to win itself and if that is not possible
    the AI will try to block the opponent from winning the game.
     */
    private String bestComputerMoveLev3(ExtendableGamingBoard currentBoard, int playedTurns, String opponentSign){
        String positionToWin = "";
        String positionToBlock = "";

        //Best position is middle so calculate middle of board and try to place there.
        int middleOfBoard = currentBoard.getSize()/2;
        String middlePosition = "A"+ middleOfBoard+"B"+middleOfBoard;
        if(!triedPositions.contains(middlePosition)){
            triedPositions.add(middlePosition);
            return middlePosition;
        }
        //second best are corners so try those (when board is 3X3)
        if(currentBoard.getSize() == 3){
            if(playedTurns <= 2){
                String newPosition;
                //There are 4 corners 0 = topleft (A0B0) 1= topRight (A0B2) 2 = leftBottom(A2B0) 3 rightBottom(A2B2)
                int randomPosition = rnd.nextInt(4);
                switch(randomPosition){
                    case 0:
                        newPosition = "A0B0";
                        triedPositions.add(newPosition);
                        return newPosition;
                    case 1:
                        newPosition = "A0B2";
                        triedPositions.add(newPosition);
                        return newPosition;
                    case 2:
                        newPosition = "A2B0";
                        triedPositions.add(newPosition);
                        return newPosition;
                    case 3:
                        newPosition = "A2B2";
                        triedPositions.add(newPosition);
                        return newPosition;
                }
            }else{
                //First see if computer can win game
                positionToWin = checkIfPlayerCanWin(currentBoard, this.getSign(),opponentSign);
            if (!positionToWin.equals("")){
                    return positionToWin;
                }
                positionToBlock = checkIfPlayerCanWin(currentBoard,opponentSign,this.getSign());
                if(!positionToBlock.equals("")){
                    return positionToBlock;
                }

            }
        }
        int randomRow = rnd.nextInt(currentBoard.getSize());
        int randomColumn = rnd.nextInt(currentBoard.getSize());
        String randomPosition = "A"+randomRow+"B"+randomColumn;
        return randomPosition;
    }

    private String checkIfPlayerCanWin(ExtendableGamingBoard currentBoard, String signToCheck, String opponentSign) {
        String winningPosition = "";
        int inARowCount = 0;
        String emptyRowIndex = "";
        String emptyColumnIndex = "";
        boolean hasAWinner = false;

        //HorizontalCheck
        for(int i=0;i < currentBoard.getSize();i++)
            //firstnumber is row = horizontal, second number is column = vertical
            //since we change column for each iteration this loop checks horizontally
            if(currentBoard.board[0][i].equals(signToCheck)){
                int j;
                for(j=1;j< currentBoard.getSize();j++){
                    if (!currentBoard.board[j][i].equals(signToCheck)){
                        break;
                    }
                }
                //if the inner loop has not found any opponent signs before
                //reaching NumberInRowToWin-1, we have a winningPosition.
                if(j == currentBoard.getSize()-1){
                    winningPosition = "A"+(j+1)+"B"+i;
                    return winningPosition;
                }
            }
        //Vertical check
        for(int i=0;i < currentBoard.getSize();i++)
            //firstNumber is row = horizontal, second number is column = vertical
            //since we change column for each iteration this loop checks horizontally
            if(currentBoard.board[i][0].equals(signToCheck)){
                int j;
                for(j=1;j< currentBoard.getSize();j++){
                    if (!currentBoard.board[i][j].equals(signToCheck)){
                        break;
                    }
                }
                //if the inner loop has not found any opponent signs before
                //reaching NumberInRowTOWin, we have a winner.
                if(j == currentBoard.getSize()-1){
                    winningPosition = "A"+i+"B"+(j+1);
                    return winningPosition;
                }
            }
        int d;
        //Diagonal Checks
        for(d=0;d<currentBoard.getSize();d++){
            if(!currentBoard.board[d][d].equals(signToCheck)){
                break;
            }
        }
        if(d== currentBoard.getSize()-1){
            winningPosition = "A"+(d+1)+"B"+(d+1);
            return winningPosition;
        }
        //Checks reverse diagonally. Starting at top right position
        //and then reversing one to the left and one down.
        for(d=0;d< currentBoard.getSize();d++){
            if(!currentBoard.board[d][(currentBoard.getSize()-1)-d].equals(signToCheck)){
                break;
            }
        }
        if(d== currentBoard.getSize()){
            winningPosition = "A"+(d-1)+"B"+(d-1);
            return winningPosition;
        }
        return "";
    }
}
