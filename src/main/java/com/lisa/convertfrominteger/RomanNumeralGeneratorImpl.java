package com.lisa.convertfrominteger;

import com.lisa.configuration.ApplicationMessagesConfiguration;
import com.lisa.configuration.FromIntegerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class RomanNumeralGeneratorImpl implements RomanNumeralGenerator {

    public RomanNumeralGeneratorImpl() {
        // [Sonar]
    }

    @Autowired
    private FromIntegerConfiguration inputLimitations;

    @Autowired
    private NumeralTable numeralTable;

    @Autowired
    private ApplicationMessagesConfiguration applicationMessagesConfiguration;

    private boolean boundaryCheck(int input) {
        return input >= inputLimitations.getMinimumInputInteger() &&
                input <= inputLimitations.getMaximumInputInteger();
    }

    private String generateNumeralRepetition(Integer repetitions, Integer forKey) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i=0; i<repetitions; ++i) {
            stringBuilder.append(numeralTable.getValue(forKey));
        }

        return stringBuilder.toString();
    }

    @Override
    public String generate(int number) {
        StringBuilder stringBuilder = new StringBuilder();

        if (!boundaryCheck(number)) {
            throw new IllegalArgumentException(
                    applicationMessagesConfiguration.getMessage("app.validation.input.decimal.error")
            );
        }

        Iterator<Integer> mapKeyIterator = numeralTable.getDecimalIterator();
        int remainingNumber = number;
        while (mapKeyIterator.hasNext()) {
            Integer nextKey = mapKeyIterator.next();
            if (nextKey > 0) {
                stringBuilder.append(this.generateNumeralRepetition(remainingNumber / nextKey, nextKey));
                remainingNumber = remainingNumber % nextKey;
            } else if (nextKey == 0 && stringBuilder.length() == 0) {
                stringBuilder.append(numeralTable.getValue(0));
            }
        }

        return stringBuilder.toString();
    }

    public void setInputLimitations(FromIntegerConfiguration inputLimitations) {
        this.inputLimitations = inputLimitations;
    }

    public void setNumeralTable(NumeralTable numeralTable) {
        this.numeralTable = numeralTable;
    }

    public void setApplicationMessagesConfiguration(ApplicationMessagesConfiguration applicationMessagesConfiguration) {
        this.applicationMessagesConfiguration = applicationMessagesConfiguration;
    }
}
