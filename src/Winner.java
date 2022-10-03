import java.util.ArrayList;

public class Winner {
    static int numberOfRoundsPlayed = 0;
    public static boolean gameHasAWinner(ExtendableGamingBoard currentBoard, Player currentPlayer, ArrayList<Player> players, int playedTurns){
        boolean hasAWinner = false;
        //HorizontalCheck
        for(int i=0;i < currentBoard.getNumberInRowToWin();i++)
            //firstnumber is row = horizontal, second number is column = vertical
            //since we change column for each iteration this loop checks horizontally
            if(currentBoard.board[0][i].equals(currentPlayer.getSign())){
                int j;
                for(j=1;j< currentBoard.getNumberInRowToWin();j++){
                    if (!currentBoard.board[j][i].equals(currentPlayer.getSign())){
                        break;
                    }
                }
                //if the inner loop has not found any opponent signs before
                //reaching NumberInRowTOWin, we have a winner.
                if(j== currentBoard.getNumberInRowToWin()){
                    hasAWinner = true;
                }
        }
        //Vertical check
        for(int i=0;i < currentBoard.getNumberInRowToWin();i++)
            //firstnumber is row = horizontal, second number is column = vertical
            //since we change column for each iteration this loop checks horizontally
            if(currentBoard.board[i][0].equals(currentPlayer.getSign())){
                int j;
                for(j=1;j< currentBoard.getNumberInRowToWin();j++){
                    if (!currentBoard.board[i][j].equals(currentPlayer.getSign())){
                        break;
                    }
                }
                //if the inner loop has not found any opponent signs before
                //reaching NumberInRowTOWin, we have a winner.
                if(j== currentBoard.getNumberInRowToWin()){
                    hasAWinner = true;
                }
            }
        int d;
        //Diagonal Checks
        for(d=0;d<currentBoard.getNumberInRowToWin();d++){
            if(!currentBoard.board[d][d].equals(currentPlayer.getSign())){
                break;
            }
        }
        if(d== currentBoard.getNumberInRowToWin()){
            hasAWinner = true;
        }
        //Checks reverse diagonally. Starting at top right position
        //and then reversing one to the left and one down.
        for(d=0;d< currentBoard.getNumberInRowToWin();d++){
            if(!currentBoard.board[d][(currentBoard.getSize()-1)-d].equals(currentPlayer.getSign())){
                break;
            }
        }
        if(d== currentBoard.getNumberInRowToWin()){
            hasAWinner = true;
        }
        if(hasAWinner){
            currentPlayer.addNumberOfWins();
            if(currentPlayer.getName().equals("Computer")){
                System.out.println("Sorry but the computer won this game!");
            }else{
                System.out.println("Congratulations " + currentPlayer.getName() + " you won the game!");
            }
        }


        Boolean draw = isGameDraw(currentBoard);
        if(draw){
            System.out.println("This game was a tie!");
        }
        if(hasAWinner || draw){
            numberOfRoundsPlayed++;
            ArrayList<String> print = new ArrayList<>();
            int tieRounds = numberOfRoundsPlayed-players.get(0).getNumberOfWins()-players.get(1).getNumberOfWins();
            print.add("Results after " + numberOfRoundsPlayed + " played rounds\n" + players.get(0).getName() + "\t" + players.get(1).getName()+ "\n"+
            "  " + players.get(0).getNumberOfWins() + "        " + players.get(1).getNumberOfWins()+"\n"+
            "Number of rounds that has ended in a tie: "+tieRounds);
            System.out.println(print);
        }

        return hasAWinner;
    }
    /*
    Checks if a game is draw and returns true if so, false otherwise.
     */
    private static Boolean isGameDraw(ExtendableGamingBoard currentBoard){
        for(int i=0; i<currentBoard.getSize();i++){
            for(int j=0;j< currentBoard.getSize();j++){
                if(currentBoard.board[i][j].equals("_ | ")){
                    return false;
                }
            }
        }
        return true;
    }
}
