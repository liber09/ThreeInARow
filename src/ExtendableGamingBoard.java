import java.util.ArrayList;

public class ExtendableGamingBoard implements IGamingBoard{


    @Override
    public char[][] getNewBoard() {
        return new char[0][];
    }

    @Override
    public void printBoard(int boardSize) {
        char[][] temp = {{'_','|','_','|'}};
        ArrayList<char[][]> board = new ArrayList<char[][]>();
        for(int i=0; i<boardSize;i++){
            if (i == 0) {
                System.out.print("    A"+i);
            }else if(i == boardSize-1){
                System.out.println("  A"+i);
            }else{
                System.out.print("  A"+i);
            }

        }
        for(int j=0; j<boardSize;j++){
            System.out.print("B"+j);
            for(int k=0; k<(boardSize*2);k++){
                if(k%2==0){
                    System.out.print(' ');
                    System.out.print('|');
                }else{
                    System.out.print(' ');
                    System.out.print('_');
                }
            }
            System.out.println();
        }

    }

    @Override
    public boolean isPositionFree(int row, int column) {
        return false;
    }

    @Override
    public void setPlayers() {

    }

    @Override
    public Player nextPlayer() {
        return null;
    }

    @Override
    public void Play() {

    }

    @Override
    public void resetGame() {

    }

    @Override
    public void resetPlayers() {

    }
}
