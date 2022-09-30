import java.util.ArrayList;
import java.util.Scanner;
public class GamingBoard {
    Scanner input = new Scanner(System.in);
    char[][] board = {{'_','|','_','|','_'},{'_','|','_','|','_'},{' ','|',' ','|',' '}};
    boolean gameContinue = true;
    Player currentPlayer;
    ArrayList<Player> players = new ArrayList<Player>();
    public void printBoard(){
        for(char[] gb : board){
            for(char g: gb){
               System.out.print(g);
            }
            System.out.println();
        }
    }
    private boolean checkPositionTaken(int row,int column ){
        if(board[row][column] == '_' || board[row][column] == ' '){
             return false;
        }else{
            System.out.println("Position already taken,take another..");
            return true;
        }
}
    /*
        Creates players, asking user if playing against friend or computer

     */
    public void setPlayers(){
        System.out.println("Want to play with:\n1.friend\nor\n2.computer?");
        int opponent = input.nextInt();
        input.nextLine();
        if(opponent == 1){
            Player player1 = new Player();
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
            Player player1 = new Player();
            Player player2 = new Player();
            System.out.println("Enter player1's name:");
            player1.setName(input.nextLine());
            player2.setName("Computer");
            System.out.println("Ok "+player1.getName()+", lets choose sign X or O:");
            player1.setSign(input.next().charAt(0));
            input.nextLine();
            if(player1.getSign() == 'X' || player1.getSign() == 'x'){
                player2.setSign('O');
            }else{
                player2.setSign('X');
            }
            players.add(player1);
            players.add(player2);
        }
    }
    //Changes current player to next player
    private Player nextPlayer(){
        if (currentPlayer == null){
            currentPlayer = players.get(0);
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
            nextPlayer();
            System.out.println(currentPlayer.getName() + " Choose your position 1-9: ");
            int chosenPosition = input.nextInt();
            switch (chosenPosition){
                case 1:
                    if(!checkPositionTaken(0,0)){
                        // create a player and get and set the sign.
                        board[0][0] = currentPlayer.getSign();
                       // prints the current board
                        printBoard();
                    }
                    break;
                case 2:
                    if(!checkPositionTaken(0,2)){
                        // create a player and get and set the sign.
                        board[0][2] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                case 3:
                    if(!checkPositionTaken(0,4)){
                        //create a player and get and set the sign.
                        board[0][4] = currentPlayer.getSign();
                        //prints the current board
                        printBoard();
                    }
                    break;
                case 4:
                    if(!checkPositionTaken(1,0)){
                        // create a player and get and set the sign.
                        board[1][0] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                case 5:
                    if(!checkPositionTaken(1,2)){
                        // create a player and get and set the sign.
                        board[1][2] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                case 6:
                    if(!checkPositionTaken(1,4)){
                        // create a player and get and set the sign.
                        board[1][4] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                case 7:
                    if(!checkPositionTaken(2,0)){
                        // create a player and get and set the sign.
                        board[2][0] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                case 8:
                    if(!checkPositionTaken(2,2)){
                        // create a player and get and set the sign.
                        board[2][2] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                case 9:
                    if(!checkPositionTaken(2,4)){
                        // create a player and get and set the sign.
                        board[2][4] = currentPlayer.getSign();
                        // prints the current board
                        printBoard();
                    }
                    break;
                default:
                    System.out.println("Enter a valid number: ");
                    break;

            }
              currentPlayer = nextPlayer();
        }while(gameContinue);
    }
}
