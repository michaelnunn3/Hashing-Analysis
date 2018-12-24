package com.company;

public class AddressingHashtable extends AbstractHashtable {

    private final int[] table;

    public AddressingHashtable(int capacity, boolean useModSizeHashCode) {
        super(capacity, useModSizeHashCode);
        table = new int[capacity];
        for (int i = 0; i < capacity; i++) table[i] = -1;
    }

    @Override
    protected Result doAdd(int value) {
        int hashCode = hashCode(value);
        if (table[hashCode] == -1) {
            table[hashCode] = value;
            return Result.NEW;
        }
        else {
            for (;;) {
                if (table[hashCode] == value) {
                    return Result.EQUAL;
                }
                hashCode = (hashCode + 1) % capacity;
                if (table[hashCode] == -1) {
                    table[hashCode] = value;
                    return Result.COLLISION;
                }
            }
        }

    }
}
