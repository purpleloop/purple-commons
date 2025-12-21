package io.github.purpleloop.commons.lang;

import java.util.Arrays;
import java.util.List;

/** A multi-dimensionnal recursive array. */
public class RecursiveArray {

    /** Number of dimensions. */
    private int dimension;

    /** This level size. */
    private int size;

    /** This level storage. */
    private Object[] storage;

    /**
     * Creates a recursive array with the given sizes.
     * 
     * @param dimensionSizes sizes of each dimension
     */
    public RecursiveArray(List<Integer> dimensionSizes) {

        dimension = dimensionSizes.size();

        size = dimensionSizes.get(0);
        storage = new Object[size];

        if (dimension > 1) {

            List<Integer> subDimensionSizes = dimensionSizes.subList(1, dimension);

            // Creates sub arrays
            for (int i = 0; i < size; i++) {
                storage[i] = new RecursiveArray(subDimensionSizes);
            }

        } else {
            Arrays.fill(storage, null);
        }
    }

    /**
     * @return the current level size.
     */
    public int getSize() {
        return size;
    }

    /**
     * @param index index of the requested sub array
     * @return sub array for the requested index
     */
    public RecursiveArray getSubArray(int index) {

        checkBounds(index);

        if (dimension < 2) {
            throw new ArrayIndexOutOfBoundsException("The array does not have sub arrays");
        }

        return (RecursiveArray) storage[index];
    }

    /**
     * Check the validity of the index.
     * 
     * @param index value to check against current array bounds
     * @throws ArrayIndexOutOfBoundsException if index is invalid
     */
    private void checkBounds(int index) {
        if (index < 0 || index >= size) {

            StringBuilder sbError = new StringBuilder();
            sbError.append("Access error : ");
            sbError.append(index);
            sbError.append(" is out of bounds [0; ");
            sbError.append((size - 1));
            sbError.append("]");

            throw new ArrayIndexOutOfBoundsException(sbError.toString());
        }

    }

    /**
     * Return the stored value at the given coordinates.
     * 
     * @param <T> the stored object type
     * @param coords coordinates in the recursive array
     * @return the value
     */
    @SuppressWarnings("unchecked")
    public <T> T getObject(List<Integer> coords) {

        int coordsSize = coords.size();
        int coordValue;

        if (coordsSize == 1) {

            // Single dimension, so direct access to the requested value
            coordValue = coords.get(0);
            checkBounds(coordValue);
            return (T) storage[coordValue];

        } else if (coordsSize > 1) {

            // Get the requested sub-array for the current dimension
            coordValue = coords.get(0);
            checkBounds(coordValue);

            List<Integer> subCoords = coords.subList(1, coordsSize);

            return (T) ((RecursiveArray) storage[coordValue]).getObject(subCoords);

        } else {
            throw new ArrayIndexOutOfBoundsException("Invalid coordinates size " + coordsSize);
        }

    }

    /**
     * Stores a value at the given coordinates.
     * 
     * @param <T> the stored object type
     * @param coords coordinates in the recursive array
     * @param value the value to set
     */
    public <T> void setObject(T value, List<Integer> coords) {

        int coordsSize = coords.size();
        int coordValue = -1;

        if (coordsSize == 1) {

            // Single dimension, so direct access to the requested value
            coordValue = coords.get(0);
            checkBounds(coordValue);
            storage[coordValue] = value;

        } else if (coordsSize > 1) {

            // Get the requested sub-array for the current dimension
            coordValue = coords.get(0);
            checkBounds(coordValue);

            List<Integer> subCoords = coords.subList(1, coordsSize);

            ((RecursiveArray) storage[coordValue]).setObject(value, subCoords);

        } else {
            throw new ArrayIndexOutOfBoundsException("Invalid coordinates size " + coordsSize);
        }

    }

    /**
     * Return the stored double value at the given coordinates.
     * 
     * @param coords coordinates in the recursive array
     * @return a double value
     */
    public double getDouble(List<Integer> coords) {
        return ((Double) getObject(coords)).doubleValue();
    }

    /**
     * Stores a double value at the given coordinates.
     * 
     * @param value double value to store
     * @param coords coordinates in the recursive array
     */
    public void setDouble(double value, List<Integer> coords) {
        setObject(Double.valueOf(value), coords);
    }

    /**
     * Return the stored integer value at the given coordinates.
     * 
     * @param coords coordinates in the recursive array
     * @return an integer value
     */
    public int getInt(List<Integer> coords) {
        return ((Integer) getObject(coords)).intValue();
    }

    /**
     * Stores an integer value at the given coordinates.
     * 
     * @param value integer value to store
     * @param coords coordinates in the recursive array
     */
    public void setInt(int value, List<Integer> coords) {
        setObject(Integer.valueOf(value), coords);
    }

}
