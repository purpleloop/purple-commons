package io.github.purpleloop.commons.math.algebra;

/** A 2D integer matrix. */
public class Int2DMatrix {

    /**
     * Contents - Note : Storage is array order inverted to match natural order
     * of indices.
     */
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
            for (int x = 0; x < width; x++) {
                this.matrix[x][y] = source.matrix[x][y];
            }
        }

    }

    /**
     * Creates a matrix as a copy of a source matrix.
     * 
     * @param intMatrix the source matrix (Java / usual order, row first)
     * 
     */
    public Int2DMatrix(int[][] intMatrix) {

        this(intMatrix[0].length, intMatrix.length);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.matrix[x][y] = intMatrix[y][x];
            }
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
    public int arraySumRow(int rowNum) {
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
    public int arraySumColumn(int colNum) {
        int sum = 0;
        for (int rowNum = 0; rowNum < height; rowNum++) {
            sum += this.matrix[colNum][rowNum];
        }

        return sum;
    }

    /**
     * @return true if the matrix is diagonal (not zero only on the
     *         left-to-right diagonal), false otherwise
     */
    public boolean isDiagonal() {
        if (!isSquare()) {
            return false;
        }

        boolean notZeroExpected;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                notZeroExpected = (x == y);

                if (notZeroExpected ^ (this.matrix[x][y] != 0)) {
                    return false;
                }
            }
        }

        return true;
    }

}
