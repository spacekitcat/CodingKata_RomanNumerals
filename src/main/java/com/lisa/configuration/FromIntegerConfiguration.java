package com.lisa.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="input-limits.from-integer")
public class FromIntegerConfiguration {

    private int minimumInputInteger;

    private int maximumInputInteger;

    public int getMinimumInputInteger() {
        return minimumInputInteger;
    }

    public void setMinimumInputInteger(int minimumInputInteger) {
        this.minimumInputInteger = minimumInputInteger;
    }

    public int getMaximumInputInteger() {
        return maximumInputInteger;
    }

    public void setMaximumInputInteger(int maximumInputInteger) {
        this.maximumInputInteger = maximumInputInteger;
    }
}
