package com.lisa.integration.bbc.autowire;

import com.lisa.Application;
import com.lisa.configuration.NumeralRepresentationConfiguration;
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
public class NumeralRepresentationConfigurationIT {

    @Autowired
    private NumeralRepresentationConfiguration sut;

    @Test
    public void nonNullInstanceAcquired() {
        assertThat(sut).isNotNull();
    }

    @Test
    public void numeralTableInstanceAcquired() {
        assertThat(sut.getNumeralTable()).isNotNull();
    }

    @Test
    public void numeralTableBeanReturned() {
        assertThat(sut.numeralTable()).isNotNull();
    }
}
