package pl.kl.apinbp;

import lombok.extern.log4j.Log4j;
import pl.kl.apinbp.exception.DateTimeParsingException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Log4j
public class DateTimeUtilities {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyy-MM-dd");

    /**
     * Method processes input string given in format <code>yyyy-MM-dd</code>
     * @param input - input in format <code>yyyy-MM-dd</code>
     * @return parsed local date instance
     * @throws DateTimeParsingException - exception that might happen when there is parsing exception or date is after yesterday.
     */

    public static LocalDate loadEndDate(String input) throws DateTimeParsingException {
        LocalDate loadedDate;
        try {
            loadedDate = LocalDate.parse(input, FORMATTER);

            //SPRAWDZAMY CZY ZALADOWANA DATA JEST PO WCZORAJ
            if (loadedDate.isAfter(LocalDate.now().minusDays(1))) {
                throw new DateTimeParseException("End date should be no later than yesterday.", input, 1);
            }
        } catch (DateTimeParseException dtpe) {
            log.error("Parsing date error");
            throw new DateTimeParsingException(dtpe.getMessage());
        }
        return loadedDate;
    }

    public static LocalDate loadStartDate(String input, LocalDate endDate) throws DateTimeParsingException {
        LocalDate loadedDate;
        try {
            loadedDate = LocalDate.parse(input, FORMATTER);

            //SPRAWDZAMY CZY ZALADOWANA DATA JEST PRZED DATA KONCOWA
            if (loadedDate.isAfter(endDate.minusDays(1))) {
                throw new DateTimeParseException("Start date should be no later than end date.", input, 1);
            }
        } catch (DateTimeParseException dtpe) {
            log.error("Parsing date error");
            throw new DateTimeParsingException(dtpe.getMessage());
        }
        return loadedDate;
    }
}
