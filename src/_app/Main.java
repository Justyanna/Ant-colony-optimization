package _app;

import algorithm.ACO;
import algorithm.Ant;
import algorithm.Item;
import data.Generator;
import graph.Graph;

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

//        seeded = false;
//        seed = new Random().nextLong();

        seeded = true;
        seed = 0;

        rng = new Random(seed);
        int itemsAmount = 5;
        Item[] items = Generator.getInstance().createItems(itemsAmount);

        Graph graph = new Graph(items);
        System.out.println();
        System.out.println(graph);
        System.out.println();

        ACO aco = new ACO(graph, 10, 100, graph.getTotalWeight() / 2,
                1, 5, Ant.Optimization.AKA2, 0.5);
        System.out.println(aco);

    }
}
