package _app;

import algorithm.ACO;
import algorithm.Item;
import data.Generator;
import graph.Graph;
import graph.Node;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
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


        System.out.println();
        int itemsAmount = 5;
        Item[] items = Generator.getInstance().createItems(itemsAmount);
        Graph graph = buildGraph(items);
        System.out.println(graph);
        System.out.println();
        ACO aco = new ACO(graph, 10, 100, 15, 1, 5, 0.5);
        System.out.println("Result: " + aco.getScore());
        System.out.print("Steps:");
        for (Node step : aco.getSolution()) {
            System.out.print(" " + step.getName());
        }
        System.out.println();
    }

    private static Graph buildGraph(Item[] items) {
        Graph graph = new Graph(items[0]);
        Map<String, Node> nodes = graph.getNodes();

        for (Item item : items) {
            graph.createNode(item);
        }

        for (String key : nodes.keySet()) {
            for (String nextKey : nodes.keySet()) {

                Node source = graph.getNodes().get(key);
                Node target = graph.getNodes().get(nextKey);

                source.linkTo(target);
                target.linkTo(source);
            }
        }

        return graph;
    }
}
