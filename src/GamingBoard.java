import java.util.ArrayList;
import java.util.Scanner;
public class GamingBoard{
    Scanner input = new Scanner(System.in);
    char[][] board = {{'_','|','_','|','_'},{'_','|','_','|','_'},{' ','|',' ','|',' '}};
    boolean gameContinue = true;
    Player currentPlayer;
    ArrayList<Player> players = new ArrayList<Player>();
    public void play(){
        do{
            if(positionFree){
                currentPlayer = nextPlayer();
                playedTurns++;
            }
            System.out.println(currentPlayer.getName() + " Choose your position 1-9: ");
            if(currentPlayer.getName().equals("Computer")){
                chosenPosition = currentPlayer.ComputerTurn(board, playedTurns, players.get(0).getSign());
            }else{
                chosenPosition = input.nextInt();
            }
            switch (chosenPosition){
                case 1:
                    positionFree = isPositionFree(0,0);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[0][0] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(1);
                        }
                    }
                    break;
                case 2:
                    positionFree = isPositionFree(0,2);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[0][2] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(2);
                        }
                    }
                    break;
                case 3:
                    positionFree = isPositionFree(0,4);
                    if(positionFree){
                        //create a player and get and set the sign.
                        board[0][4] = currentPlayer.getSign();
                        //prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(3);
                        }
                    }
                    break;
                case 4:
                    positionFree = isPositionFree(1,0);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[1][0] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(4);
                        }
                    }
                    break;
                case 5:
                    positionFree = isPositionFree(1,2);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[1][2] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(5);
                        }
                    }
                    break;
                case 6:
                    positionFree = isPositionFree(1,4);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[1][4] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(6);
                        }
                    }
                    break;
                case 7:
                    positionFree = isPositionFree(2,0);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[2][0] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(7);
                        }
                    }
                    break;
                case 8:
                    positionFree = isPositionFree(2,2);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[2][2] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(8);
                        }
                    }
                    break;
                case 9:
                    positionFree = isPositionFree(2,4);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[2][4] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(9);
                        }
                    }
                    break;
                default:
                    System.out.println("Enter a valid number: ");
                    positionFree = false;
                    break;
            }
            if (playedTurns > 4){
                hasAWinner = Winner.gameHasAWinner(board,players,playedTurns);
            }
        }while((playedTurns < boardSize) && !hasAWinner);
    }
    private int chosenPosition = 0;
    int boardSize = 9;
    private int playedTurns = 0;
    private Boolean hasAWinner = false;

    public char[][] getNewBoard(){
        char[][] newBoard = {{'_','|','_','|','_'},{'_','|','_','|','_'},{' ','|',' ','|',' '}};
        return newBoard;
    }

    @Override
    public void printBoard(int boardSize) {

    }

    public void printBoard(){
        for(char[] gb : board){
                   for(char g: gb){
               System.out.print(g);
            }
            System.out.println();
        }
    }
    public boolean isPositionFree(int row, int column ){
        if(board[row][column] == '_' || board[row][column] == ' '){
             return true;
        }else{
            System.out.println("Position already taken,take another..");
            return false;
        }
}

    //Changes current player to next player
    public Player nextPlayer(){
        if (currentPlayer == null){
            return players.get(0);
        }
        if(currentPlayer.getName().equals(players.get(0).getName())){
            return players.get(1);
        }else{
            return players.get(0);
        }
    }

    @Override
    public void Play() {

    }

    /*
        Handles all the gameplay
     */
    public void play(){
        do{
            if(positionFree){
                currentPlayer = nextPlayer();
                playedTurns++;
            }
            System.out.println(currentPlayer.getName() + " Choose your position 1-9: ");
            if(currentPlayer.getName().equals("Computer")){
                chosenPosition = currentPlayer.ComputerTurn(board, playedTurns, players.get(0).getSign());
            }else{
                chosenPosition = input.nextInt();
            }
            switch (chosenPosition){
                case 1:
                    positionFree = isPositionFree(0,0);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[0][0] = currentPlayer.getSign();
                       // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(1);
                        }
                    }
                    break;
                case 2:
                    positionFree = isPositionFree(0,2);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[0][2] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(2);
                        }
                    }
                    break;
                case 3:
                    positionFree = isPositionFree(0,4);
                    if(positionFree){
                        //create a player and get and set the sign.
                        board[0][4] = currentPlayer.getSign();
                        //prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(3);
                        }
                    }
                    break;
                case 4:
                    positionFree = isPositionFree(1,0);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[1][0] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(4);
                        }
                    }
                    break;
                case 5:
                    positionFree = isPositionFree(1,2);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[1][2] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(5);
                        }
                    }
                    break;
                case 6:
                    positionFree = isPositionFree(1,4);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[1][4] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(6);
                        }
                    }
                    break;
                case 7:
                    positionFree = isPositionFree(2,0);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[2][0] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(7);
                        }
                    }
                    break;
                case 8:
                    positionFree = isPositionFree(2,2);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[2][2] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(8);
                        }
                    }
                    break;
                case 9:
                    positionFree = isPositionFree(2,4);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[2][4] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                        if(currentPlayer.getName().equals("Computer")){
                            currentPlayer.setLastSelectedPosition(9);
                        }
                    }
                    break;
                default:
                    System.out.println("Enter a valid number: ");
                    positionFree = false;
                    break;
            }
            if (playedTurns > 4){
                hasAWinner = Winner.gameHasAWinner(board,players,playedTurns);
            }
        }while((playedTurns < boardSize) && !hasAWinner);
    }
    //Reset the gaming-board and other variables that need to be reset to play again.
    public void resetGame(){
        board = getNewBoard();
        positionFree = true;
        playedTurns = 0;
        currentPlayer = null;
        chosenPosition = 0;
        hasAWinner = false;
    }
    public void resetPlayers(){
        players.clear();
    }

}
