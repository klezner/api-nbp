package pl.kl.apinbp;

import lombok.extern.java.Log;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        NBPApiParameters parameters = new NBPApiParameters();
        NBPApi api = new NBPApi();

        loadAndSetCurrency(scanner, parameters);
        loadAndSetEndDate(scanner, parameters);
        loadAndSetStartDate(scanner, parameters);
        api.requestBidAskRates(parameters);

    }

    private static void loadAndSetEndDate(Scanner scanner, NBPApiParameters parameters) {
        do {
            System.out.println("Please enter end date [yyyy-MM-dd]:");
            try {
                parameters.setEndDate(scanner.nextLine());
            } catch (DateTimeParsingException e) {
                System.err.println("Error: Wrong date - " + e.getMessage());
            }
        } while (parameters.getEndDate() == null);
    }

    private static void loadAndSetStartDate(Scanner scanner, NBPApiParameters parameters) {
        do {
            System.out.println("Please enter start date [yyyy-MM-dd]:");
            try {
                parameters.setStartDate(scanner.nextLine());
            } catch (DateTimeParsingException e) {
                System.err.println("Error: Wrong date - " + e.getMessage());
            }
        } while (parameters.getStartDate() == null);
    }

    private static void loadAndSetCurrency(Scanner scanner, NBPApiParameters parameters) {
        do {
            System.out.println("Please enter currency [dolar, euro, rubel]:");
            Optional<NBPCurrency> optionalNBPCurrency = NBPCurrency.parse(scanner.nextLine());
            if (optionalNBPCurrency.isPresent()) {
                parameters.setCurrency(optionalNBPCurrency.get());
            } else {
                System.err.println("Error: Unrecognized currency.");
            }
        } while (parameters.getCurrency() == null);
    }
}
