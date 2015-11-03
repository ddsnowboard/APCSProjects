import java.util.Random;

class Flippable {
    private Random random;
    protected int sides;
    public Flippable()
    {
        this.random = new Random();
    }
    public int flip(){
        return random.nextInt(sides);
    }
}
