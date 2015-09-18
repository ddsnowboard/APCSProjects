import java.util.*;
class MPG {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How long did you drive?");
        double miles = scanner.nextDouble();
        System.out.println("\nHow much gas did you use?");
        double gallons = scanner.nextDouble();
        System.out.printf("%nYou got %f MPG%n", miles / gallons);
    }
}
