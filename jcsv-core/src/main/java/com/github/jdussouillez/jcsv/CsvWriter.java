package com.github.jdussouillez.jcsv;

/**
 * CSV writer
 */
public interface CsvWriter {

    /**
     * Appends a value to the CSV
     *
     * @param value Value to append
     * @param escapeStr Escape the value (default is true)
     * @return This writer instance
     */
    public CsvWriter append(String value, boolean escapeStr);

    /**
     * Appends a value to the CSV
     *
     * @param value Value to append
     * @return This writer instance
     */
    public CsvWriter append(String value);

    /**
     * Appends a number value to the CSV
     *
     * @param value Number to append
     * @return This writer instance
     */
    public CsvWriter append(Number value);

    /**
     * Appends a null value to the CSV
     *
     * @return This writer instance
     */
    public CsvWriter append();

    /**
     * Appends a newline
     *
     * @return This writer instance
     */
    public CsvWriter newline();
}
