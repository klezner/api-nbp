package pl.kl.apinbp;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class NBPApi {
    private final static String API_BID_ASK_ENDPOINT = "http://api.nbp.pl/api/exchangerates/rates/C/{currency}/{startDate}/{endDate}/?format=json";
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Rate> requestBidAskRates(NBPApiParameters parameters) {
        String requestUrl = prepareRequestUrl(parameters);
        log.info("Request Url: " + requestUrl);

        HttpRequest request = HttpRequest.newBuilder() //STWORZ ZAPYTANIE
                .GET()                                 //TYPU GET (pobieramy informacje)
                .uri(URI.create(requestUrl))           //NA ADRES
                .build();                              //SFINALIZUJ STWORZENIE OBIEKTU (WZORZEC PROJEKTOWY BUILDER)

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()); //WYSLIJ ZAPYTANIE, SPODZIEWAMY SIE ODPOWIEDZI W POSTACI STRING(BODYHANDLERS)

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                ExchangeRates exchangeRates = objectMapper.readValue(responseBody, ExchangeRates.class);
                return exchangeRates.getRates();
            } else {
                log.error("Error: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        throw new NoResultException("There was no result from API call.");
    }

    private String prepareRequestUrl(NBPApiParameters parameters) {
        return API_BID_ASK_ENDPOINT
                .replaceAll("\\{currency\\}", parameters.getCurrency().getShortName())
                .replaceAll("\\{startDate\\}", parameters.getStartDate().toString())
                .replaceAll("\\{endDate\\}", parameters.getEndDate().toString());
    }
}
