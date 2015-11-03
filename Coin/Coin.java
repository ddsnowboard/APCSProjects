import java.util.Random;
class Coin {
    private Random random;
    public Coin()
    {
        this.random = new Random();
    }
    public boolean flip(){
        return random.nextBoolean();
    }
}
