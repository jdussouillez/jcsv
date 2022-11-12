package com.github.jdussouillez.jcsv;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests of {@link com.github.jdussouillez.jcsv.CsvStringWriter}
 */
public class CsvStringWriterTest {

    /**
     * Writer with configuration following the RFC
     */
    private CsvStringWriter writerRfc;

    /**
     * Writer with configuration following the RFC but without escape for strings by default
     */
    private CsvStringWriter writerRfcNoEsc;

    /**
     * Writer with configuration following the RFC but using semicolons
     */
    private CsvStringWriter writerSemi;

    /**
     * Writer with configuration following the RFC but using semicolons, LF newlines and single quotes.
     */
    private CsvStringWriter writerSemiLfSingle;

    /**
     * All writers to test
     */
    private List<CsvStringWriter> writers;

    @BeforeEach
    public void setUp() {
        writerRfc = new CsvStringWriter(new CsvConfiguration.Factory().rfc4180());
        writerRfcNoEsc = new CsvStringWriter(new CsvConfiguration.Factory().rfc4180NoEscape());
        writerSemi = new CsvStringWriter(new CsvConfiguration.Factory().usingSemicolon());
        writerSemiLfSingle = new CsvStringWriter(
            new CsvConfiguration.Builder()
                .columnSeparator(';')
                .lineSeparator("\n")
                .useSingleQuotes()
                .build()
        );
        writers = List.of(writerRfc, writerRfcNoEsc, writerSemi, writerSemiLfSingle);
    }

    @Test
    public void testAppendNullAndEmpty() {
        writers.forEach(w -> w.append().append((String) null).append(""));
        assertEquals(",,", writerRfc.content());
        assertEquals(",,", writerRfcNoEsc.content());
        assertEquals(";;", writerSemi.content());
        assertEquals(";;", writerSemiLfSingle.content());
    }

    @Test
    public void testAppendString() {
        writers.forEach(w -> w
            .append("\"Software is like sex: it's better when it's free.\"")
            .newline()
            .append("Linus Torvalds")
        );
        assertEquals(
            """
            ""\"Software is like sex: it's better when it's free.\"""\r
            "Linus Torvalds"
            """.trim(),
            writerRfc.content()
        );
        assertEquals(
            """
            "Software is like sex: it's better when it's free."\r
            Linus Torvalds
            """.trim(),
            writerRfcNoEsc.content()
        );
        assertEquals(
            """
            ""\"Software is like sex: it's better when it's free.\"""\r
            "Linus Torvalds"
            """.trim(),
            writerRfc.content()
        );
        assertEquals(
            """
            '"Software is like sex: it''s better when it''s free."'
            'Linus Torvalds'
            """.trim(),
            writerSemiLfSingle.content()
        );
    }

    @Test
    public void testAppendStringUnescaped() {
        writers.forEach(w -> w.append("foo", false));
        assertEquals("foo", writerRfc.content());
        assertEquals("foo", writerRfcNoEsc.content());
        assertEquals("foo", writerSemi.content());
        assertEquals("foo", writerSemiLfSingle.content());
    }

    @Test
    public void testAppendNumber() {
        writers.forEach(w -> w.append(0).append(3.14d).append((Integer) null).append(123_456_789L));
        assertEquals("0,3.14,,123456789", writerRfc.content());
        assertEquals("0,3.14,,123456789", writerRfcNoEsc.content());
        assertEquals("0;3.14;;123456789", writerSemi.content());
        assertEquals("0;3.14;;123456789", writerSemiLfSingle.content());
    }

    @Test
    public void testNewline() {
        writers.forEach(w -> w.append("foo").newline().append("bar").newline());
        assertEquals(
            """
            "foo"\r
            "bar"
            """.trim(),
            writerRfc.content()
        );
        assertEquals(
            """
            foo\r
            bar
            """.trim(),
            writerRfcNoEsc.content()
        );
        assertEquals(
            """
            "foo"\r
            "bar"
            """.trim(),
            writerSemi.content()
        );
        assertEquals(
            """
            'foo'
            'bar'
            """.trim(),
            writerSemiLfSingle.content()
        );
    }
}
