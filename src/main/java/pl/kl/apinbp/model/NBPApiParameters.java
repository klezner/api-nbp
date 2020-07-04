package pl.kl.apinbp.model;

import lombok.*;
import pl.kl.apinbp.DateTimeUtilities;
import pl.kl.apinbp.exception.DateTimeParsingException;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NBPApiParameters {
    private LocalDate startDate;
    private LocalDate endDate;

    @Setter
    private NBPCurrency currency;

    public void setEndDate(String userInput) throws DateTimeParsingException {
        this.endDate = DateTimeUtilities.loadEndDate(userInput);
    }

    public void setStartDate(String userInput) throws DateTimeParsingException {
        this.startDate = DateTimeUtilities.loadStartDate(userInput, endDate);
    }
}
