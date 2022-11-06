package boggle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * The BoggleStats class for the first Assignment in CSC207, Fall 2022
 * The BoggleStats will contain statsitics related to game play Boggle 
 */
public class BoggleStats {

    /**
     * set of words the player finds in a given round 
     */  
    private Set<String> playerWords = new HashSet<String>();  
    /**
     * set of words the computer finds in a given round 
     */  
    private Set<String> computerWords = new HashSet<String>();
    /**
     * the player's score for the current round
     */  
    private int pScore; 
    /**
     * the computer's score for the current round
     */  
    private int cScore; 
    /**
     * the player's total score across every round
     */  
    private int pScoreTotal; 
    /**
     * the computer's total score across every round
     */  
    private int cScoreTotal; 
    /**
     * the average number of words, per round, found by the player
     */  
    private double pAverageWords; 
    /**
     * the average number of words, per round, found by the computer
     */  
    private double cAverageWords; 
    /**
     * the current round being played
     */  
    private int round; 

    /**
     * enumarable types of players (human or computer)
     */  
    public enum Player {
        Human("Human"),
        Computer("Computer");
        private final String player;
        Player(final String player) {
            this.player = player;
        }
    }

    /* BoggleStats constructor
     * ----------------------
     * Sets round, totals and averages to 0.
     * Initializes word lists (which are sets) for computer and human players.
     */
    public BoggleStats() {
        this.cScoreTotal = this.pScoreTotal = 0;
        this.round = 0;
        this.pAverageWords = this.cAverageWords = 0.0;
    }

    /* 
     * Add a word to a given player's word list for the current round.
     * You will also want to increment the player's score, as words are added.
     *
     * @param word     The word to be added to the list
     * @param player  The player to whom the word was awarded
     */
    public void addWord(String word, Player player) {
        word = word.toLowerCase();
        if (player.player == "Human" && !playerWords.contains(word) && !computerWords.contains(word))
        {this.playerWords.add(word);
        this.pScore += (word.length() - 3);}

        else if (player.player == "Computer" && !playerWords.contains(word)) {
            this.computerWords.add(word);
            this.cScore += (word.length() - 3);
        }
    }

    /* 
     * End a given round.
     * This will clear out the human and computer word lists, so we can begin again.
     * The function will also update each player's total scores, average scores, and
     * reset the current scores for each player to zero.
     * Finally, increment the current round number by 1.
     */
    public void endRound()
    {
        //this shit is all wrong fix it.
        this.pScoreTotal += this.pScore;
        this.cScoreTotal += this.cScore;
        this.cScore = this.pScore = 0;


        this.pAverageWords = ((this.pAverageWords * (this.round)) + this.playerWords.size())/(this.round + 1);
        this.cAverageWords = ((this.cAverageWords * (this.round)) + this.computerWords.size())/(this.round + 1);


        this.playerWords.clear();
        this.computerWords.clear();

        ++this.round;//might have to put this before pAverageWords
        //u might have to do remove all here instead
    }

    /* 
     * Summarize one round of boggle.  Print out:
     * The words each player found this round.
     * Each number of words each player found this round.
     * Each player's score this round.
     */
    public void summarizeRound() {
        Iterator<String> h_player = this.playerWords.iterator();
        Iterator<String> c_player = this.computerWords.iterator();
        while(h_player.hasNext())
        {
            System.out.println(h_player.next().toUpperCase());
        }
        while (c_player.hasNext())
        {
            System.out.println(c_player.next().toUpperCase());
        }
        System.out.println("\nThe Players Word Count For Round " +  this.round +  " is " + this.playerWords.size());
        System.out.println("The Player Score For Round " + this.round + " is " + this.pScore + "\n");

        System.out.println("The Computer Word Count For Round " + this.round + " is " + this.computerWords.size());
        System.out.println("The Computer Score For Round " + this.round + " is " +this.cScore + "\n");
    }

    /* 
     * Summarize the entire boggle game.  Print out:
     * The total number of rounds played.
     * The total score for either player.
     * The average number of words found by each player per round.
     */
    public void summarizeGame() {
        System.out.println(this.round);

        System.out.println("Player Total Score is: " + this.pScoreTotal);
        System.out.println("Player Average Words is: " + this.pAverageWords);

        System.out.println("Computer Total Score is: " + this.cScoreTotal);
        System.out.println("Computer Average Words is: " + this.cAverageWords);

    }

    /* 
     * @return Set<String> The player's word list
     */
    public Set<String> getPlayerWords() {
        return this.playerWords;
    }

    /*
     * @return int The number of rounds played
     */
    public int getRound() { return this.round; }

    /*
    * @return int The current player score
    */
    public int getScore()
    {
        return this.pScore;
    }
}
