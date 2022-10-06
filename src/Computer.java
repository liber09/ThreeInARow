import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player {
    private int computerDifficultyLevel;
    private String lastSelectedPosition = "";
    private Random rnd = new Random();
    private int bestCol = 0;
    private int bestRow = 0;
    private ArrayList<String> triedPositions = new ArrayList<String>();

    public Computer() {
        super();
    }

    public void setComputerDifficultyLevel(int difficultyLevel) {
        this.computerDifficultyLevel = difficultyLevel;
    }

    public int getComputerDifficultyLevel() {
        return computerDifficultyLevel;
    }

    /*
        when computer is successful with a move we store that number here
        and clear the list of numbers the computer has attempted this turn
        because we store each attempted number to avoid an infinite loop where no
        possible numbers are left for the computer.
     */
    public void setLastSelectedPosition(String lastSelectedPosition) {
        this.lastSelectedPosition = lastSelectedPosition;
        triedPositions.clear();
    }

    //Get the latest position that was selected by the computer
    public String getLastSelectedPosition() {
        return lastSelectedPosition;
    }

    /*
    Method that handles the computer turn depending on
    chosen difficulty level the computer randomizes in the easiest
    and has an improved AI in medium and even more improved in Hard.
     */
    public String ComputerTurn(ExtendableGamingBoard currentBoard, int playedTurns, String opponentSign) {
        if (getComputerDifficultyLevel() == 1) {
            return "A" + rnd.nextInt(currentBoard.getSize()) + "B" + rnd.nextInt(currentBoard.getSize());
        }
        if (getComputerDifficultyLevel() == 2) {
            if (getLastSelectedPosition().equals("")) {
                String randomPosition = "A" + rnd.nextInt(currentBoard.getSize()) + "B" + rnd.nextInt(currentBoard.getSize());
                return randomPosition;
            }
            return bestComputerMoveLev2(getLastSelectedPosition(), currentBoard, playedTurns, opponentSign);
        }
        if (getComputerDifficultyLevel() == 3) {
            return bestComputerMoveLev3(currentBoard, playedTurns, opponentSign);
        }
        return "A0B0";
    }

    /*
        This method calculates which positions that
        are next to the last one the computer selected.
     */
    private String bestComputerMoveLev2(String lastSelectedPosition, ExtendableGamingBoard currentBoard, int playedTurns, String opponentSign) {
        String nextToSelfPosition = "";
        if (playedTurns % 2 == 0) {
            String nextToOpponentPosition = "";
            for (int i = 0; i < currentBoard.getSize(); i++) {
                for (int j = 0; j < currentBoard.getSize(); j++) {
                    if (currentBoard.board[i][j].equals(opponentSign)) {
                        if ((j + 1) < currentBoard.getSize() && currentBoard.board[i][(j + 1)].equals("_")) {
                            nextToOpponentPosition = "A" + i + "B" + (j + 1);
                        } else if ((i + 1) < currentBoard.getSize() && currentBoard.board[(i + 1)][(j)].equals("_")) {
                            nextToOpponentPosition = "A" + (i + 1) + "B" + j;
                        } else if ((i + 1) < currentBoard.getSize() && (j + 1) < currentBoard.getSize() && currentBoard.board[(i + 1)][(j + 1)].equals("_")) {
                            nextToOpponentPosition = "A" + (i + 1) + "B" + (j + 1);
                        } else {
                            nextToOpponentPosition = "A" + rnd.nextInt(currentBoard.getSize()) + "B" + rnd.nextInt(currentBoard.getSize());
                        }

                    }
                }
            }
            return nextToOpponentPosition;
        } else {
            for (int i = 0; i < currentBoard.getSize(); i++) {
                for (int j = 0; j < currentBoard.getSize(); j++) {
                    if (currentBoard.board[i][j].equals(this.getSign())) {
                        if ((j + 1) < currentBoard.getSize() && currentBoard.board[i][(j + 1)].equals("_")) {
                            nextToSelfPosition = "A" + i + "B" + (j + 1);
                        } else if ((i + 1) < currentBoard.getSize() && currentBoard.board[(i + 1)][(j)].equals("_")) {
                            nextToSelfPosition = "A" + (i + 1) + "B" + j;
                        } else if ((i + 1) < currentBoard.getSize() && (j + 1) < currentBoard.getSize() && currentBoard.board[(i + 1)][(j + 1)].equals("_")) {
                            nextToSelfPosition = "A" + (i + 1) + "B" + (j + 1);
                        } else {
                            nextToSelfPosition = "A" + rnd.nextInt((currentBoard.getSize() - 1)) + "B" + rnd.nextInt((currentBoard.getSize() - 1));
                        }

                    }
                }
            }
        }
        return nextToSelfPosition;
    }

    /*
    This is the hardest computer level. In this level the computer
    will try to block the opponent from winning the game.
    First the AI will try to win itself and if that is not possible
    the AI will try to block the opponent from winning the game.
     */
    private String bestComputerMoveLev3(ExtendableGamingBoard currentBoard, int playedTurns, String opponentSign) {
        if (currentBoard.getSize() == 3) {
            String bestMove = findBestMove(currentBoard, opponentSign);
            return bestMove;
        } else {
            return level3ComputerTryingToBlock(currentBoard, opponentSign, playedTurns);
        }
    }

    private String level3ComputerTryingToBlock(ExtendableGamingBoard currentBoard, String opponentSign, int playedTurns) {
        String positionToBlock = "";
        int score = 0;
        if (playedTurns <= 2) {
            //Should first try to take center position otherwise close to center
            int middlePosition = currentBoard.getSize() / 2;
            if (currentBoard.board[middlePosition][middlePosition].equals("_")) {
                return "A" + middlePosition + "B" + middlePosition;
            } else {
                int row = rnd.nextInt(2);
                int column = rnd.nextInt(2);
                return "A" + row + "B" + column;
            }
        }
        //HorizontalCheck
        //firstNumber is row = horizontal, second number is column = vertical
        //since we change column for each iteration this loop checks horizontally
        for (int i = 0; i < currentBoard.getSize(); i++) {
            for (int j = 0; j < currentBoard.getSize(); j++) {
                //if the inner loop has not found any opponent signs before
                //reaching NumberInRowToWin, we have a winner.
                if (currentBoard.board[i][j].equals(opponentSign)) {
                    score++;
                    if (score == currentBoard.getNumberInRowToWin() - 2) {
                        if (j + 1 <= currentBoard.getSize() - 1) {
                            positionToBlock = "A" + i + "B" + (j + 1);
                            if (triedPositions.contains(positionToBlock)) {
                                positionToBlock = "";
                            } else {
                                triedPositions.add(positionToBlock);
                            }
                        }
                    }
                } else {
                    score = 0;
                }
            }
            score = 0;
        }

        //Vertical check
        //firstNumber is row = horizontal, second number is column = vertical
        //since we change column for each iteration this loop checks vertically
        for (int i = 0; i < currentBoard.getSize(); i++) {
            for (int j = 0; j < currentBoard.getSize(); j++) {
                if (currentBoard.board[j][i].equals(opponentSign)) {
                    score++;
                    //if the inner loop has not found any opponent signs before
                    //reaching NumberInRowTOWin, we have a winner.
                    if (score == currentBoard.getNumberInRowToWin() - 2) {
                        if ((j + 1) < currentBoard.getSize() - 1) {
                            positionToBlock = "A" + (j + 1) + "B" + i;
                            if (triedPositions.contains(positionToBlock)) {
                                positionToBlock = "";
                            } else {
                                triedPositions.add(positionToBlock);
                            }
                        }
                    }
                } else {
                    score = 0;
                }

            }
            score = 0;
        }

        int d;
        //Diagonal Checks
        for (d = 0; d < currentBoard.getSize(); d++) {
            if (!currentBoard.board[d][d].equals(opponentSign)) {
                break;
            }
        }
        if (d == currentBoard.getNumberInRowToWin() - 2) {
            positionToBlock = "A" + (d + 1) + "B" + (d + 1);
            if (triedPositions.contains(positionToBlock)) {
                positionToBlock = "";
            } else {
                triedPositions.add(positionToBlock);
            }
        }
        /*
            Checks reverse diagonally. Starting at top left corner iterating one
            row at the time. When we find an opponent sign, we check if there is another one
            at row+1,column-1 and if so computer try to block by placing a
            sign at row+2 and column-2. Also we store each block attempt in a
            list and check each new block attempt towards that list. If the next block
            attempt has already been checked then it is discarded next time
            so we can find new positions to block.
         */

        for (int i = 0; 0 < currentBoard.getSize(); i++) {
            if(positionToBlock != ""){
                break;
            }
            for (int j = 0; j < currentBoard.getSize(); j++) {
                if (currentBoard.board[i][j].equals(opponentSign)) {
                    if (i + 1 > 0 && j - 1 < currentBoard.getSize() - 1) {
                        if (currentBoard.board[i + 1][j - 1].equals(opponentSign)) {
                            if (i + 2 > 0 && j - 2 < currentBoard.getSize() - 1) {
                                positionToBlock = "A" + (i+2) + "B" + (j - 2);
                                if (triedPositions.contains(positionToBlock)) {
                                    positionToBlock = "";
                                } else {
                                    triedPositions.add(positionToBlock);
                                }
                            }
                        }
                    }
                }
            }
        }
            if (positionToBlock != "") {
                return positionToBlock;
            } else {
                positionToBlock = "A" + rnd.nextInt((currentBoard.getSize() - 1)) + "B" + rnd.nextInt(currentBoard.getSize() - 1);
                triedPositions.add(positionToBlock);
                return positionToBlock;
            }
        }






    private String findBestMove(ExtendableGamingBoard currentBoard, String opponentSign)
    {
        int bestVal = -1000;

        this.bestCol = -1;
        this.bestRow = -1;

        // Traverse all cells, evaluate minimax function
        // for all empty cells. And return the cell
        // with optimal value.
        for (int i = 0; i < currentBoard.getSize(); i++) {
            for (int j = 0; j < currentBoard.getSize(); j++) {
                // Check if cell is empty
                if (currentBoard.board[i][j].equals("_"))
                {
                    // Make the move
                    currentBoard.board[i][j] = this.getSign();
                    // compute evaluation function for this
                    // move.
                    int moveVal = minimax(currentBoard, 0, false,opponentSign);

                    // Undo the move
                    currentBoard.board[i][j] = "_";

                    // If the value of the current move is
                    // more than the best value, then update
                    // best/
                    if (moveVal > bestVal)
                    {
                        bestRow = i;
                        bestCol = j;
                        bestVal = moveVal;
                    }
                }
            }
        }

        System.out.printf("The value of the best Move " +
                "is : %d\n\n", bestVal);
        String bestMove ="A"+bestRow+"B"+bestCol;
        return bestMove;
    }
    private Boolean areThereMovesLeft(ExtendableGamingBoard currentBoard){
        for(int i=0;i<currentBoard.getSize();i++){
            for(int j=0;j<currentBoard.getSize();j++){
                if(currentBoard.board[i][j].equals("_")){
                    return true;
                }
            }
        }
        return false;
    }

    private int evaluate(ExtendableGamingBoard currentBoard, String opponentSign)
    {
        // Checking for Rows for X or O victory.
        for (int row = 0; row < currentBoard.getSize(); row++) {
            for(int col = 0;col< currentBoard.getSize();col++){
                if (currentBoard.board[row][col] == currentBoard.board[row][col] &&
                        currentBoard.board[row][col] == currentBoard.board[row][col])
                {
                    if (currentBoard.board[row][col].equals(this.getSign())
                    )
                        return +10;
                    else if (currentBoard.board[row][col].equals(opponentSign))
                        return -10;
                }
            }
        }

        // Checking for Columns for X or O victory.
        for (int col = 0; col < currentBoard.getSize(); col++) {
            for (int row = 0;row <currentBoard.getSize();row++){
                if (currentBoard.board[row][col] == currentBoard.board[row][col] &&
                        currentBoard.board[row][col] == currentBoard.board[row][col])
                {
                    if (currentBoard.board[row][col].equals(this.getSign()))
                        return +10;

                    else if (currentBoard.board[row][col].equals(opponentSign))
                        return -10;
                }
            }
        }

        // Checking for Diagonals for X or O victory.
        for (int i = 0;i< currentBoard.getSize();i++){
            if (i+1<currentBoard.getSize()-1){
                if (currentBoard.board[i][i] == currentBoard.board[i+1][i+1] && currentBoard.board[1][1] == currentBoard.board[2][2])
                {
                    if (currentBoard.board[i][i].equals(this.getSign()))
                        return +10;
                    else if (currentBoard.board[i][i].equals(opponentSign))
                        return -10;
                }

                if(i+2<currentBoard.getSize()-1){
                    if (currentBoard.board[i][i+2] == currentBoard.board[i+1][i+1] && currentBoard.board[1][1] == currentBoard.board[2][0])
                    {
                        if (currentBoard.board[i][i+2].equals(this.getSign()))
                            return +10;
                        else if (currentBoard.board[i][i+2].equals(opponentSign))
                            return -10;
                    }
                }
            }
        }
        // Else if none of them have won then return 0
        return 0;
    }

    int minimax(ExtendableGamingBoard currentBoard, int depth, Boolean isMax,String opponentSign)
    {
        int score = evaluate(currentBoard,opponentSign);

        // If Maximizer has won the game
        // return his/her evaluated score
        if (score == 10)
            return score;

        // If Minimizer has won the game
        // return his/her evaluated score
        if (score == -10)
            return score;

        // If there are no more moves and
        // no winner then it is a tie
        if (areThereMovesLeft(currentBoard) == false)
            return 0;

        // If this maximizer's move
        if (isMax)
        {
            int best = -1000;

            // Traverse all cells
            for (int i = 0; i < currentBoard.getSize(); i++) {
                for (int j = 0; j < currentBoard.getSize(); j++) {
                    // Check if cell is empty
                    if (currentBoard.board[i][j].equals("_"))
                    {
                        // Make the move
                        currentBoard.board[i][j] = this.getSign();

                        // Call minimax recursively and choose
                        // the maximum value
                        best = Math.max(best, minimax(currentBoard,
                                depth + 1, !isMax,opponentSign));

                        // Undo the move
                        currentBoard.board[i][j] = "_";
                    }
                }
            }
            return best;
        }

        // If this minimizer's move
        else
        {
            int best = 1000;

            // Traverse all cells
            for (int i = 0; i < currentBoard.getSize(); i++) {
                for (int j = 0; j < currentBoard.getSize(); j++) {
                    // Check if cell is empty
                    if (currentBoard.board[i][j].equals("_"))
                    {
                        // Make the move
                        currentBoard.board[i][j] = opponentSign;

                        // Call minimax recursively and choose
                        // the minimum value
                        best = Math.min(best, minimax(currentBoard,
                                depth + 1, !isMax,opponentSign));

                        // Undo the move
                        currentBoard.board[i][j] = "_";
                    }
                }
            }
            return best;
        }
    }
}
