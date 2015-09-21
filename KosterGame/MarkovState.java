// NB That this is a special MarkovState. It returns the thing that beats the next thing in rock 
// paper scissors, not the thing itself. DO NOT copy and paste for a standard Markov Chain. 
import java.util.*;

class MarkovState {
    private Hashtable<KosterGame.Plays, Integer> outcomes;
    public MarkovState() {
        outcomes = new Hashtable<>();
    }
    public void add(KosterGame.Plays outcome)
    {
        if(outcomes.containsKey(outcome))
        {
            outcomes.put(outcome, outcomes.get(outcome) + 1);
        } 
        else
        {
            outcomes.put(outcome, 1);
        }
    }
    public KosterGame.Plays getNext()
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
        // This should really throw an error instead of just this. This isn't really how it's supposed
        // to work. 
        System.out.printf("HEEEEEY THERE!!!! HEEEEREE'S OUTCOMES!!! %s", outcomes.toString());
        return KosterGame.Plays.SCISSORS;
    }
}
