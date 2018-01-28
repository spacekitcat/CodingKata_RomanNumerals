package com.lisa.integration.bbc.autowire;

import com.lisa.Application;
import com.lisa.configuration.FromIntegerConfiguration;
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
public class FromIntegerConfigurationIT {

    @Autowired
    private FromIntegerConfiguration sut;

    @Test
    public void nonNullInstanceAcquired() {
        assertThat(sut).isNotNull();
    }

    @Test
    public void hasCorrectMaximumInputIntegerValue() {
        assertThat(sut.getMaximumInputInteger()).isEqualTo(3999);
    }

    @Test
    public void hasCorrectMinimumInputIntegerValue() {
        assertThat(sut.getMinimumInputInteger()).isEqualTo(1);
    }
}
