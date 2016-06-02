class Main {
    public static void main(String[] args)
    {
        Dice dice = new Dice();
        for(int i = 0;i < 100; i++)
        {
            System.out.println(dice.flip());
        }
    }
}
