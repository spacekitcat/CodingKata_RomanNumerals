package com.lisa.fromdecimal;

import com.lisa.configuration.ApplicationMessagesConfiguration;
import com.lisa.configuration.FromIntegerConfiguration;
import com.lisa.convertfrominteger.NumeralTable;
import com.lisa.convertfrominteger.RomanNumeralGeneratorImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class RomanNumeralGeneratorZeroTest {

    private RomanNumeralGeneratorImpl sut;

    @Before
    public void setup() {
        sut = new RomanNumeralGeneratorImpl();
        FromIntegerConfiguration integerToNumeralLimitations = new FromIntegerConfiguration();
        integerToNumeralLimitations.setMinimumInputInteger(0);
        integerToNumeralLimitations.setMaximumInputInteger(3);
        sut.setInputLimitations(integerToNumeralLimitations);

        Map<Integer, String> mappings = new HashMap<>();
        mappings.put(0, "@");
        mappings.put(1, "I");
        NumeralTable numeralTable = new NumeralTable();
        numeralTable.setDecimalToNumeralMap(mappings);
        sut.setNumeralTable(numeralTable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generate_outsideUpperBound_throwsException() {
        sut.setApplicationMessagesConfiguration(new ApplicationMessagesConfiguration());
        sut.generate(4);
    }

    @Test
    public void generate_lowerBoundary_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(0))
                .as("0 should translate to @")
                .isEqualTo("@");
    }

    @Test
    public void generate_one_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(1))
                .as("1 should translate to I")
                .isEqualTo("I");
    }

    @Test
    public void generate_two_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(2))
                .as("2 should translate to II")
                .isEqualTo("II");
    }

    @Test
    public void generate_three_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(3))
                .as("3 should translate to III")
                .isEqualTo("III");
    }
}
