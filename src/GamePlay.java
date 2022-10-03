import java.util.ArrayList;
import java.util.Scanner;

public class GamePlay {
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Player> players = new ArrayList<Player>();
    private static boolean positionFree = true;
    private static Player currentPlayer;
    private static int playedTurns = 0;
    private static String chosenPosition;
    private static Boolean hasAWinner = false;
    /*
        Creates players, asking user if playing against
        friend or computer
     */
    public static void setPlayers(){
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
                player1.setSign(input.nextLine().toUpperCase());
                System.out.println("Enter player2's name: ");
                player2.setName(input.nextLine());
                if(player1.getSign().equals("X | ")){
                    player2.setSign("O | ");
                }else{
                    player2.setSign("X | ");
                }
                players.add(player1);
                players.add(player2);
            }else{
                Computer computer = new Computer();
                System.out.println("Enter player1's name:");
                player1.setName(input.nextLine());
                computer.setName("Computer");
                System.out.println("Select computer difficulty level.\n1.Easy\n2.Medium\n3.Hard");
                int difficultyLevel = input.nextInt();
                input.nextLine();
                computer.setComputerDifficultyLevel(difficultyLevel);
                System.out.println("Ok "+player1.getName()+", lets choose sign X or O:");
                player1.setSign(input.nextLine().toUpperCase());
                input.nextLine();
                if(player1.getSign().equals("X | ")){
                    computer.setSign("O | ");
                }else{
                    computer.setSign("X | ");
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
    public static Player nextPlayer(){
        if (currentPlayer == null){
            return players.get(0);
        }
        if(currentPlayer.getName().equals(players.get(0).getName())){
            return players.get(1);
        }else{
            return players.get(0);
        }
    }
    //Checks if the requested position is free
    //Returns true if free, false if already taken
    public static boolean isPositionFree(ExtendableGamingBoard board, int row, int column){
        if(board.board[row][column] == "_ | "){
            return true;
        }else{
            System.out.println("Position already taken,take another..");
            return false;
        }
    }
    public static void play(ExtendableGamingBoard gamingBoard){
        do{
            if(positionFree){
                currentPlayer = nextPlayer();
                playedTurns++;
            }
            System.out.println(currentPlayer.getName() + " Choose your position (ex A3B2");
            if(currentPlayer.getName().equals("Computer")){
                chosenPosition = currentPlayer.ComputerTurn(gamingBoard, playedTurns, players.get(0).getSign());
            }else{
                chosenPosition = input.nextLine();
            }
                positionFree = isPositionFree(gamingBoard,Integer.parseInt(chosenPosition.substring(1,1)),Integer.parseInt(chosenPosition.substring(3,3)));
                if(positionFree){
                    gamingBoard.board[Integer.parseInt(chosenPosition.substring(1,1))][Integer.parseInt(chosenPosition.substring(3,3))] = currentPlayer.getSign();
                    gamingBoard.printBoard();
                    if(currentPlayer.getName().equals("Computer")){
                        currentPlayer.setLastSelectedPosition("A"+chosenPosition.substring(1,1)+"B"+chosenPosition.substring(3,3));
                    }
                }
            if (playedTurns > 4){
                hasAWinner = Winner.gameHasAWinner(gamingBoard,currentPlayer,playedTurns);
            }
        }while((playedTurns < boardSize) && !hasAWinner);
    }
}
