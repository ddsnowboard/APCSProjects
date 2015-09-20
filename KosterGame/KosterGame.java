import java.util.*;
import java.lang.NumberFormatException;
// Rock paper scissors Markov chains, anyone?
public class KosterGame {
    public static final String ROCK = "rock";
    public static final String PAPER = "paper";
    public static final String SCISSORS = "scissors";
    public static void main(String[] args)
    {
        String name = getString("What's your name?");
        System.out.printf("Your name has %d characters, in case you didn't know. Now that that's out of the way, we can do something more interesting...%n", name.length());
        Hashtable<String, MarkovState> states = new Hashtable<>();
        fillTable(states);
        String last = "";
        String computerPlay;
        String humanPlay;
        print("Let's play rock paper scissors\n");
        for(;;)
        {
            print("\n\n\n");
outer:
            {
                for(;;)
                {
                    humanPlay = getString("What's your play?");
                    switch(humanPlay.toLowerCase()) {
                        case ROCK:
                            humanPlay = ROCK;
                            break outer;
                        case PAPER:
                            humanPlay = PAPER;
                            break outer;
                        case SCISSORS:
                            humanPlay = SCISSORS;
                            break outer;
                        default:
                            print("That wasn't an option. Try again.\n");
                            break;
                    }
                }
            }
            if(last.equals(""))
            {
                Random random = new Random();
                computerPlay = random.nextInt(3) == 0 ? ROCK : random.nextInt(3) == 1 ? PAPER : SCISSORS;
            }
            else
            {
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
        // Bytewise, this is shorter than while(true) or even while(1)
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
        System.out.printf(thing);
    }
    private static void fillTable(Hashtable<String, MarkovState> table)
    {
        for(String s : new String[] {ROCK, PAPER, SCISSORS})
        {
            table.put(s, new MarkovState());
        }
    }
    private static void printWinner(String humanPlay, String computerPlay)
    {
        if(humanPlay.equals(computerPlay))
        {
            print("It's a draw!");
        }
        else if(humanPlay.equals(ROCK))
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
        else if(humanPlay.equals(PAPER))
        {
            switch(computerPlay){
                case SCISSORS:
                    print("Scissors cut paper. I win");
                    break;
                case ROCK:
                    print("Paper covers rock. You win.");
            }
        }
        else if(humanPlay.equals(SCISSORS))
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
}
