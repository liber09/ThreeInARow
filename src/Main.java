import java.util.Scanner;
public class Main {
    static Scanner input = new Scanner(System.in);
    static String continueGame = "";
    public static void main(String[] args) {
        IGamingBoard gamingBoard = Menu.printStartMenu();
        GamePlay.setPlayers();
        gamingBoard.printBoard();
        do{
            GamePlay.play((ExtendableGamingBoard)gamingBoard);
            GamePlay.resetGame((ExtendableGamingBoard)gamingBoard);
            System.out.println("Do you want to play again?\nY.Yes\nN.No");
            continueGame = input.nextLine();
            if (continueGame.equalsIgnoreCase("n")){
                System.out.println("Do you want to reset game results and start all over?\nY.Yes\nN.No");
                String resetGame = input.nextLine();
                if(resetGame.equalsIgnoreCase("n")){
                    System.out.println("Okay, so you don't want to reset, do you want to play again?\nY.Yes\nN.No");
                    continueGame = input.nextLine();
                }
                if(resetGame.equalsIgnoreCase("y")){
                    gamingBoard.resetPlayers();
                    gamingBoard.setPlayers();
                    continueGame = "y";
                }
            }
            gamingBoard.printBoard();
        }while(continueGame.equalsIgnoreCase("y"));

    }

}