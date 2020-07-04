package pl.kl.apinbp;

import lombok.extern.log4j.Log4j;
import pl.kl.apinbp.exception.DateTimeParsingException;
import pl.kl.apinbp.model.NBPApiParameters;
import pl.kl.apinbp.model.NBPCurrency;
import pl.kl.apinbp.model.Rate;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Log4j
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        NBPApiParameters parameters = new NBPApiParameters();
        NBPApi api = new NBPApi();

        loadAndSetCurrency(scanner, parameters);
        loadAndSetEndDate(scanner, parameters);
        loadAndSetStartDate(scanner, parameters);

        List<Rate> rates = api.requestBidAskRates(parameters);
        rates.forEach(log::info);
    }

    private static void loadAndSetEndDate(Scanner scanner, NBPApiParameters parameters) {
        do {
            log.info("Please enter end date [yyyy-MM-dd]:");
            try {
                parameters.setEndDate(scanner.nextLine());
            } catch (DateTimeParsingException e) {
                log.error("Error: Wrong date - " + e.getMessage());
            }
        } while (parameters.getEndDate() == null);
    }

    private static void loadAndSetStartDate(Scanner scanner, NBPApiParameters parameters) {
        do {
            log.info("Please enter start date [yyyy-MM-dd]:");
            try {
                parameters.setStartDate(scanner.nextLine());
            } catch (DateTimeParsingException e) {
                log.error("Error: Wrong date - " + e.getMessage());
            }
        } while (parameters.getStartDate() == null);
    }

    private static void loadAndSetCurrency(Scanner scanner, NBPApiParameters parameters) {
        do {
            log.info("Please enter currency [dolar, euro, rubel]:");
            Optional<NBPCurrency> optionalNBPCurrency = NBPCurrency.parse(scanner.nextLine());
            if (optionalNBPCurrency.isPresent()) {
                parameters.setCurrency(optionalNBPCurrency.get());
            } else {
                log.error("Error: Unrecognized currency.");
            }
        } while (parameters.getCurrency() == null);
    }
}
