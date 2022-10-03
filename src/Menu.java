import java.util.Scanner;
public class Menu {
    static Scanner input = new Scanner(System.in);
    public static IGamingBoard printStartMenu(){
        int userMenuChoice = 0;
        IGamingBoard gamingBoard = new ExtendableGamingBoard(3);
        WelcomePhrase();
            try{
                do{
                    System.out.println("What do you want to play?\n1.Classic Tic-Tac-Toe\n2.Advanced Tic-Tac-Toe");
                    userMenuChoice = input.nextInt();
                    input.nextLine();
                    if(userMenuChoice == 1){
                        gamingBoard = new ExtendableGamingBoard(3);
                    }else if(userMenuChoice == 2){
                        System.out.println("What board size (number of cells) would you like to play with?");
                        int boardSize = input.nextInt();
                        input.nextLine();
                        gamingBoard = new ExtendableGamingBoard(boardSize);
                        return gamingBoard;
                    }else{
                        System.out.println("You have to make a correct choice of 1 or 2. Try again");
                    }
                }while (userMenuChoice != 1 || userMenuChoice != 2);
            }catch(Exception e){
                System.out.println("You have to make a correct choice of 1 or 2. Try again");
            }
        return gamingBoard;
    }

    private static void WelcomePhrase() {
    }
}
