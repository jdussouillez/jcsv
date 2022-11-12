package com.github.jdussouillez.jcsv;

/**
 * CSV configuration
 */
public final class CsvConfiguration {

    /**
     * Column separator
     */
    private final char columnSeparator;

    /**
     * Line separator
     */
    private final String lineSeparator;

    /**
     * Escape strings by default
     */
    private final boolean escapeStrings;

    /**
     * Use double quotes
     */
    private final boolean doubleQuotes;

    /**
     * Constructor
     *
     * @param columnSeparator Column separator
     * @param lineSeparator Line separator
     * @param escape Escape strings by default
     * @param doubleQuotes Use double quotes
     */
    private CsvConfiguration(final char columnSeparator, final String lineSeparator, final boolean escape,
        final boolean doubleQuotes) {
        this.columnSeparator = columnSeparator;
        this.lineSeparator = lineSeparator;
        this.escapeStrings = escape;
        this.doubleQuotes = doubleQuotes;
    }

    /**
     * Returns the column separator
     *
     * @return The column separator
     */
    public char columnSeparator() {
        return columnSeparator;
    }

    /**
     * Returns the line separator
     *
     * @return The line separator
     */
    public String lineSeparator() {
        return lineSeparator;
    }

    /**
     * Returns true if this configuration escapes strings by default
     *
     * @return True if strings must be escaped by default
     */
    public boolean escapeStrings() {
        return escapeStrings;
    }

    /**
     * Returns true if strings must be escaped using double quotes
     *
     * @return True if strings must be escaped using double quotes, false if single quotes
     */
    public boolean doubleQuotes() {
        return doubleQuotes;
    }

    /**
     * Configuration builder
     *
     * <p>
     * Default values are based on RFC 4180
     * </p>
     */
    public static final class Builder {

        /**
         * Column separator
         */
        private char columnSeparator = ',';

        /**
         * Line separator
         */
        private String lineSeparator = "\r\n";

        /**
         * Escape strings by default
         */
        private boolean escapeStrings = true;

        /**
         * Use double quotes
         */
        private boolean doubleQuotes = true;

        /**
         * Sets the column separator
         *
         * @param sep Column separator
         * @return The builder instance
         */
        public Builder columnSeparator(final char sep) {
            columnSeparator = sep;
            return this;
        }

        /**
         * Sets the line separator
         *
         * @param sep Line separator
         * @return The builder instance
         */
        public Builder lineSeparator(final String sep) {
            lineSeparator = sep;
            return this;
        }

        /**
         * Escapes strings by default
         *
         * @param escape Escape strings by default
         * @return The builder instance
         */
        public Builder escapeStrings(final boolean escape) {
            escapeStrings = escape;
            return this;
        }

        /**
         * Escapes the strings using double quotes
         *
         * @return The builder instance
         */
        public Builder useDoubleQuotes() {
            this.doubleQuotes = true;
            return this;
        }

        /**
         * Escapes the strings using double quotes
         *
         * @return The builder instance
         */
        public Builder useSingleQuotes() {
            this.doubleQuotes = false;
            return this;
        }

        /**
         * Build the CSV configuration
         *
         * @return The CSV configuration
         */
        public CsvConfiguration build() {
            return new CsvConfiguration(columnSeparator, lineSeparator, escapeStrings, doubleQuotes);
        }
    }

    /**
     * Configuration factory
     */
    public static final class Factory {

        /**
         * Creates a new configuration based on RFC 4180.
         *
         * @return Configuration
         */
        public CsvConfiguration rfc4180() {
            return new CsvConfiguration.Builder().build();
        }

        /**
         * Creates a new configuration based on RFC 4180 but not escaping strings by default.
         *
         * @return Configuration
         */
        public CsvConfiguration rfc4180NoEscape() {
            return new CsvConfiguration.Builder()
                .escapeStrings(false)
                .build();
        }

        /**
         * Creates a new configuration based on RFC 4180 but using semicolon.
         *
         * @return Configuration
         */
        public CsvConfiguration usingSemicolon() {
            return new CsvConfiguration.Builder()
                .columnSeparator(';')
                .build();
        }

        /**
         * Creates a new configuration based on RFC 4180 but using semicolon and LF line separator.
         *
         * @return Configuration
         */
        public CsvConfiguration usingSemicolonAndLf() {
            return new CsvConfiguration.Builder()
                .columnSeparator(';')
                .lineSeparator("\n")
                .build();
        }
    }
}
