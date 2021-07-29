package io.github.purpleloop.commons.lang;

import io.github.purpleloop.commons.util.HexTools;

/** 2D Array of bytes and utilities. */
public class Byte2DMatrix {

    /** The data storage array. */
    protected byte[][] store;

    /** Width. */
    private int width;

    /** Height. */
    private int height;

    /**
     * Creates an array of the given size.
     * 
     * @param width width
     * @param height height
     */
    public Byte2DMatrix(int width, int height) {

        this.width = width;
        this.height = height;
        this.store = new byte[width][height];
    }

    /**
     * Creates an array of the given size and the provided data.
     * 
     * @param width width
     * @param height height
     * @param source values to copy
     */
    public Byte2DMatrix(int width, int height, byte[][] source) {
        this(width, height);
        fill(source);
    }

    /**
     * Fills the 2D byte array with the provided values.
     * 
     * @param source source datz
     */
    private void fill(byte[][] source) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                store[x][y] = source[x][y];
            }
        }
    }

    /**
     * Gets the contents of the array at location coordinates (x,y).
     * 
     * @param x abscissa
     * @param y ordinate
     * @return stored value
     */
    public byte get(int x, int y) {
        return store[x][y];
    }

    /**
     * Stores a value at a given location coordinates (x,y).
     * 
     * @param x abscissa
     * @param y ordinate
     * @param value value to store
     * 
     * */
    public void put(int x, int y, byte value) {
        store[x][y] = value;
    }

    /**
     * Do a logical 'or' on the array contents at location coordinates (x,y) with the given value.
     * 
     * @param x abscissa
     * @param y ordinate
     * @param value value to use
     */
    public void or(int x, int y, byte value) {
        store[x][y] |= value;

    }

    /**
     * Do a logical 'and' on the array contents at location coordinates (x,y) with the given value.
     * 
     * @param x abscissa
     * @param y ordinate
     * @param value value to use
     */
    public void and(int x, int y, int value) {
        store[x][y] &= value;
    }

    /**
     * Builds a string containing hexadecimal values of the array.
     * 
     * @return hexadecimal dump
     */
    public String hexDump() {
        StringBuffer sb = new StringBuffer();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (x > 0) {
                    sb.append(" ");
                }
                sb.append(HexTools.toHex(store[x][y], 2));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
