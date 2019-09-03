package com.lisa.convertfrominteger;

import com.lisa.configuration.ApplicationMessagesConfiguration;
import com.lisa.configuration.FromIntegerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FromIntegerCommandLineRunner implements ApplicationRunner {

    @Autowired
    private RomanNumeralGenerator romanNumeralGenerator;

    @Autowired
    private FromIntegerConfiguration fromIntegerConfiguration;

    private static final String APP_INPUT_ERROR_KEY = "app.validation.input.decimal.error";

    public void setApplicationMessagesConfiguration(ApplicationMessagesConfiguration applicationMessagesConfiguration) {
        this.applicationMessagesConfiguration = applicationMessagesConfiguration;
    }

    @Autowired
    private ApplicationMessagesConfiguration applicationMessagesConfiguration;

    private List<Integer> convertArgumentsToIntegers(ApplicationArguments arguments) {
        List<Integer> integerList = new ArrayList<>();

        for (String strArgument : arguments.getNonOptionArgs()) {
            if (strArgument.trim().isEmpty()) {
                continue;
            }
            Integer asInteger = Integer.parseInt(strArgument.trim());
            integerList.add(asInteger);
        }

        return integerList;
    }

    private boolean validateApplicationArguments(ApplicationArguments args) {
        return !args.getNonOptionArgs().isEmpty() &&
                !(args.getNonOptionArgs().size() == 1 && args.getNonOptionArgs().get(0).trim().isEmpty());
    }

    private boolean validateIntegerForConversion(Integer inputInteger) {
        return inputInteger >= fromIntegerConfiguration.getMinimumInputInteger() &&
                            inputInteger <= fromIntegerConfiguration.getMaximumInputInteger();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (!validateApplicationArguments(args)) {
            System.out.println(applicationMessagesConfiguration.getMessage(APP_INPUT_ERROR_KEY));
        } else {
            List<Integer> integerList = new ArrayList<>();
            try {
                for (Integer nextInput : convertArgumentsToIntegers(args)) {
                    if (!validateIntegerForConversion(nextInput)) {
                        integerList.clear();
                        System.err.print(applicationMessagesConfiguration.getMessage(APP_INPUT_ERROR_KEY));
                    } else {
                        integerList.add(nextInput);
                    }
                }
            } catch (NumberFormatException nfx) {
                integerList.clear();
                System.err.print(applicationMessagesConfiguration.getMessage(APP_INPUT_ERROR_KEY));
            }

            for (Integer num : integerList) {
                System.out.print(romanNumeralGenerator.generate(num) + " ");
            }
            System.out.println();
        }

    }

    public void setFromIntegerConfiguration(FromIntegerConfiguration fromIntegerConfiguration) {
        this.fromIntegerConfiguration = fromIntegerConfiguration;
    }

    public void setRomanNumeralGenerator(RomanNumeralGenerator romanNumeralGenerator) {
        this.romanNumeralGenerator = romanNumeralGenerator;
    }
}
