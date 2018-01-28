package com.lisa.convertfrominteger;

import com.lisa.configuration.ApplicationMessagesConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class NumeralTable {

    private Map<Integer, String> decimalToNumeralMap;

    @Autowired
    private ApplicationMessagesConfiguration applicationMessagesConfiguration;

    public Map<Integer, String> getDecimalToNumeralMap() {
        return decimalToNumeralMap;
    }

    public void setDecimalToNumeralMap(Map<Integer, String> decimalToNumeralMap) {
        if (decimalToNumeralMap == null) {
            throw new NullPointerException(applicationMessagesConfiguration.getMessage("null-mapping-table-error"));
        }

        this.decimalToNumeralMap = decimalToNumeralMap;
    }

    public Iterator<Integer> getDecimalIterator() {
        if (decimalToNumeralMap == null) {
            throw new NullPointerException(applicationMessagesConfiguration.getMessage("null-mapping-table-error"));
        }

        return this.decimalToNumeralMap.keySet()
                .stream().sorted()
                .collect(Collectors.toCollection(LinkedList::new))
                .descendingIterator();
    }

    public String getValue(Integer key) {
        if (key == null) {
            throw new NullPointerException(applicationMessagesConfiguration.getMessage("app.error.null.numeral.key"));
        }

        if (!this.decimalToNumeralMap.containsKey(key)) {
            throw new IllegalArgumentException(applicationMessagesConfiguration.getMessage("app.error.unknown.numeral.key"));
        }

        return decimalToNumeralMap.get(key);
    }

    public void setApplicationMessagesConfiguration(ApplicationMessagesConfiguration applicationMessagesConfiguration) {
        this.applicationMessagesConfiguration = applicationMessagesConfiguration;
    }
}
