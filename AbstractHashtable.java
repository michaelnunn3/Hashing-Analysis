package com.company;

public abstract class AbstractHashtable {

    protected enum Result {
        NEW(true, false),
        EQUAL(false, false),
        COLLISION(true, true);

        final boolean count;
        final boolean collision;

        Result(boolean count, boolean collision) {
            this.count = count;
            this.collision = collision;
        }
    }

    protected final int capacity;
    private final boolean useModSizeHashCode;
    private int count;
    private int collisions;

    public AbstractHashtable(int capacity, boolean useModSizeHashCode) {
        this.capacity = capacity;
        this.useModSizeHashCode = useModSizeHashCode;
    }

    private int modSizeHashCode(int value) {
        return value % capacity;
    }

    private int midSquareHashCode(int value) {
        long square = ((long)value) * ((long)value);
        return ((int)(square >> 16)) % capacity;
    }

    protected int hashCode(int value) {
        return useModSizeHashCode ? modSizeHashCode(value) : midSquareHashCode(value);
    }

    public int capacity() {
        return capacity;
    }

    public int count() {
        return count;
    }

    public double loadFactor() {
        return ((double)count) / capacity;
    }

    public int collisions() {
        return collisions;
    }

    public void add(int value) {
        Result result = doAdd(value);
        if (result.count) count++;
        if (result.collision) collisions++;
    }

    protected abstract Result doAdd(int value);
}
