import java.util.*;
import java.lang.NumberFormatException;
// Rock paper scissors Markov chains, anyone?
public class KosterGame {
    public enum Plays {ROCK, PAPER, SCISSORS};
    public static void main(String[] args)
    {
        String name = getString("What's your name?");
        System.out.printf("Your name has %d characters, in case you didn't know. Now that that's out of the way, we can do something more interesting...%n", name.length());
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
            humanPlay = getPlay();
            if(last == null) // ie this is the first game.
            {
                // http://steve-yegge.blogspot.com/2006/03/execution-in-kingdom-of-nouns.html
                Random random = new Random();
                // Yo dawg, I heard you liked ternary operators. 
                computerPlay = random.nextInt(3) == 0 ? Plays.ROCK : random.nextInt(3) == 1 ? Plays.PAPER : Plays.SCISSORS;
            }
            else
            {
                // This is kinda cheaty, but if I don't do this, there will be null pointers and stuff everywhere. 
                // I need to fix this. 
                states.get(last).add(humanPlay);
                computerPlay = states.get(last).getNext();
            }
            System.out.printf("I play %s%n", computerPlay);
            last = humanPlay;
            printWinner(humanPlay, computerPlay);
            if(!getString("Do you want to play again? y/n").toLowerCase().equals("y"))
                break;
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
        String input = getString("What's your play?");
        switch(input.toLowerCase()) {
            case "rock":
                return Plays.ROCK;
            case "paper":
                return Plays.PAPER;
            case "scissors":
                return Plays.SCISSORS;
            default:
                print("That wasn't an option. Try again.\n");
                return getPlay();
        }
    }
}
