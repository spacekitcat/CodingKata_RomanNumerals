package com.lisa.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="application-messages")
public class ApplicationMessagesConfiguration {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String id) {
        if (messageSource == null) {
            return id;
        }

        return messageSource.getMessage(id, new Object[]{}, LocaleContextHolder.getLocale());
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
