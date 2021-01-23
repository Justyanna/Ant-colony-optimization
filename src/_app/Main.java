package _app;

import algorithm.ACO;
import algorithm.Data;
import algorithm.Item;
import graph.Graph;
import graph.Node;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class Main {

    private static Random rng;

    public static Random getRng() {
        return rng;
    }

    public static void main(String[] args) {

        boolean random_seed = true;
        long seed = 0;

        for (Iterator<String> it = Arrays.stream(args).iterator(); it.hasNext(); ) {
            switch (it.next()) {
                case "-s":
                    try {
                        seed = Long.parseLong(it.next());
                        random_seed = false;
                    } catch (NullPointerException | NumberFormatException e) {
                        System.err.println("Call error : missing or invalid value for parameter -s");
                    }
            }
        }

        rng = random_seed ? new Random() : new Random(seed);

        System.out.println();
        int itemsAmount = 5;
        Item[] items = generateItems(itemsAmount);
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

    private static Item[] generateItems(int itemsAmount) {
        Item[] items = new Item[Math.min(itemsAmount, Data.itemsNames.length)];

        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(Data.itemsNames[i], rng.nextInt(10) + 1, rng.nextInt(100) + 1);
        }
        return items;
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
