import java.util.ArrayList;
import java.util.Scanner;
public class GamePlay {
    private static final Scanner input = new Scanner(System.in);
    private static final ArrayList<Player> players = new ArrayList<>();
    private static boolean positionFree = true;
    private static Player currentPlayer;
    private static int numberOfMatches = 0;
    private static int playedTurns = 0;
    private static String chosenPosition;
    private static Boolean hasAWinner = false;
    public static void increaseMatchCount(){
        numberOfMatches++;
    }
    public static int getNumberOfMatches(){
        return numberOfMatches;
    }
    /*
        Creates players, asking user if playing against
        friend or computer
     */
    public static void setPlayers(ExtendableGamingBoard gamingBoard){
        System.out.println("Want to play with:\n1.friend\nor\n2.computer?");
        try{
            int opponent = input.nextInt();
            int difficultyLevel = 0;
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
                if(player1.getSign().equals("X")){
                    player2.setSign("O");
                }else{
                    player2.setSign("X");
                }
                players.add(player1);
                players.add(player2);
            }else{
                Boolean difficultylevelOk = false;
                Computer computer = new Computer();
                System.out.println("Enter player1's name:");
                player1.setName(input.nextLine());
                computer.setName("Computer");
                do{
                    if (gamingBoard.getSize() == 3) {
                        System.out.println("Select computer difficulty level.\n1.Easy\n2.Medium\n3.Hard");
                    }else{
                        System.out.println("Select computer difficulty level.\n1.Easy\n2.Hard");
                    }
                    difficultyLevel = input.nextInt();
                    input.nextLine();
                    if (difficultyLevel >2 && gamingBoard.getSize() >3){
                        System.out.println("You have to choose either 1 or 2.");
                        continue;
                    }
                    if (difficultyLevel == 1 || difficultyLevel ==  2  || difficultyLevel == 3){
                        difficultylevelOk = true;
                    }
                }while(difficultylevelOk == false);
                computer.setComputerDifficultyLevel(difficultyLevel);
                Boolean playerSignOk = false;
                do {
                    System.out.println("Ok " + player1.getName() + ", lets choose sign X or O:");
                    player1.setSign(input.nextLine().toUpperCase());
                    if (player1.getSign().equals("X") || player1.getSign().equals("O")) {
                        playerSignOk = true;
                    } else {
                        System.out.println("You have to choose a correct sign either an X or O!");
                    }
                }while(playerSignOk == false);
                if(player1.getSign().equals("X")){
                    computer.setSign("O");
                }else{
                    computer.setSign("X");
                }
                players.add(player1);
                players.add(computer);
            }
        }
        //Handles exception prints message to user
        catch(Exception e){
            System.out.println("Wrong input, try again");
            input.nextLine();
            setPlayers(gamingBoard);
        }
    }
    //Changes player
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
        if(board.board[row][column].equals("_")){
            return true;
        }else{
            System.out.println("Position already taken,take another..");
            return false;
        }
    }
    /*
    Method that handles all gameplay.
     */
    public static void play(ExtendableGamingBoard gamingBoard){
        do{
            try{
                if(positionFree){
                    currentPlayer = nextPlayer();
                    positionFree = false;
                    playedTurns++;
                }
                System.out.println(currentPlayer.getName() + " Choose your position (ex A3B2");
                if(currentPlayer.getName().equals("Computer")){
                    chosenPosition = currentPlayer.ComputerTurn(gamingBoard, playedTurns, players.get(0).getSign());
                }else{
                    chosenPosition = input.nextLine().toUpperCase();
                }
                int row = Integer.parseInt(chosenPosition.substring(chosenPosition.lastIndexOf("A")+1,chosenPosition.lastIndexOf("B")));
                int column = Integer.parseInt(chosenPosition.substring(chosenPosition.lastIndexOf("B")+1));
                positionFree = isPositionFree(gamingBoard,row,column);
                if(positionFree){
                    gamingBoard.board[row][column] = currentPlayer.getSign();
                    gamingBoard.printBoard();
                    if(currentPlayer.getName().equals("Computer")){
                        currentPlayer.setLastSelectedPosition("A"+row+"B"+column);
                    }
                }
                if (playedTurns > 4){
                    hasAWinner = Winner.gameHasAWinner(gamingBoard,currentPlayer,players);
                }
            }catch(Exception e){
                System.out.println("You have to enter position in correct format, ex A1B2. Please try again!");
            }

        }while((playedTurns < (gamingBoard.getSquares())) && !hasAWinner);
    }
    //Resets the game.
    public static void resetGame(ExtendableGamingBoard gamingBoard){
        gamingBoard.getNewBoard(gamingBoard.getSize());
        positionFree = true;
        playedTurns = 0;
        currentPlayer = null;
        chosenPosition = "";
        hasAWinner = false;
    }

    //Clears the playerlist so it is possilble to start all over witout restarting
    public static void resetPlayers() {
        players.clear();
    }

}
