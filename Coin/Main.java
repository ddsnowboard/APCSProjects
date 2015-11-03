class Main {
    public static void main(String[] args)
    {
        Coin coin = new Coin();
        for(int i = 0;i < 100; i++)
        {
            System.out.println(coin.flip());
        }
    }
}
