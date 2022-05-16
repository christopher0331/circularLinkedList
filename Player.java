public class Player implements Comparable<Player>{

    String name;
    int score;

    public Player(String name){
        this.name = name;
        this.score = 0;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public int compareTo(Player anotherPlayer){
        return this.score - anotherPlayer.getScore();
    }
}
