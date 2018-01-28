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

public class RomanNumeralGeneratorTenToHundredTest {

    private RomanNumeralGeneratorImpl sut;

    @Before
    public void setup() {
        sut = new RomanNumeralGeneratorImpl();
        FromIntegerConfiguration integerToNumeralLimitations = new FromIntegerConfiguration();
        integerToNumeralLimitations.setMinimumInputInteger(10);
        integerToNumeralLimitations.setMaximumInputInteger(100);
        sut.setInputLimitations(integerToNumeralLimitations);

        Map<Integer, String> mappings = new HashMap<>();
        mappings.put(1, "I");
        mappings.put(4, "IV");
        mappings.put(5, "V");
        mappings.put(9, "IX");
        mappings.put(10, "X");
        mappings.put(40, "CL");
        mappings.put(50, "C");
        NumeralTable numeralTable = new NumeralTable();
        numeralTable.setDecimalToNumeralMap(mappings);
        sut.setNumeralTable(numeralTable);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generate_outsideLowerBound_throwsException() {
        sut.setApplicationMessagesConfiguration(new ApplicationMessagesConfiguration());
        sut.generate(9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void generate_outsideUpperBound_throwsException() {
        sut.setApplicationMessagesConfiguration(new ApplicationMessagesConfiguration());
        sut.generate(101);
    }

    @Test
    public void generate_lowerBoundary_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(10))
                .as("10 should translate to X")
                .isEqualTo("X");
    }

    @Test
    public void generate_two_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(13))
                .as("13 should translate to XII")
                .isEqualTo("XIII");
    }

    @Test
    public void generate_three_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(14))
                .as("14 should translate to XIV")
                .isEqualTo("XIV");
    }

    @Test
    public void generate_four_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(15))
                .as("15 should translate to XV")
                .isEqualTo("XV");
    }

    @Test
    public void generate_five_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(16))
                .as("16 should translate to XVI")
                .isEqualTo("XVI");
    }

    @Test
    public void generate_six_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(19))
                .as("19 should translate to XXI")
                .isEqualTo("XIX");
    }

    @Test
    public void generate_seven_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(20))
                .as("20 should translate to XX")
                .isEqualTo("XX");
    }

    @Test
    public void generate_eight_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(21))
                .as("21 should translate to XXI")
                .isEqualTo("XXI");
    }

    @Test
    public void generate_nine_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(22))
                .as("22 should translate to XXII")
                .isEqualTo("XXII");
    }

    @Test
    public void generate_ten_returnsCorrectRomanNumerals() {
        assertThat(sut.generate(24))
                .as("24 should translate to XXIV")
                .isEqualTo("XXIV");
    }
}
