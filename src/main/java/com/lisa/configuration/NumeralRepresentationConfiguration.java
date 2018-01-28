package com.lisa.configuration;

import com.lisa.convertfrominteger.NumeralTable;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="numeral-representation")
public class NumeralRepresentationConfiguration {

    private NumeralTable numeralTable;

    @Bean
    public NumeralTable numeralTable() {
        return numeralTable;
    }

    public NumeralTable getNumeralTable() {
        return numeralTable;
    }

    public void setNumeralTable(NumeralTable numeralTable) {
        this.numeralTable = numeralTable;
    }
}
