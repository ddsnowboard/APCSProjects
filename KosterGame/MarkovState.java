// NB That this is a special MarkovState. It returns the thing that beats the next thing in rock 
// paper scissors, not the thing itself. 
import java.util.*;
class MarkovState {
    private Hashtable<String, Integer> outcomes;
    public MarkovState() {
        outcomes = new Hashtable<>();
    }
    public void add(String outcome)
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
    public String getNext()
    {
        Set<String> keys = outcomes.keySet();
        int max = 0;
        String most = "";
        for(String s : keys)
        {
            if(outcomes.get(s) > max) {
                max = outcomes.get(s);
                most = s;
            }
        }
        switch(most)
        {
            case KosterGame.ROCK:
                return KosterGame.PAPER;
            case KosterGame.PAPER:
                return KosterGame.SCISSORS;
            case KosterGame.SCISSORS:
                return KosterGame.ROCK;
        }
        System.out.printf("HEEEEEY THERE!!!! HEEEEREE'S OUTCOMES!!! %s", outcomes.toString());
        return KosterGame.SCISSORS;
    }
}
