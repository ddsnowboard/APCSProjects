import java.util.*;
import java.lang.NumberFormatException;
// Rock paper scissors Markov chains, anyone?
public class KosterGame {
    public enum Plays { ROCK, PAPER, SCISSORS };
    public static void main(String[] args) throws Exception
    {
        String name = getString("What's your name?");
        System.out.printf("Your name has %d characters, in case you didn't know. Now that that's out of the way, we can do something more interesting...%n",
                          name.length());
        Hashtable<Plays, MarkovState> states = new Hashtable<>();
        fillTable(states);
        // The last thing the human played. 
        Plays last = null;
        // The current thing that the human is playing. 
        Plays humanPlay;
        Plays computerPlay;
        print("Let's play rock paper scissors\n");
        // This saves one byte off the shortest equivalent while() loop. That means I that, over
        // the entire program, I saved on the order of four bytes!
        for(;;)
        {
            print("\n\n\n");
            if(last == null || !states.get(last).isInitialized()) // ie this is the first game or I don't have much information. 
            {
                // http://steve-yegge.blogspot.com/2006/03/execution-in-kingdom-of-nouns.html
                Random random = new Random();
                // Yo dawg, I heard you liked ternary operators. 
                computerPlay = random.nextInt(3) == 0 ? Plays.ROCK : random.nextInt(3) == 1 ? Plays.PAPER : Plays.SCISSORS;
            }
            else
            {
                computerPlay = states.get(last).getNext();
            }
            humanPlay = getPlay();
            if(last != null)
            {
                states.get(last).add(humanPlay);
            }
            System.out.printf("I play %s%n", computerPlay);
            last = humanPlay;
            printWinner(humanPlay, computerPlay);
            String again = getString("Do you want to play again? y/n").toLowerCase();
            if(!(again.equals("y") || again.equals("")))
            {
                break;
            }
        }
    }

    // Who has the time to write all this code over and over?
    private static String getString(String prompt)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%s ", prompt);
        return scanner.nextLine();
    }
    private static int getInt(String prompt)
    {
        for(;;)
        {
            String input = getString(prompt);
            try
            {
                return Integer.parseInt(input);
            }
            catch(NumberFormatException e)
            {
                System.out.print("That wasn't a number!\n");
            }
        }
    }
    // If I'm going to print a bunch of stuff, God knows that I'm not going to write System.out.print every 
    // time. 
    private static void print(String thing)
    {
        System.out.print(thing);
    }
    
    // Populates the tables
    private static void fillTable(Hashtable<Plays, MarkovState> table)
    {
        for(Plays p : Plays.values())
        {
            table.put(p, new MarkovState());
        }
    }
    private static void printWinner(Plays humanPlay, Plays computerPlay)
    {
        if(humanPlay == computerPlay)
        {
            print("It's a draw!");
        }
        else if(humanPlay == Plays.ROCK)
        {
            switch(computerPlay){
                case PAPER:
                    print("Paper covers rock. I win");
                    break;
                case SCISSORS:
                    print("Rock breaks scissors. You win");
                    break;
            }
        }
        else if(humanPlay == Plays.PAPER)
        {
            switch(computerPlay){
                case SCISSORS:
                    print("Scissors cut paper. I win");
                    break;
                case ROCK:
                    print("Paper covers rock. You win.");
            }
        }
        else if(humanPlay == Plays.SCISSORS)
        {
            switch(computerPlay)
            {
                case ROCK:
                    print("Rock breaks scissors. I win");
                    break;
                case PAPER:
                    print("Scissors cuts paper. You win.");
                    break;
            }
        }
        // Don't repeat yourself...
        print("\n");
    }
    static private Plays getPlay()
    {
        String input = getString("Do you want to play (r)ock, (p)aper, or (s)cissors?");
        switch(input.toLowerCase().substring(0, 1)) {
            case "r":
                return Plays.ROCK;
            case "p":
                return Plays.PAPER;
            case "s":
                return Plays.SCISSORS;
            default:
                print("That wasn't an option. Try again.\n");
                return getPlay();
        }
    }
}
