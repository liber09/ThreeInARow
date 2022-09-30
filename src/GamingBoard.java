import java.util.ArrayList;
import java.util.Scanner;
public class GamingBoard {
    Scanner input = new Scanner(System.in);
    char[][] board = {{'_','|','_','|','_'},{'_','|','_','|','_'},{' ','|',' ','|',' '}};
    boolean gameContinue = true;
    Player currentPlayer;
    ArrayList<Player> players = new ArrayList<Player>();
    Boolean positionFree = true;
    private int chosenPosition = 0;
    int boardSize = 9;
    private int playedTurns = 0;

    public char[][] getNewBoard(){
        char[][] newBoard = {{'_','|','_','|','_'},{'_','|','_','|','_'},{' ','|',' ','|',' '}};
        return newBoard;
    }
    public void printBoard(){
        for(char[] gb : board){
            for(char g: gb){
               System.out.print(g);
            }
            System.out.println();
        }
    }
    private boolean isPositionFree(int row, int column ){
        if(board[row][column] == '_' || board[row][column] == ' '){
             return true;
        }else{
            System.out.println("Position already taken,take another..");
            return false;
        }
}
    /*
        Creates players, asking user if playing against
        friend or computer
     */
    public void setPlayers(){
        System.out.println("Want to play with:\n1.friend\nor\n2.computer?");
        try{
            int opponent = input.nextInt();
            input.nextLine();
            Player player1 = new Player();
            if(opponent == 1){
                Player player2 = new Player();
                System.out.println("Enter player1's name: ");
                player1.setName(input.nextLine());
                System.out.println("Ok "+player1.getName()+", lets choose a sign X or O?");
                player1.setSign(input.next().charAt(0));
                input.nextLine();
                System.out.println("Enter player2's name: ");
                player2.setName(input.nextLine());
                if(player1.getSign() == 'X' || player1.getSign() == 'x'){
                    player2.setSign('O');
                }else{
                    player2.setSign('X');
                }
                players.add(player1);
                players.add(player2);
            }else{
                Computer computer = new Computer();
                System.out.println("Enter player1's name:");
                player1.setName(input.nextLine());
                computer.setName("Computer");
                System.out.println("Select computer difficulty level. 1.Easy\n2.Medium\n3.Hard");
                int difficultyLevel = input.nextInt();
                input.nextLine();
                computer.setComputerDifficultyLevel(difficultyLevel);
                System.out.println("Ok "+player1.getName()+", lets choose sign X or O:");
                player1.setSign(input.next().charAt(0));
                input.nextLine();
                if(player1.getSign() == 'X' || player1.getSign() == 'x'){
                    computer.setSign('O');
                }else{
                    computer.setSign('X');
                }
                players.add(player1);
                players.add(computer);
            }


        }
        //Handles exception prints message to user
        catch(Exception e){
            System.out.println("Wrong input, try again");
            input.nextLine();
            setPlayers();
        }


    }
    //Changes current player to next player
    private Player nextPlayer(){
        if (currentPlayer == null){
            return players.get(0);
        }
        if(currentPlayer.getName().equals(players.get(0).getName())){
            return players.get(1);
        }else{
            return players.get(0);
        }
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
                chosenPosition = currentPlayer.ComputerTurn();
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
                    }
                    break;
                case 2:
                    positionFree = isPositionFree(0,2);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[0][2] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                case 3:
                    positionFree = isPositionFree(0,4);
                    if(positionFree){
                        //create a player and get and set the sign.
                        board[0][4] = currentPlayer.getSign();
                        //prints the current board
                        printBoard();
                    }
                    break;
                case 4:
                    positionFree = isPositionFree(1,0);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[1][0] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                case 5:
                    positionFree = isPositionFree(1,2);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[1][2] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                case 6:
                    positionFree = isPositionFree(1,4);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[1][4] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                case 7:
                    positionFree = isPositionFree(2,0);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[2][0] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                case 8:
                    positionFree = isPositionFree(2,2);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[2][2] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                case 9:
                    positionFree = isPositionFree(2,4);
                    if(positionFree){
                        // create a player and get and set the sign.
                        board[2][4] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                default:
                    System.out.println("Enter a valid number: ");
                    positionFree = false;
                    break;
            }
        }while(playedTurns < boardSize);
    }
    //Reset the gamingboard and other variables that need to be reset to play again.
    public void resetGame(){
        board = getNewBoard();
        positionFree = true;
        playedTurns = 0;
        currentPlayer = null;
        chosenPosition = 0;
    }
}
