import java.lang.reflect.Array;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        gamingBoard();

    }
    public static void gamingBoard(){
        String[] position = new String[8];
        position[0] = " ";
        position[1] = "X";
        position[2] = "0";
        //method for writing out the start gamingbord.
        System.out.println("---+---+---+");
        System.out.println(" "+position[0]+" | "+position[1]+" | "+ position[2]+" ");
        System.out.println("---+---+---+");
        System.out.println(" O |   | X ");
        System.out.println("---+---+---+");
        System.out.println(" X | X | O ");
        System.out.println("---+---+---+");
    }
}