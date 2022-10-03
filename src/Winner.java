import java.util.ArrayList;

public class Winner {
    static int numberOfRoundsPlayed = 0;
    public static boolean gameHasAWinner(ExtendableGamingBoard currentBoard, Player currentPlayer, int playedTurns){
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
        if(hasAWinner){
            currentPlayer.addNumberOfWins();
            if(currentPlayer.getName().equals("Computer")){
                System.out.println("Sorry but the computer won this game!");
            }else{
                System.out.println("Congratulations " + currentPlayer.getName() + " you won the game!");
            }
        }







        if(!hasAWinner && playedTurns == 9){
            System.out.println("This game was a tie!");
        }
        if(hasAWinner || playedTurns == 9){
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
}
