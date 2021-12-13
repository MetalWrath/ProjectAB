package com.ancient.projectab.pojos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyPojo {
    private Rates rates;

    public CurrencyPojo() {
    }

    @JsonCreator
    public CurrencyPojo(@JsonProperty("rates") Rates rates) {
        this.rates = rates;
    }

    public Rates getRates() {
        return rates;
    }

    public void setRates(Rates rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CurrencyPojo{" +
                "rates=" + rates +
                '}';
    }

    @Component
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Rates {

        private double rub;
        private double inr;
        private double eur;

        public Rates() {
        }

        @JsonCreator
        public Rates(@JsonProperty("RUB") double rub,
                     @JsonProperty("INR") double inr,
                     @JsonProperty("EUR") double eur) {
            this.rub = rub;
            this.inr = inr;
            this.eur = eur;
        }

        public double getRub() {
            return rub;
        }

        public void setRub(double rub) {
            this.rub = rub;
        }

        public double getInr() {
            return inr;
        }

        public void setInr(double inr) {
            this.inr = inr;
        }

        public double getEur() {
            return eur;
        }

        public void setEur(double eur) {
            this.eur = eur;
        }

        @Override
        public String toString() {
            return "Rates{" +
                    "rub=" + rub +
                    ", inr=" + inr +
                    ", eur=" + eur +
                    '}';
        }
    }
}

