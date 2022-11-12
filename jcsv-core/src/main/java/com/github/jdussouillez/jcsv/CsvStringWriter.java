package com.github.jdussouillez.jcsv;

/**
 * CSV string writer
 */
public class CsvStringWriter implements CsvWriter {

    /**
     * CSV configuration
     */
    protected final CsvConfiguration configuration;

    /**
     * CSV content
     */
    protected final StringBuilder content;

    /**
     * Constructor
     *
     * @param configuration Configuration
     */
    public CsvStringWriter(final CsvConfiguration configuration) {
        this.configuration = configuration;
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
        content.append(configuration.columnSeparator());
        return this;
    }

    @Override
    public CsvStringWriter append(final String value) {
        return append(value, configuration.escapeStrings());
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
        content.append(configuration.lineSeparator());
        return this;
    }

    /**
     * Escapes the string
     *
     * @param s String to escape
     * @return The escaped string
     */
    private String escape(final String s) {
        final var quote = configuration.doubleQuotes() ? '"' : '\'';
        return String.format(
            "%s%s%s",
            quote,
            s.replace(String.valueOf(quote), String.valueOf(quote) + quote),
            quote
        );
    }

    /**
     * Tests if the CSV content ends with column separator
     *
     * @return true si le contenu CSV se termine par un séparateur de colonne, false sinon
     */
    private boolean endsWithColSep() {
        return content.length() > 0 && content.charAt(content.length() - 1) == configuration.columnSeparator();
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
