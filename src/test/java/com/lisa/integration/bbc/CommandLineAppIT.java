package com.lisa.integration.bbc;

import com.lisa.Application;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import static org.hamcrest.Matchers.containsString;

@RunWith(Parameterized.class)
@ContextConfiguration(classes = {Application.class})
@ActiveProfiles("bbc-kata-specification")
public class CommandLineAppIT {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"0", "Please enter a valid decimal number - the input value must be between 1 and 3999 (inclusive)"},
                {"1", "I "},
                {"2", "II "},
                {"3", "III "},
                {"4", "IV "},
                {"5", "V "},
                {"6", "VI "},
                {"7", "VII "},
                {"8", "VIII "},
                {"9", "IX "},
                {"10", "X "},
                {"20", "XX "},
                {"40", "XL "},
                {"50", "L "},
                {"90", "XC "},
                {"100", "C "},
                {"400", "CD "},
                {"500", "D "},
                {"900", "CM "},
                {"1000", "M "},
                {"3999", "MMMCMXCIX "},
                {"4000", "Please enter a valid decimal number - the input value must be between 1 and 3999 (inclusive)"},
                {"", "Please enter a valid decimal number - the input value must be between 1 and 3999 (inclusive)"},
                {" ", "Please enter a valid decimal number - the input value must be between 1 and 3999 (inclusive)"},
                {"\t", "Please enter a valid decimal number - the input value must be between 1 and 3999 (inclusive)"},
                {"\n", "Please enter a valid decimal number - the input value must be between 1 and 3999 (inclusive)"},
                {"z", "Please enter a valid decimal number - the input value must be between 1 and 3999 (inclusive)"},
                {"1.2", "Please enter a valid decimal number - the input value must be between 1 and 3999 (inclusive)"},
                {"-200", "Please enter a valid decimal number - the input value must be between 1 and 3999 (inclusive)"},
                {"1 3 5", "I III V "},
                {" 1  3   5 ", "I III V "}
        });
    }

    private final String input;
    private final String expectedOutput;

    public CommandLineAppIT(String input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @ClassRule
    public static final SpringClassRule SPRING_AWARE = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    private TestContextManager testContextManager;

    @Before
    public void setup() throws Exception {
        LocaleContextHolder.setLocale(Locale.ENGLISH);
        testContextManager = new TestContextManager(getClass());
        testContextManager.prepareTestInstance(this);
    }

    @Test
    public void shouldTranslate() throws Exception {
        SpringApplication.run(Application.class, this.input.split(" "));
        outputCapture.expect(containsString("\n" + this.expectedOutput + "\n"));
    }
}
