package io.github.purpleloop.commons.math.algebra;

/** A 2D integer matrix. */
public class Int2DMatrix {

    /** Contents. */
    private int[][] matrix;

    /** Width. */
    private int width;

    /** Height. */
    private int height;

    /**
     * Creates a matrix.
     * 
     * @param width Width
     * @param height Height
     * 
     */
    public Int2DMatrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new int[width][height];
    }

    /**
     * Creates a matrix as a copy of a source matrix.
     * 
     * @param source the source matrix
     * 
     */
    public Int2DMatrix(Int2DMatrix source) {

        this(source.width, source.height);

        for (int y = 0; y < height; y++) {
            System.arraycopy(source.matrix[y], 0, matrix[y], 0, height);
        }

    }

    /**
     * @return the matrix width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the matrix height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the matrix contents at the given coordinates.
     * 
     * @param x abscissa
     * @param y ordinate
     * @return the matrix value
     */
    public int get(int x, int y) {
        return matrix[x][y];
    }

    /**
     * Set the matrix contents at the given coordinates.
     * 
     * @param x abscissa
     * @param y ordinate
     * @param value the matrix value
     */
    public void set(int x, int y, int value) {
        matrix[x][y] = value;
    }

    /**
     * @return true if matrix is square, false otherwise
     */
    public boolean isSquare() {
        return height == width;
    }

    /**
     * @param rowNum the row where to sum
     * @return sum of all the matrix values for the given row
     */
    public int arraySumW(int rowNum) {
        int sum = 0;
        for (int colNum = 0; colNum < width; colNum++) {
            sum += this.matrix[colNum][rowNum];
        }

        return sum;
    }

    /**
     * @param colNum the column where to sum
     * @return sum of all the matrix values for the given column
     */
    public int arraySumH(int colNum) {
        int sum = 0;
        for (int rowNum = 0; rowNum < height; rowNum++) {
            sum += this.matrix[colNum][rowNum];
        }

        return sum;
    }

}
