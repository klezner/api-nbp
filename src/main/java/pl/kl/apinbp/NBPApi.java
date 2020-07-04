package pl.kl.apinbp;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NBPApi {
    private final static String API_BID_ASK_ENDPOINT = "http://api.nbp.pl/api/exchangerates/rates/C/{currency}/{startDate}/{endDate}/?format=json";
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    public void requestBidAskRates(NBPApiParameters parameters) {
        String requestUrl = prepareRequestUrl(parameters);
        System.out.println("Request Url: " + requestUrl); // TODO : zmienić na loggera

        HttpRequest request = HttpRequest.newBuilder() //STWORZ ZAPYTANIE
                .GET()                                 //TYPU GET (pobieramy informacje)
                .uri(URI.create(requestUrl))           //NA ADRES
                .build();                              //SFINALIZUJ STWORZENIE OBIEKTU (WZORZEC PROJEKTOWY BUILDER)

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString()); //WYSLIJ ZAPYTANIE, SPODZIEWAMY SIE ODPOWIEDZI W POSTACI STRING(BODYHANDLERS)

            if (response.statusCode() == 200) {
                System.out.println("Response: " + response.body());
            } else {
                System.out.println("Error: " + response.statusCode());
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String prepareRequestUrl(NBPApiParameters parameters) {
        return API_BID_ASK_ENDPOINT
                .replaceAll("\\{currency\\}", parameters.getCurrency().getShortName())
                .replaceAll("\\{startDate\\}", parameters.getStartDate().toString())
                .replaceAll("\\{endDate\\}", parameters.getEndDate().toString());
    }
}
