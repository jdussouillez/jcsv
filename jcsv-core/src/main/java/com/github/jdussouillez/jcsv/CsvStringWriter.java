package com.github.jdussouillez.jcsv;

/**
 * CSV string writer
 */
public class CsvStringWriter implements CsvWriter {

    /**
     * Column separator
     */
    private static final char COL_SEP = ',';

    /**
     * Line separator
     */
    private static final String LINE_SEP = System.lineSeparator();

    /**
     * String escape character
     */
    private static final char STRING_ESCAPE_CHAR = '"';

    /**
     * CSV content
     */
    protected final StringBuilder content;

    /**
     * Constructor
     */
    public CsvStringWriter() {
        content = new StringBuilder();
    }

    /**
     * Returns the CSV content
     *
     * @return CSV content
     */
    public String content() {
        removeTrailingColSep();
        return content.toString().trim();
    }

    @Override
    public CsvStringWriter append(final String value, final boolean escapeStr) {
        if (value != null && !value.isEmpty()) {
            content.append(escapeStr ? escape(value) : value);
        }
        content.append(COL_SEP);
        return this;
    }

    @Override
    public CsvStringWriter append(final String value) {
        return append(value, true);
    }

    @Override
    public CsvStringWriter append(final Number value) {
        return append(value != null ? value.toString() : null, false);
    }

    @Override
    public CsvWriter append() {
        return append((String) null);
    }

    @Override
    public CsvStringWriter newline() {
        removeTrailingColSep();
        content.append(LINE_SEP);
        return this;
    }

    /**
     * Escapes the string
     *
     * @param s String to escape
     * @return The escaped string
     */
    private String escape(final String s) {
        return String.format(
            "%s%s%s",
            STRING_ESCAPE_CHAR,
            s.replace(String.valueOf(STRING_ESCAPE_CHAR), String.valueOf(STRING_ESCAPE_CHAR) + STRING_ESCAPE_CHAR),
            STRING_ESCAPE_CHAR
        );
    }

    /**
     * Tests if the CSV content ends with column separator
     *
     * @return true si le contenu CSV se termine par un séparateur de colonne, false sinon
     */
    private boolean endsWithColSep() {
        return content.length() > 0 && content.charAt(content.length() - 1) == COL_SEP;
    }

    /**
     * Removes the trailing column separator for the current lien
     *
     * @return True if it was removed, false if no trailing separator
     */
    private boolean removeTrailingColSep() {
        var endsWithSep = endsWithColSep();
        if (endsWithSep) {
            // Using "setLength" is faster than "deleteCharAt".
            // See https://stackoverflow.com/questions/6461402/java-charat-and-deletecharat-performance
            content.setLength(content.length() - 1);
        }
        return endsWithSep;
    }
}
