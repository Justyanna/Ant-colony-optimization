package _app;

import algorithm.ACO;
import algorithm.Item;
import data.Generator;
import graph.Graph;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

public class Main {

    private static boolean seeded;
    private static long seed;
    private static Random rng;

    public static boolean isSeeded() {
        return seeded;
    }

    public static long getSeed() {
        return seed;
    }

    public static Random getRng() {
        return rng;
    }

    public static void main(String[] args) {

        seeded = false;
        seed = new Random().nextLong();

        for (Iterator<String> it = Arrays.stream(args).iterator(); it.hasNext(); ) {
            switch (it.next()) {
                case "-s":
                    try {
                        seed = Long.parseLong(it.next());
                        seeded = true;
                    } catch (NullPointerException | NumberFormatException e) {
                        System.err.println("Call error : missing or invalid value for parameter -s");
                    }
            }
        }

        rng = new Random(seed);
        int itemsAmount = 5;
        Item[] items = Generator.getInstance().createItems(itemsAmount);

        Graph graph = new Graph(items);
        System.out.println();
        System.out.println(graph);
        System.out.println();

        ACO aco = new ACO(graph, 10, 100, 150, 1, 5, 0.5);
        System.out.println(aco);

    }
}
