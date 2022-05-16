import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main {

    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
       
        // from a wave File
        File soundFile = new File("./GameStartSound.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        // start()
        clip.start();


        CircularLinkedList<Player> game = new CircularLinkedList<>();
        
        Player player1 = new Player("Fred Nietzche");
        Player player2 = new Player("Sam Harris");
        Player player3 = new Player("Al Einstein");
        Player player4 = new Player("Sun Tzu");
        game.add(player1);
        game.add(player2);
        game.add(player3);
        game.add(player4);

        int gameWinningScore = 100;
        Player currentHighScore = player1;
        System.out.println("Let the Game Begin!!");
        while(currentHighScore.score < gameWinningScore){
        
            Iterator<Player> playerIterator = game.iterator();
            int counter = 0;    
            int size = game.getSize();

            Thread.sleep(2500);
            // from a wave File
            File newRoundSound = new File("./NewRound.wav");
            AudioInputStream roundFile = AudioSystem.getAudioInputStream(newRoundSound);
            Clip newRound = AudioSystem.getClip();
            newRound.open(roundFile);
            // start()
            newRound.start();
            
            System.out.println();
            System.out.println();
            System.out.println("================ New Round Starting ================");

            while(playerIterator.hasNext() && counter < size){
                // from a wave File
                File diceRollSound = new File("./DiceRoll.wav");
                AudioInputStream diceFile = AudioSystem.getAudioInputStream(diceRollSound);
                Clip dice = AudioSystem.getClip();
                dice.open(diceFile);
                // start()
                dice.start();

                Thread.sleep(2000);
                int roll1 = rollDice();
                int roll2 = rollDice();
                Player current =playerIterator.next();
                current.setScore(current.getScore() + roll1 + roll2);

                if(current.getScore() > currentHighScore.score){
                    System.out.println(current.getName() + " rolls a " + roll1 + " and a " + roll2 + ", score now totaling " + current.getScore() + "... New 1st Place Player");
                    currentHighScore = current;
                } else {
                    System.out.println(current.getName() + " rolls a " + roll1 + " and a " + roll2 + ", score now totaling " + current.getScore());
                }

                if(current.getScore() >= 100){
                    break;
                }
                counter++;   
            }
        }

        // from a wave File
        File winnerSound = new File("./Winner.wav");
        AudioInputStream winnerSoundFile = AudioSystem.getAudioInputStream(winnerSound);
        Clip winner = AudioSystem.getClip();
        winner.open(winnerSoundFile);
        // start()
        winner.start();            

        Thread.sleep(2500);
        System.out.println(currentHighScore.getName() +" wins with " + currentHighScore.getScore() + " points!");
    }

    public static int rollDice(){
        int max = 6;
        int min = 1;
        int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
        return random_int;
    }


}
