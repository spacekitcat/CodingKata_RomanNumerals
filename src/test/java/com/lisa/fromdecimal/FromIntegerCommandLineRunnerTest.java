package com.lisa.fromdecimal;

import com.lisa.configuration.ApplicationMessagesConfiguration;
import com.lisa.configuration.FromIntegerConfiguration;
import com.lisa.convertfrominteger.FromIntegerCommandLineRunner;
import com.lisa.convertfrominteger.RomanNumeralGenerator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.StaticMessageSource;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(MockitoJUnitRunner.class)
public class FromIntegerCommandLineRunnerTest {

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    @Mock
    private RomanNumeralGenerator romanNumeralGenerator;

    private FromIntegerCommandLineRunner sut;

    @Before
    public void setup() {
        sut = new FromIntegerCommandLineRunner();
        ApplicationMessagesConfiguration applicationMessagesConfiguration = new ApplicationMessagesConfiguration();
        LocaleContextHolder.setLocale(Locale.ENGLISH);
        StaticMessageSource staticMessageSource = new StaticMessageSource();
        staticMessageSource.addMessage(
                "app.validation.input.decimal.error",
                Locale.ENGLISH  ,
                "invalid-input"
        );
        applicationMessagesConfiguration.setMessageSource(staticMessageSource);
        sut.setApplicationMessagesConfiguration(applicationMessagesConfiguration);

        FromIntegerConfiguration fromIntegerConfiguration = new FromIntegerConfiguration();
        fromIntegerConfiguration.setMaximumInputInteger(10);
        fromIntegerConfiguration.setMinimumInputInteger(1);
        sut.setFromIntegerConfiguration(fromIntegerConfiguration);

        sut.setRomanNumeralGenerator(romanNumeralGenerator);
    }

    @Test
    public void run_inputTooSmall_usesCorrectValidationMsg() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"0"}));
        outputCapture.expect(is("invalid-input\n"));
    }

    @Test
    public void run_noInput_usesCorrectValidationMsg() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {""}));
        outputCapture.expect(is("invalid-input\n"));
    }

    @Test
    public void run_spacesInput_usesCorrectValidationMsg() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"   "}));
        outputCapture.expect(is("invalid-input\n"));
    }


    @Test
    public void run_tabInput_usesCorrectValidationMsg() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {" \t "}));
        outputCapture.expect(is("invalid-input\n"));
    }

    @Test
    public void run_inputTooSmall_zeroGeneratorInvocations() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"0"}));
        Mockito.verifyZeroInteractions(romanNumeralGenerator);
    }

    @Test
    public void run_inputTooLarge_usesCorrectValidationMsg() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"11"}));
        outputCapture.expect(is("invalid-input\n"));
    }

    @Test
    public void run_inputTooLarge_zeroGeneratorInvocations() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"11"}));
        Mockito.verifyZeroInteractions(romanNumeralGenerator);
    }

    @Test
    public void run_inputNotADecimalNumber_usesCorrectValidationMsg() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[]{"X"}));
        outputCapture.expect(is("invalid-input\n"));
    }

    @Test
    public void run_inputNotADecimalNumber_zeroGeneratorInvocations() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"X"}));
        Mockito.verifyZeroInteractions(romanNumeralGenerator);
    }

    @Test
    public void run_inputNotAnInteger_usesCorrectValidationMsg() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"4.4"}));
        outputCapture.expect(is("invalid-input\n"));
    }

    @Test
    public void run_inputNotAnInteger_zeroGeneratorInvocations() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"4.4"}));
        Mockito.verifyZeroInteractions(romanNumeralGenerator);
    }

    @Test
    public void run_inputIsNegative_usesCorrectValidationMsg() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"-8"}));
        outputCapture.expect(is("invalid-input\n"));
    }

    @Test
    public void run_inputIsNegative_zeroGeneratorInvocations() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"-8"}));
        Mockito.verifyZeroInteractions(romanNumeralGenerator);
    }

    @Test
    public void run_inputValidLowerBoundary_usesCorrectValidationMsg() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"1"}));
        Mockito.verify(romanNumeralGenerator, Mockito.times(1)).generate(1);
    }

    @Test
    public void run_inputValidUpperBoundary_usesCorrectValidationMsg() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"10"}));
        Mockito.verify(romanNumeralGenerator, Mockito.times(1)).generate(10);
    }

    @Test
    public void run_inputValid_usesCorrectValidationMsg() throws Exception {
        sut.run(new DefaultApplicationArguments(new String[] {"5"}));
        Mockito.verify(romanNumeralGenerator, Mockito.times(1)).generate(5);
    }

    @Test
    public void run_inputValidMultiTwo_returnsCorrectFormat() throws Exception {
        Mockito.when(romanNumeralGenerator.generate(Mockito.anyInt())).thenReturn("X");
        sut.run(new DefaultApplicationArguments(new String[] {"5", "3"}));
        assertThat(outputCapture.toString()).isEqualTo("X X \n");
    }

    @Test
    public void run_inputValidMultiThree_returnsCorrectFormat() throws Exception {
        Mockito.when(romanNumeralGenerator.generate(Mockito.anyInt())).thenReturn("X");
        sut.run(new DefaultApplicationArguments(new String[] {"5", "3", "8"}));
        assertThat(outputCapture.toString()).isEqualTo("X X X \n");
    }

    @Test
    public void run_inputValidMultiThreeExtraSpaces_returnsCorrectFormat() throws Exception {
        Mockito.when(romanNumeralGenerator.generate(Mockito.anyInt())).thenReturn("X");
        sut.run(new DefaultApplicationArguments(new String[] {" 5", "  3    ", "8  "}));
        assertThat(outputCapture.toString()).isEqualTo("X X X \n");
    }

    @Test
    public void run_inputMultiSecondInvalid_throwsCorrectException() throws Exception {
        Mockito.when(romanNumeralGenerator.generate(Mockito.anyInt())).thenReturn("X");
        sut.run(new DefaultApplicationArguments(new String[] {"5", "X", "8"}));
        outputCapture.expect(is("invalid-input\n"));
    }
}
