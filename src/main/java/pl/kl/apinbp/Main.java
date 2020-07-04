package pl.kl.apinbp;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        NBPApiParameters parameters = new NBPApiParameters();

        loadAndSetCurrency(scanner, parameters);
    }

    private static void loadAndSetCurrency(Scanner scanner, NBPApiParameters parameters) {
        do {
            System.out.println("Please enter currency [dolar, euro, rubel]:");
            Optional<NBPCurrency> optionalNBPCurrency = NBPCurrency.parse(scanner.nextLine());
            if (optionalNBPCurrency.isPresent()) {
                parameters.setCurrency(optionalNBPCurrency.get());
            }
        } while (parameters.getCurrency() == null);
    }
}
