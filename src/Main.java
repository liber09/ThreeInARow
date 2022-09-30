import java.util.Scanner;
public class Main {
    static Scanner input = new Scanner(System.in);
    static String continueGame = "";
    public static void main(String[] args) {
        GamingBoard gamingBoard = new GamingBoard();
        gamingBoard.setPlayers();
        gamingBoard.printBoard();
        do {
            gamingBoard.play();
            gamingBoard.resetGame();
            System.out.println("Do you want to play again?");
            continueGame = input.nextLine();
            gamingBoard.printBoard();
        }while(continueGame.equalsIgnoreCase("y"));

    }

}