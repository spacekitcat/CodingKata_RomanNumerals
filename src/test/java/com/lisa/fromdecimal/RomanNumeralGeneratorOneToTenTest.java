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

public class RomanNumeralGeneratorOneToTenTest {

    private RomanNumeralGeneratorImpl sut;

    @Before
    public void setup() {
        sut = new RomanNumeralGeneratorImpl();
        FromIntegerConfiguration integerToNumeralLimitations = new FromIntegerConfiguration();
        integerToNumeralLimitations.setMinimumInputInteger(1);
        integerToNumeralLimitations.setMaximumInputInteger(10);
        sut.setInputLimitations(integerToNumeralLimitations);

        Map<Integer, String> mappings = new HashMap<>();
        mappings.put(1, "I");
        mappings.put(4, "IV");
        mappings.put(5, "V");
        mappings.put(9, "IX");
        mappings.put(10, "X");
        NumeralTable numeralTable = new NumeralTable();
        numeralTable.setDecimalToNumeralMap(mappings);
        sut.setNumeralTable(numeralTable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generate_outsideLowerBound_throwsException() {
        sut.setApplicationMessagesConfiguration(new ApplicationMessagesConfiguration());
        sut.generate(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generate_outsideUpperBound_throwsException() {
        sut.setApplicationMessagesConfiguration(new ApplicationMessagesConfiguration());
        sut.generate(11);
    }

    @Test
    public void generate_lowerBoundary_returnsCorrectRomanNumerals() {
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

    @Test
    public void generate_four_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(4))
                .as("4 should translate to IV")
                .isEqualTo("IV");
    }

    @Test
    public void generate_five_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(5))
                .as("5 should translate to V")
                .isEqualTo("V");
    }

    @Test
    public void generate_six_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(6))
                .as("6 should translate to VI")
                .isEqualTo("VI");
    }

    @Test
    public void generate_seven_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(7))
                .as("7 should translate to VII")
                .isEqualTo("VII");
    }

    @Test
    public void generate_eight_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(8))
                .as("8 should translate to VIII")
                .isEqualTo("VIII");
    }

    @Test
    public void generate_nine_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(9))
                .as("9 should translate to IX")
                .isEqualTo("IX");
    }

    @Test
    public void generate_ten_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(10))
                .as("10 should translate to X")
                .isEqualTo("X");
    }
}
