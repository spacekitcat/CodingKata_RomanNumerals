package com.lisa.fromdecimal;

import com.lisa.configuration.ApplicationMessagesConfiguration;
import com.lisa.convertfrominteger.NumeralTable;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class NumeralTableTest {

    @Test
    public void setDecimalToNumeralMap_nullMap_throwsNullPointerException() {
        NumeralTable sut = new NumeralTable();
        sut.setApplicationMessagesConfiguration(new ApplicationMessagesConfiguration());

        try {
            sut.setDecimalToNumeralMap(null);
        } catch (NullPointerException npx) {
            assertThat(npx.getMessage()).isEqualTo("null-mapping-table-error");
        }
    }

    @Test
    public void setDecimalToNumeralMap_validMAp_getSet() {
        NumeralTable sut = new NumeralTable();

        Map<Integer, String> map = new HashMap<>();
        sut.setDecimalToNumeralMap(map);

        assertThat(sut.getDecimalToNumeralMap()).isEqualTo(map);
    }

    @Test
    public void getDecimalIterator_emptyMappings_noExceptions() {
        NumeralTable sut = new NumeralTable();

        sut.setDecimalToNumeralMap(new HashMap<>());

        sut.getDecimalIterator();
    }

    @Test
    public void getDecimalIterator_emptyMappings_emptyList() {
        NumeralTable sut = new NumeralTable();

        sut.setDecimalToNumeralMap(new HashMap<>());

        assertThat(sut.getDecimalIterator()).isEmpty();
    }

    @Test
    public void getDecimalIterator_singleEntryMappings_expectedResult() {
        NumeralTable sut = new NumeralTable();

        Map<Integer, String> numeralMapTable = new HashMap<>();
        numeralMapTable.put(1, "I");
        sut.setDecimalToNumeralMap(numeralMapTable);

        assertThat(sut.getDecimalIterator()).containsOnly(1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getValue_entryNotExistEmptyTable_throwsInvalidArgumentException() {
        NumeralTable sut = new NumeralTable();
        sut.setDecimalToNumeralMap(new HashMap<>());
        sut.setApplicationMessagesConfiguration(new ApplicationMessagesConfiguration());

        sut.getValue(3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getValue_entryNotExist_throwsInvalidArgumentException() {
        NumeralTable sut = new NumeralTable();

        Map<Integer, String> numeralMapTable = new HashMap<>();
        numeralMapTable.put(1, "I");
        sut.setDecimalToNumeralMap(numeralMapTable);
        sut.setApplicationMessagesConfiguration(new ApplicationMessagesConfiguration());

        sut.getValue(3);
    }

    @Test(expected = NullPointerException.class)
    public void getValue_entryNullExistEmptyTable_throwsNullPointerException() {
        NumeralTable sut = new NumeralTable();
        sut.setDecimalToNumeralMap(new HashMap<>());
        sut.setApplicationMessagesConfiguration(new ApplicationMessagesConfiguration());

        sut.getValue(null);
    }

    @Test(expected = NullPointerException.class)
    public void getValue_entryIsNull_throwsNullPointerException() {
        NumeralTable sut = new NumeralTable();

        Map<Integer, String> numeralMapTable = new HashMap<>();
        numeralMapTable.put(1, "I");
        sut.setDecimalToNumeralMap(numeralMapTable);
        sut.setApplicationMessagesConfiguration(new ApplicationMessagesConfiguration());

        sut.getValue(null);
    }

    @Test
    public void getValue_entryExistsSingleEntryTable_returnsExpectedValue() {
        NumeralTable sut = new NumeralTable();

        Map<Integer, String> numeralMapTable = new HashMap<>();
        numeralMapTable.put(1, "I");
        sut.setDecimalToNumeralMap(numeralMapTable);

        assertThat(sut.getValue(1)).isEqualTo("I");
    }

    @Test
    public void getValue_entryIsFirstInDualEntryTable_returnsExpectedValue() {
        NumeralTable sut = new NumeralTable();

        Map<Integer, String> numeralMapTable = new HashMap<>();
        numeralMapTable.put(1, "I");
        numeralMapTable.put(5, "V");
        sut.setDecimalToNumeralMap(numeralMapTable);

        assertThat(sut.getValue(1)).isEqualTo("I");
    }

    @Test
    public void getValue_entryIsLastInDualEntryTable_returnsExpectedValue() {
        NumeralTable sut = new NumeralTable();

        Map<Integer, String> numeralMapTable = new HashMap<>();
        numeralMapTable.put(1, "I");
        numeralMapTable.put(5, "V");
        sut.setDecimalToNumeralMap(numeralMapTable);

        assertThat(sut.getValue(5)).isEqualTo("V");
    }

    @Test
    public void getDecimalIterator_nullNumeralMap_throwsNullPointerException() {
        NumeralTable sut = new NumeralTable();
        sut.setApplicationMessagesConfiguration(new ApplicationMessagesConfiguration());

        try {
            sut.getDecimalIterator();
        } catch (NullPointerException npx) {
            assertThat(npx.getMessage()).isEqualTo("null-mapping-table-error");
        }
    }

    @Test
    public void getDecimalIterator_fourEntryMappings_expectedOrderedResult() {
        NumeralTable sut = new NumeralTable();

        Map<Integer, String> numeralMapTable = new HashMap<>();
        numeralMapTable.put(1, "I");
        numeralMapTable.put(100, "C");
        numeralMapTable.put(500, "D");
        numeralMapTable.put(1000, "M");
        sut.setDecimalToNumeralMap(numeralMapTable);

        assertThat(sut.getDecimalIterator()).containsSequence(1000, 500, 100, 1);
    }

    @Test
    public void getDecimalIterator_fourEntryMappingsReverseOrder_expectedOrderedResult() {
        NumeralTable sut = new NumeralTable();

        Map<Integer, String> numeralMapTable = new HashMap<>();
        numeralMapTable.put(1000, "M");
        numeralMapTable.put(500, "D");
        numeralMapTable.put(100, "C");
        numeralMapTable.put(1, "I");
        sut.setDecimalToNumeralMap(numeralMapTable);

        assertThat(sut.getDecimalIterator()).containsSequence(1000, 500, 100, 1);
    }


    @Test
    public void getDecimalIterator_fourEntryMappingsRandomOrder_expectedOrderedResult() {
        NumeralTable sut = new NumeralTable();

        Map<Integer, String> numeralMapTable = new HashMap<>();
        numeralMapTable.put(500, "D");
        numeralMapTable.put(1000, "M");
        numeralMapTable.put(1, "I");
        numeralMapTable.put(100, "C");
        sut.setDecimalToNumeralMap(numeralMapTable);

        assertThat(sut.getDecimalIterator()).containsSequence(1000, 500, 100, 1);
    }
}
