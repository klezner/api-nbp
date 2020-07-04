package pl.kl.apinbp;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        NBPApiParameters parameters = new NBPApiParameters();

        loadAndSetCurrency(scanner, parameters);
        loadAndSetEndDate(scanner, parameters);
        loadAndSetStartDate(scanner, parameters);
        System.out.println("SUCCESS!!!");
    }

    private static void loadAndSetEndDate(Scanner scanner, NBPApiParameters parameters) {
        do {
            System.out.println("Please enter end date [yyyy-MM-dd]:");
            try {
                parameters.setEndDate(scanner.nextLine());
            } catch (DateTimeParsingException e) {
                System.err.println("Wrong date: " + e.getMessage());
            }
        } while (parameters.getEndDate() == null);
    }

    private static void loadAndSetStartDate(Scanner scanner, NBPApiParameters parameters) {
        do {
            System.out.println("Please enter start date [yyyy-MM-dd]:");
            try {
                parameters.setStartDate(scanner.nextLine());
            } catch (DateTimeParsingException e) {
                System.err.println("Wrong date: " + e.getMessage());
            }
        } while (parameters.getStartDate() == null);
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
