package com.github.jdussouillez.jcsv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/**
 * Tests of {@link com.github.jdussouillez.jcsv.CsvConfiguration}
 */
public class CsvConfigurationTest {

    /*
     * Builder
     */
    @Test
    public void testBuilderRfc() {
        var conf = new CsvConfiguration.Builder()
            .columnSeparator(',')
            .lineSeparator("\r\n")
            .escapeStrings(true)
            .useDoubleQuotes()
            .build();
        assertEquals(',', conf.columnSeparator());
        assertEquals("\r\n", conf.lineSeparator());
        assertTrue(conf.escapeStrings());
        assertTrue(conf.doubleQuotes());
    }

    @Test
    public void testCustomBuilder() {
        var conf = new CsvConfiguration.Builder()
            .columnSeparator(';')
            .lineSeparator("\n")
            .escapeStrings(false)
            .useSingleQuotes()
            .build();
        assertEquals(';', conf.columnSeparator());
        assertEquals("\n", conf.lineSeparator());
        assertFalse(conf.escapeStrings());
        assertFalse(conf.doubleQuotes());
    }

    /*
     * Factory
     */
    @Test
    public void testFactoryRfc() {
        var conf = new CsvConfiguration.Factory().rfc4180();
        assertEquals(',', conf.columnSeparator());
        assertEquals("\r\n", conf.lineSeparator());
        assertTrue(conf.escapeStrings());
        assertTrue(conf.doubleQuotes());
    }

    @Test
    public void testFactoryRfcNoEscape() {
        var conf = new CsvConfiguration.Factory().rfc4180NoEscape();
        assertEquals(',', conf.columnSeparator());
        assertEquals("\r\n", conf.lineSeparator());
        assertFalse(conf.escapeStrings());
        assertTrue(conf.doubleQuotes());
    }

    @Test
    public void testFactoryUsingSemicolon() {
        var conf = new CsvConfiguration.Factory().usingSemicolon();
        assertEquals(';', conf.columnSeparator());
        assertEquals("\r\n", conf.lineSeparator());
        assertTrue(conf.escapeStrings());
        assertTrue(conf.doubleQuotes());
    }

    @Test
    public void testFactoryUsingSemicolonAndLf() {
        var conf = new CsvConfiguration.Factory().usingSemicolonAndLf();
        assertEquals(';', conf.columnSeparator());
        assertEquals("\n", conf.lineSeparator());
        assertTrue(conf.escapeStrings());
        assertTrue(conf.doubleQuotes());
    }
}
