import java.util.ArrayList;

public class Winner {
    public static boolean gameHasAWinner(ExtendableGamingBoard currentBoard, Player currentPlayer, ArrayList<Player> players){
        boolean hasAWinner = false;
        int score = 0;
        //HorizontalCheck
        //firstNumber is row = horizontal, second number is column = vertical
        //since we change column for each iteration this loop checks horizontally
        for(int i=0;i<currentBoard.getSize();i++){
            for(int j=0;j<currentBoard.getSize();j++){
                //if the inner loop has not found any opponent signs before
                //reaching NumberInRowToWin, we have a winner.
                if(currentBoard.board[i][j].equals(currentPlayer.getSign())) {
                    score++;
                    if (score == currentBoard.getNumberInRowToWin()) {
                        hasAWinner = true;
                    }
                }else{
                    score = 0;
                }
            }
            score=0;
        }

        //Vertical check
        //firstNumber is row = horizontal, second number is column = vertical
        //since we change column for each iteration this loop checks vertically
        for(int i=0;i < currentBoard.getSize();i++){
            for(int j=0;j< currentBoard.getSize();j++){
                if(currentBoard.board[j][i].equals(currentPlayer.getSign())){
                    score++;
                    //if the inner loop has not found any opponent signs before
                    //reaching NumberInRowTOWin, we have a winner.
                    if(score == currentBoard.getNumberInRowToWin()){
                        hasAWinner = true;
                    }
                }else{
                    score = 0;
                }

            }
            score=0;
        }

        int d;
        //Diagonal Checks
        for(d=0;d<currentBoard.getSize();d++){
            if(!currentBoard.board[d][d].equals(currentPlayer.getSign())){
                break;
            }
        }
        if(d == currentBoard.getNumberInRowToWin()){
            hasAWinner = true;
        }
        //Checks reverse diagonally. Starting at top right position
        //and then reversing one to the left and one down.
        if (currentBoard.getSize() == 3){
            if(currentBoard.board[0][2].equals(currentPlayer.getSign()) &&
                            (currentBoard.board[1][1].equals(currentPlayer.getSign())) &&
                            (currentBoard.board[2][0].equals(currentPlayer.getSign()))){
                hasAWinner = true;
            }
        }
        for(d=0;d< currentBoard.getSize();d++){
            if(currentBoard.board[d][d].equals(currentPlayer.getSign())){
                score++;
            }else{
                score = 0;
            }
        }
        if(score == currentBoard.getNumberInRowToWin()){
            hasAWinner = true;
        }
        Boolean draw = false;
        if(hasAWinner){
            draw = isGameDraw(currentBoard);
            GamePlay.increaseMatchCount();
            if(draw){
                System.out.println("This game was a tie!");
            }else if(currentPlayer.getName().equals("Computer")){
                System.out.println("Sorry but the computer won this game!");
                players.get(1).addNumberOfWins();
            }else{
                System.out.println("Congratulations " + currentPlayer.getName() + " you won the game!");
                players.get(0).addNumberOfWins();
            }
        }


        if(hasAWinner || draw){
            ArrayList<String> print = new ArrayList<>();
            int tieRounds = GamePlay.getNumberOfMatches()-players.get(0).getNumberOfWins()-players.get(1).getNumberOfWins();
            print.add("Results after " + GamePlay.getNumberOfMatches() + " played rounds\n" + players.get(0).getName() + "\t" + players.get(1).getName()+ "\n"+
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
                if(currentBoard.board[i][j].equals("_")){
                    return false;
                }
            }
        }
        return true;
    }
}
