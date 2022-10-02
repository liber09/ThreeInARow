import java.util.Scanner;
public class Menu {
    Scanner input = new Scanner(System.in);
    public void printStartMenu(){
        int userMenuChoice = 0;
        WelcomePhrase();
        System.out.println("What do you want to play?\n1.Classic Tic-Tac-Toe\n2.Advanced Tic-Tac-Toe");
        userMenuChoice = input.nextInt();
        input.nextLine();
        if(userMenuChoice == 1){
            
        }
    }

    private void WelcomePhrase() {
    }
}
