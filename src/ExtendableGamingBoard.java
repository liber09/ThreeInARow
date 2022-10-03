import java.util.ArrayList;

public class ExtendableGamingBoard implements IGamingBoard{
    String[][] board;

    public ExtendableGamingBoard(int boardSize){
        board = new String[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = "_ | ";
            }
        }
        printBoard(boardSize);
        


    }
    @Override
    public char[][] getNewBoard() {
        return new char[0][];
    }

    public void printBoard(int boardSize){
        for(int i=0; i<boardSize;i++){
            if (i == 0) {
                System.out.print("   A"+i);
            }else if(i == boardSize-1) {
                System.out.println("  A" + i);
            }else if(i>9) {
                System.out.print(" A" + i);
            }else if(i>99){
                System.out.print("A" + i);
            } else{
                System.out.print("  A"+i);
            }

    }
        for(int j=0;j<boardSize;j++) {
            System.out.print("B"+j+" ");
            for(int k=0;k<boardSize;k++){
                System.out.print(board[j][k]);
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
