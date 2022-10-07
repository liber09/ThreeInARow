import java.util.ArrayList;
import java.util.Random;
public class Computer extends Player {
    private int computerDifficultyLevel;
    private String lastSelectedPosition = "";
    private final Random RND = new Random();
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
            return "A" + RND.nextInt((currentBoard.getSize() + 1)) + "B" + RND.nextInt((currentBoard.getSize() + 1));
        }
        if (getComputerDifficultyLevel() == 2) {
            if (getLastSelectedPosition().equals("")) {
                String randomPosition = "A" + RND.nextInt((currentBoard.getSize() + 1)) + "B" + RND.nextInt((currentBoard.getSize() + 1));
                return randomPosition;
            }
            return bestComputerMoveLev2(getLastSelectedPosition(), currentBoard, playedTurns, opponentSign);
        }
        if (getComputerDifficultyLevel() == 3) {
            return bestComputersMoveLev3(currentBoard, playedTurns, opponentSign);
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
                            nextToOpponentPosition = "A" + RND.nextInt((currentBoard.getSize() + 1)) + "B" + RND.nextInt((currentBoard.getSize() + 1));
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
                            nextToSelfPosition = "A" + RND.nextInt((currentBoard.getSize() + 1)) + "B" + RND.nextInt((currentBoard.getSize() + 1));
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
    private String bestComputersMoveLev3(ExtendableGamingBoard currentBoard, int playedTurns, String opponentSign) {
        if (currentBoard.getSize() == 3) {
            String bestMove = classicBoardBestMove(currentBoard, opponentSign);
            if (bestMove.equals("")) {
                return "A" + (RND.nextInt(4)) + "B" + (RND.nextInt(4));
            }
            return bestMove;
        }
        return "A" + (RND.nextInt(4)) + "B" + (RND.nextInt(4));
    }

    private String classicBoardBestMove(ExtendableGamingBoard currentBoard, String opponentSign) {
        String positionToBlock = "";
        if(currentBoard.board[1][1].equals("_")){
            positionToBlock = "A1B1";
            triedPositions.add(positionToBlock);
            return positionToBlock;
        }
        //row1
        if(currentBoard.board[0][0].equals(opponentSign) && (currentBoard.board[0][1].equals(opponentSign))){
            positionToBlock = "A0B2";
            if(currentBoard.board[0][2].equals("_")){
                return positionToBlock;
            }
        }
        if(currentBoard.board[0][0].equals(opponentSign) && (currentBoard.board[0][2].equals(opponentSign))){
            positionToBlock = "A0B1";
            if(triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }
        if(currentBoard.board[0][1].equals(opponentSign) && (currentBoard.board[0][2].equals(opponentSign))){
            positionToBlock = "A0B0";
            if(triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }

        //row2
        if(currentBoard.board[1][0].equals(opponentSign) && (currentBoard.board[1][1].equals(opponentSign))){
            positionToBlock = "A1B2";
            if(triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }
        if(currentBoard.board[1][0].equals(opponentSign) && (currentBoard.board[1][2].equals(opponentSign))){
            positionToBlock = "A1B1";
            if(triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }
        if (currentBoard.board[2][1].equals(opponentSign) && (currentBoard.board[2][2].equals(opponentSign))){
            positionToBlock = "A2B0";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }
        //row3
        if (currentBoard.board[2][0].equals(opponentSign) && (currentBoard.board[2][1].equals(opponentSign))){
            positionToBlock = "A2B2";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }
        if (currentBoard.board[2][0].equals(opponentSign) && (currentBoard.board[2][2].equals(opponentSign))){
            positionToBlock = "A2B1";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }
        if (currentBoard.board[2][2].equals(opponentSign) && (currentBoard.board[2][1].equals(opponentSign))){
            positionToBlock = "A2B0";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }

        //column1
        if (currentBoard.board[0][0].equals(opponentSign) && (currentBoard.board[1][0].equals(opponentSign))){
            positionToBlock = "A2B0";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }
        if (currentBoard.board[0][0].equals(opponentSign) && (currentBoard.board[2][0].equals(opponentSign))){
            positionToBlock = "A1B0";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }
        if (currentBoard.board[1][0].equals(opponentSign) && (currentBoard.board[2][0].equals(opponentSign))){
            positionToBlock = "A0B0";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }

        //column2
        if (currentBoard.board[0][1].equals(opponentSign) && (currentBoard.board[1][1].equals(opponentSign))){
            positionToBlock = "A2B1";
            if (triedPositions.contains(positionToBlock)){
               positionToBlock="";
            }
        }
        if (currentBoard.board[0][1].equals(opponentSign) && (currentBoard.board[2][1].equals(opponentSign))){
            positionToBlock = "A1B1";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }
        if (currentBoard.board[1][1].equals(opponentSign) && (currentBoard.board[2][1].equals(opponentSign))){
            positionToBlock = "A0B1";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock ="";
            }
        }

        //column3
        if (currentBoard.board[0][2].equals(opponentSign) && (currentBoard.board[1][2].equals(opponentSign))){
            positionToBlock = "A2B2";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock ="";
            }
        }
        if (currentBoard.board[0][2].equals(opponentSign) && (currentBoard.board[2][2].equals(opponentSign))){
            positionToBlock = "A1B2";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock="";
            }
        }
        if (currentBoard.board[1][2].equals(opponentSign) && (currentBoard.board[2][2].equals(opponentSign))){
            positionToBlock = "A0B0";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock = "";
            }
        }

        //diagonal 1
        if (currentBoard.board[0][0].equals(opponentSign) && (currentBoard.board[1][1].equals(opponentSign))){
            positionToBlock = "A2B2";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock = "";
            }
        }
        if (currentBoard.board[0][0].equals(opponentSign) && (currentBoard.board[2][2].equals(opponentSign))){
            positionToBlock = "A1B1";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock ="";
            }
        }
        if (currentBoard.board[1][1].equals(opponentSign) && (currentBoard.board[2][2].equals(opponentSign))){
            positionToBlock = "A0B0";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock ="";
            }
        }

        //diagonal 2
        if (currentBoard.board[0][2].equals(opponentSign) && (currentBoard.board[1][1].equals(opponentSign))){
            positionToBlock = "A2B0";
            if (triedPositions.contains(positionToBlock)){
                positionToBlock ="";
            }

        }
        if (currentBoard.board[0][2].equals(opponentSign) && (currentBoard.board[2][0].equals(opponentSign))){
            positionToBlock = "A1B1";
            if (triedPositions.contains(positionToBlock)) {
                positionToBlock="";
            }
        }
        if (currentBoard.board[1][1].equals(opponentSign) && (currentBoard.board[2][0].equals(opponentSign))){
            positionToBlock = "A0B2";
            if (triedPositions.contains(positionToBlock)){
               positionToBlock="";
            }
        }
        if (!triedPositions.contains(positionToBlock)){
            triedPositions.add(positionToBlock);
        }
        return positionToBlock;
    }
}
