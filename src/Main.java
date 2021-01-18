import graph.Graph;
import graph.Item;
import graph.Link;
import graph.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Item[] items = generateItems();
        Graph graph = buildGraph(items);
        System.out.println(graph);
    }

    private static Item[] generateItems() {
        Item[] items = new Item[20];
        Random random = new Random();

        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(
                    random.nextInt(10) + 1,
                    random.nextInt(100) + 1,
                    false, random.nextDouble());
        }
        return items;
    }

    private static Graph buildGraph(Item [] items) {
        // Z listy wszystkich itemów tworzy wierzchołki grafu połączone ze wszystkimi innymi wierzchołkami poza sobą
        Graph graph = new Graph(items[0]);
        Random rand = new Random();

        for(int i = 1; i < items.length; i++)
        {
            new Node(graph.getRoot(), rand.nextInt(10) + 1, items[i]);
        }

        return graph;
    }
}
