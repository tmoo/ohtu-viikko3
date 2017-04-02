import java.util.*;
import ohtu.Multiplier;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int kerrottavaNumero = 3;
        Multiplier kolme = new Multiplier(kerrottavaNumero);
        System.out.println("Anna luku: ");
        int luku = scanner.nextInt();

        System.out.println("Luku kertaa kolme on " + kolme.multipliedBy(luku));
    }
}
