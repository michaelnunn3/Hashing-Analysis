package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Random;

public class HashingAnalysis {

    public static void main(String[] args) throws Exception {
        int[] capacities = {10, 25, 50};
        boolean[] bools = {false, true};
        for (boolean chaining: bools) {
            for (int capacity : capacities) {
                for (boolean hashFunc : bools) {
                    run(chaining, capacity, hashFunc);
                }
            }
        }
    }

    private static void run(boolean chaining, int capacity, boolean modSize) throws IOException {
        String name = chaining ? "chaining" : "addressing";
        String suffix = modSize ? "modSize" : "midSquare";
        Writer out = new FileWriter(name + "-" + suffix + "-" + capacity + ".csv");
        try {
            out.write("Load Factor,Number of Collisions\n");
            AbstractHashtable table;
            if (chaining) table = new ChainingHashtable(capacity, modSize);
            else table = new AddressingHashtable(capacity, modSize);
            test(table, out);
        }
        finally {
            out.close();
        }
    }

    private static void test(AbstractHashtable table, Writer out) throws IOException {
        Random rnd = new Random();
        int capacity = table.capacity();
        for (int i = 0; i < capacity; i++) {
            int value = rnd.nextInt(3 * capacity);
            table.add(value);
            out.write(table.loadFactor() + "," + table.collisions() + "\n");
        }
    }
}
