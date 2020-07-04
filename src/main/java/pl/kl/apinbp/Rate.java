package pl.kl.apinbp;

import lombok.Data;

@Data
public class Rate {
    private String no;
    private String effectiveDate;
    private Double ask;
    private Double bid;
}
