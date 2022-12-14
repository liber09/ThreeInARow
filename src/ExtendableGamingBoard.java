public class ExtendableGamingBoard implements IGamingBoard{
    String[][] board;
    private int size = 0;
    private int numberInRowToWin = 0;
    public ExtendableGamingBoard(int boardSize, int numberInRowToWin){
        this.size = boardSize;
        this.numberInRowToWin = numberInRowToWin;
        board = new String[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = "_";
            }
        }
    }
    public int getSquares(){
         return getSize()*getSize();
    }
    @Override
    public String[][] getNewBoard(int boardSize) {
        board = new String[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                board[i][j] = "_";
            }
        }
        return board;
    }
    public void printBoard(){
        for (int i=0; i<size;i++){
            if (i == 0) {
                System.out.print("   B"+i);
            }else if (i == size-1) {
                System.out.println("  B" + i);
            }else if (i>9) {
                System.out.print(" B" + i);
            }else if (i>99){
                System.out.print("B" + i);
            } else{
                System.out.print("  B"+i);
            }
    }
        for (int j=0;j<size;j++) {
            System.out.print("A"+j+" ");
            for (int k=0;k<size;k++){
                System.out.print(board[j][k]+" | ");
            }
            System.out.println();
        }
    }
    //Returns the boardSize
    public int getSize(){
        return this.size;
    }
    public int getNumberInRowToWin(){
        return this.numberInRowToWin;
    }
}