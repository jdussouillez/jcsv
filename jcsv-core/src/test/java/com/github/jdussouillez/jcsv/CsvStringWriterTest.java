package com.github.jdussouillez.jcsv;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests of {@link com.github.jdussouillez.jcsv.CsvStringWriter}
 */
public class CsvStringWriterTest {

    /**
     * Writer
     */
    private CsvStringWriter writer;

    @BeforeEach
    public void setUp() {
        writer = new CsvStringWriter();
    }

    @Test
    public void testAppendNull() {
        writer.append().append((String) null);
        assertEquals(",", writer.content());
    }

    @Test
    public void testAppendString() {
        writer.append("foo").append("foo bar");
        assertEquals("\"foo\",\"foo bar\"", writer.content());
    }

    @Test
    public void testAppendStringUnescaped() {
        writer.append("foo", false);
        assertEquals("foo", writer.content());
    }

    @Test
    public void testAppendNumber() {
        writer.append(0).append(3.14d).append((Integer) null).append(123_456_789L);
        assertEquals("0,3.14,,123456789", writer.content());
    }

    @Test
    public void testNewline() {
        writer.append("foo").newline().append("bar").newline().newline().append("baz");
        assertEquals(
            """
            "foo"
            "bar"

            "baz"
            """.trim(),
            writer.content()
        );
    }
}
