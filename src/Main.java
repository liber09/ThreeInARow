import java.util.Scanner;
public class Main {
    private static Scanner input = new Scanner(System.in);
    private static String continueGame = "";
    public static void main(String[] args) {
        IGamingBoard gamingBoard = Menu.printStartMenu();
        GamePlay.setPlayers((ExtendableGamingBoard)gamingBoard);
        gamingBoard.printBoard();
        do{
            try{
                GamePlay.play((ExtendableGamingBoard)gamingBoard);
                GamePlay.resetGame((ExtendableGamingBoard)gamingBoard);
                System.out.println("Do you want to play again?\nY.Yes\nN.No");
                continueGame = input.nextLine();
                if (continueGame.equalsIgnoreCase("n")){
                    boolean resetQuestionOk = false;
                    String resetGame = "";
                    do{
                        System.out.println("Do you want to reset game results and start all over?\nY.Yes\nN.No");
                        resetGame = input.nextLine();
                        if(resetGame.equalsIgnoreCase("y") || resetGame.equalsIgnoreCase("n")) {
                            resetQuestionOk = true;
                        }
                    }while(!resetQuestionOk);
                    if(resetGame.equalsIgnoreCase("n")){
                        boolean continueQuestionOk = false;
                        do{
                            System.out.println("Okay, so you don't want to reset, do you want to play again?\nY.Yes\nN.No");
                            continueGame = input.nextLine();
                            if(continueGame.equalsIgnoreCase("y") || (continueGame.equalsIgnoreCase("n"))){
                                continueQuestionOk = true;
                            }else{
                                System.out.println("You have to answer the question with either y or n, please try again.");
                            }
                        }while(!continueQuestionOk);

                    }
                    if(resetGame.equalsIgnoreCase("y")){
                        GamePlay.resetPlayers();
                        GamePlay.setPlayers((ExtendableGamingBoard)gamingBoard);
                        continueGame = "y";
                    }
                }
                gamingBoard.printBoard();
            }catch(Exception e){
                System.out.println("Something went wrong, please try again!");
            }
        }while(continueGame.equalsIgnoreCase("y"));

    }

}