public interface IGamingBoard {
    public char[][] getNewBoard();
    public void printBoard(int boardSize);
    public boolean isPositionFree(int row, int column);
    public void setPlayers();
    public Player nextPlayer();
    public void Play();
    public void resetGame();
    public void resetPlayers();

}
