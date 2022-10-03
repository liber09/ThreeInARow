public interface IGamingBoard {
    public String[][] getNewBoard(int boardSize);
    public void printBoard();
    public boolean isPositionFree(int row, int column);
    public void setPlayers();
    public Player nextPlayer();
    public void Play();
    public void resetGame();
    public void resetPlayers();

}
