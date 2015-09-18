import java.util.*;
import java.lang.NumberFormatException;
public class KosterGame {
    public static void main(String[] args)
    {
        String name = getString("What's your name?");
        print("OK! Let's go!");
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
}
