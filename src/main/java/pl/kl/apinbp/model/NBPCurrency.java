package pl.kl.apinbp.model;

import java.util.Optional;

public enum NBPCurrency {
    AMERICAN_DOLLAR("USD"),
    RUSSIAN_RUBEL("RUB"),
    EURO("EUR");

    private String shortName;

    NBPCurrency(String shortName) {
        this.shortName = shortName;
    }

    public String getShortName() {
        return shortName;
    }

    //NBPCurrency currency = NBPCurrency.EURO;
    //sout(currency); EURO
    //sout(currency.getShortName); EUR

    public static Optional<NBPCurrency> parse(String input) {
        if (input.toUpperCase().contains("DOLAR")) {
            return Optional.of(NBPCurrency.AMERICAN_DOLLAR);
        } else if (input.toUpperCase().contains("RUBEL")) {
            return Optional.of(NBPCurrency.RUSSIAN_RUBEL);
        } else if (input.toUpperCase().contains("EURO")) {
            return Optional.of(NBPCurrency.EURO);
        }
        return Optional.empty();
    }
}
