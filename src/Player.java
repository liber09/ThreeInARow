public class Player {
    String name = "";
    char sign = ' ';
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
        public void setSign(char sign){
            this.sign = sign;
        }
        //return players sign
        public char getSign(){
            return this.sign;
        }
        //Sets the number of wins
        public void setNumberOfWins(){
            numberOfWins++;
        }
        //returns the number of wins
        public int getNumberOfWins(){
            return numberOfWins;
        }
    }
