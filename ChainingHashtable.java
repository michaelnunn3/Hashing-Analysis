package com.company;

public class ChainingHashtable extends AbstractHashtable {

    private static class Entry {
        final int value;
        Entry next = null;

        public Entry(int value) {
            this.value = value;
        }
    }

    private final Entry[] table;

    public ChainingHashtable(int size, boolean useModSizeHashCode) {
        super(size, useModSizeHashCode);
        table = new Entry[size];
    }

    @Override
    protected Result doAdd(int value) {
        int hashCode = hashCode(value);
        if (table[hashCode] == null) {
            table[hashCode] = new Entry(value);
            return Result.NEW;
        }
        else {
            Entry e = table[hashCode];
            for (;;) {
                if (value == e.value) {
                    return Result.EQUAL;
                }
                else if (e.next == null) {
                    e.next = new Entry(value);
                    return Result.COLLISION;
                }
                else {
                    e = e.next;
                }
            }
        }
    }

}
