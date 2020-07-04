package pl.kl.apinbp;

import lombok.Data;

import java.util.List;

//POJO - PLAIN OLD JAVA OBJECT
//- PUSTY KONSTRUKTOR
//- PRYWATNE POLA
//-GETTERY I SETTERY
@Data
public class ExchangeRates {
    private String table;
    private String currency;
    private String code;
    private List<Rate> rates;
}
