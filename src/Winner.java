import java.util.ArrayList;

public class Winner {

    public static boolean gameHasAWinner(char[][] currentBoard, ArrayList<Player> players, int playedTurns){
        boolean hasAWinner = false;
        if((currentBoard[0][0] == players.get(0).getSign() && currentBoard[0][2] == players.get(0).getSign() && currentBoard[0][4] == players.get(0).getSign()) ||
                (currentBoard[1][0] == players.get(0).getSign() && currentBoard[1][2] == players.get(0).getSign() && currentBoard[1][4] == players.get(0).getSign()) ||
                (currentBoard[2][0] == players.get(0).getSign() && currentBoard[2][2] == players.get(0).getSign() && currentBoard[2][4] == players.get(0).getSign()) ||
                (currentBoard[0][0] == players.get(0).getSign() && currentBoard[1][0] == players.get(0).getSign() && currentBoard[2][0] == players.get(0).getSign()) ||
                (currentBoard[0][2] == players.get(0).getSign() && currentBoard[1][2] == players.get(0).getSign() && currentBoard[2][2] == players.get(0).getSign()) ||
                (currentBoard[0][4] == players.get(0).getSign() && currentBoard[1][4] == players.get(0).getSign() && currentBoard[2][4] == players.get(0).getSign()) ||
                (currentBoard[0][0] == players.get(0).getSign() && currentBoard[1][2] == players.get(0).getSign() && currentBoard[2][4] == players.get(0).getSign()) ||
                (currentBoard[0][4] == players.get(0).getSign() && currentBoard[1][2] == players.get(0).getSign() && currentBoard[2][0] == players.get(0).getSign())){
            players.get(0).addNumberOfWins();
            System.out.println("Congratulations " + players.get(0).getName() + " you won the game!");
            hasAWinner = true;
        }
        if((currentBoard[0][0] == players.get(1).getSign() && currentBoard[0][2] == players.get(1).getSign() && currentBoard[0][4] == players.get(1).getSign()) ||
                (currentBoard[1][0] == players.get(1).getSign() && currentBoard[1][2] == players.get(1).getSign() && currentBoard[1][4] == players.get(1).getSign()) ||
                (currentBoard[2][0] == players.get(1).getSign() && currentBoard[2][2] == players.get(1).getSign() && currentBoard[2][4] == players.get(1).getSign()) ||
                (currentBoard[0][0] == players.get(1).getSign() && currentBoard[1][0] == players.get(1).getSign() && currentBoard[2][0] == players.get(1).getSign()) ||
                (currentBoard[0][2] == players.get(1).getSign() && currentBoard[1][2] == players.get(1).getSign() && currentBoard[2][2] == players.get(1).getSign()) ||
                (currentBoard[0][4] == players.get(1).getSign() && currentBoard[1][4] == players.get(1).getSign() && currentBoard[2][4] == players.get(1).getSign()) ||
                (currentBoard[0][0] == players.get(1).getSign() && currentBoard[1][2] == players.get(1).getSign() && currentBoard[2][4] == players.get(1).getSign()) ||
                (currentBoard[0][4] == players.get(1).getSign() && currentBoard[1][2] == players.get(1).getSign() && currentBoard[2][0] == players.get(1).getSign())){
            players.get(1).addNumberOfWins();
            if(players.get(1).getName().equals("Computer")){
                System.out.println("Sorry " + players.get(0).getName() + " but the computer won this game!");
            }else{
                System.out.println("Congratulations " + players.get(1).getName() + " you won the game!");
            }
            hasAWinner = true;
        }
        if(!hasAWinner && playedTurns == 9){
            System.out.println("This game was a tie!");
        }
        return hasAWinner;
    }
}
