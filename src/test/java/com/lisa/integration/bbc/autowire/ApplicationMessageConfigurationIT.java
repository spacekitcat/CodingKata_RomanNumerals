package com.lisa.integration.bbc.autowire;

import com.lisa.Application;
import com.lisa.configuration.ApplicationMessagesConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class})
@ActiveProfiles("bbc-kata-specification")
public class ApplicationMessageConfigurationIT {

    @Autowired
    private ApplicationMessagesConfiguration sut;

    @Test
    public void nonNullInstanceAcquired() {
        assertThat(sut).isNotNull();
    }

    @Test
    public void hasNullNumeralKeyErrorString() {
        assertThat(sut.getMessage("app.error.null.numeral.key"))
                .containsIgnoringCase("null numeral key was provided");
    }

    @Test
    public void hasNullNumeralTableErrorString() {
        assertThat(sut.getMessage("app.error.null.mapping.table"))
                .containsIgnoringCase("numeral mapping is null");
    }

    @Test
    public void hasInvalidNumeralTableErrorString() {
        assertThat(sut.getMessage("app.error.unknown.numeral.key"))
                .containsIgnoringCase("unrecognised numeral key");
    }
}
