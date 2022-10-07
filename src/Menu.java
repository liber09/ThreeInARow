import java.util.Scanner;
public class Menu {
    private static final Scanner INPUT = new Scanner(System.in);
    public static IGamingBoard printStartMenu(){
        String userMenuChoice = "0";
        Boolean leaveMenu = false;
        IGamingBoard gamingBoard = new ExtendableGamingBoard(3,3);
        WelcomePhrase();
            try{
                do{
                    System.out.println("What do you want to play?\n1.Classic Tic-Tac-Toe\n2.Advanced Tic-Tac-Toe");
                    userMenuChoice = INPUT.nextLine();
                    if (userMenuChoice.equals("1")){
                        gamingBoard = new ExtendableGamingBoard(3,3);
                        leaveMenu = true;
                    }else if (userMenuChoice.equals("2")){
                        System.out.println("What board size (number of cells) would you like to play with (min 3)?");
                        int boardSize = 0;
                        String tempBoardSize = INPUT.nextLine();
                        boolean isNumber = isNumber(tempBoardSize);
                        if (!isNumber){
                            System.out.println("You have to enter boardsize in numbers above 3.");
                            continue;
                        }else{
                            boardSize = Integer.parseInt(tempBoardSize);
                        }
                        if (boardSize >3 ){
                            System.out.println("Your board is "+boardSize+ ", you need to enter at least 4. How many signs in a row to win?");
                        }else if (boardSize <3){
                            System.out.println("You have to enter a boardsize of at least 3!");
                            continue;
                        }
                        else{
                            System.out.println("How many signs in a row to win?");
                        }
                        int numberInRowToWin = 0;
                        String tempWinNumber = INPUT.nextLine();
                        if (!isNumber(tempWinNumber)){
                            System.out.println("You have to select a number over 3 or 4 depending on chosen boardsize. Try again.");
                            continue;
                        }else{
                            numberInRowToWin = Integer.parseInt(tempWinNumber);
                        }
                        if (boardSize >3 && numberInRowToWin >= 4){
                            gamingBoard = new ExtendableGamingBoard(boardSize,numberInRowToWin);
                            leaveMenu = true;
                        }else if (numberInRowToWin > boardSize){
                            System.out.println("You can not set a higher number than boardSize for required number in row to win.\nTry again!");
                        }else if (boardSize >2 && numberInRowToWin > 2){
                            gamingBoard = new ExtendableGamingBoard(boardSize,numberInRowToWin);
                            leaveMenu = true;
                        }
                    }else{
                        System.out.println("You have to make a correct choice. Try again");
                    }
                }while (!leaveMenu);
            }catch (Exception e){
                System.out.println("You have to make a correct choice of 1 or 2. Try again");
            }
        return gamingBoard;
    }

    /*
    Checks if a string is a number by parsing it, if successful, return true
    else catch NumberFormatException and return false
     */
    private static boolean isNumber(String numberString) {
        try{
            Integer.parseInt(numberString);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    //Print a welcome phrase.
    private static void WelcomePhrase() {
        System.out.println("Welcome to Tic-Tac-Toe!");
    }
}
