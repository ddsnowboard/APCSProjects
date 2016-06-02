// NB That this is a special MarkovState. It returns the thing that beats the next thing in rock 
// paper scissors, not the thing itself. DO NOT copy and paste for a standard Markov Chain. 



import java.util.*;

public class MarkovState {
    private Hashtable<KosterGame.Plays, Integer> outcomes;
    private boolean isInitialized;
    public MarkovState() {
        outcomes = new Hashtable<>();
        isInitialized = false;
    }
    public void add(KosterGame.Plays outcome)
    {
        isInitialized = true;
        if(outcomes.containsKey(outcome))
        {
            outcomes.put(outcome, outcomes.get(outcome) + 1);
        } 
        else
        {
            outcomes.put(outcome, 1);
        }
    }
    public KosterGame.Plays getNext() throws Exception
    {
        Set<KosterGame.Plays> keys = outcomes.keySet();
        int max = 0;
        KosterGame.Plays most = null;
        for(KosterGame.Plays s : keys)
        {
            if(outcomes.get(s) > max) {
                max = outcomes.get(s);
                most = s;
            }
        }
        switch(most)
        {
            case ROCK:
                return KosterGame.Plays.PAPER;
            case PAPER:
                return KosterGame.Plays.SCISSORS;
            case SCISSORS:
                return KosterGame.Plays.ROCK;
        }
        throw new Exception("Something bad happened! I don't know why!");
    }
    public boolean isInitialized()
    {
        return isInitialized;
    }
    public void setInitialized(boolean initialized)
    {
        isInitialized = initialized;
    }
}
