import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Item[] items = generateItems();
        List<Node> nodes = foldItems(items);
        Graph graph = new Graph(nodes, nodes.get(0));
        System.out.println(graph);
    }

    private static Item[] generateItems() {
        Item[] items = new Item[20];
        Random random = new Random();

        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(random.nextInt(10) + 1, random.nextInt(100) + 1, false, random.nextDouble());
        }
        return items;
    }

    private static List<Node> foldItems(Item[] items) {
        // Z listy wszystkich itemów tworzy wierzchołki grafu połączone ze wszystkimi innymi wierzhołkami poza sobą
        List<Node> nodes = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < items.length; i++) {
            nodes.add(new Node(items[i]));
        }

        List<Link> links;
        for (Node node : nodes) {
            links = new ArrayList<>();
            for (Node node1 : nodes) {
                if (node.getItem() != node1.getItem()) {
                    links.add(new Link(node, node1, random.nextInt(10) + 1));
                }
            }
            System.out.println(links);
            node.setLinks(links);
        }

        return nodes;
    }
}
