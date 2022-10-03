import java.util.Scanner;
public class Menu {
    static Scanner input = new Scanner(System.in);
    public static IGamingBoard printStartMenu(){
        int userMenuChoice = 0;
        WelcomePhrase();
        System.out.println("What do you want to play?\n1.Classic Tic-Tac-Toe\n2.Advanced Tic-Tac-Toe");
        userMenuChoice = input.nextInt();
        input.nextLine();
        if(userMenuChoice == 1){
            return new GamingBoard();
        }else if(userMenuChoice == 2){
            System.out.println("What board size (number of cells) would you like to play with?");
            int boardSize = input.nextInt();
            input.nextLine();
            IGamingBoard gamingBoard = new ExtendableGamingBoard(12);
            return gamingBoard;
        }
        return new GamingBoard();
    }

    private static void WelcomePhrase() {
    }
}
