public class Player {
    String name = "";
    String sign = " ";
    int numberOfWins = 0;

    //Set name of player
    public void setName(String name){
        this.name = name;
    }
    //Returns name of player
    public String getName() {
        return this.name;
    }
        //Set players sign
        public void setSign(String sign){
            this.sign = sign+" | ";
        }
        //return players sign
        public String getSign(){
            return this.sign;
        }
        //Sets the number of wins
        public void addNumberOfWins(){
            numberOfWins++;
        }
        //returns the number of wins
        public int getNumberOfWins(){
            return numberOfWins;
        }
        public String ComputerTurn(ExtendableGamingBoard board, int playedTurns, String sign){
            return "A0B0";
        }
        public void setLastSelectedPosition(String position){

        }
    }
