public class Main {
    public static void main(String[] args) {
        GamingBoard gamingBoard = new GamingBoard();
        gamingBoard.setPlayers();
        gamingBoard.printBoard();
        gamingBoard.play();
    }

}