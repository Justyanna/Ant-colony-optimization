package graph;

import _app.Main;
import algorithm.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    private final Map<String, Node> nodes;
    private final Node root;

    private int total_weight;
    private int total_value;

    private int weight_limit;
    private List<Node> valid_nodes;

    public Graph(Item item) {
        nodes = new HashMap<>();
        total_weight = 0;
        total_value = 0;
        root = createNode(item);
        weight_limit = 0;
        valid_nodes = null;
    }

    public Graph(Item[] items) {

        this(items[0]);

        for (Item item : items) {
            createNode(item);
        }

        for (String key : nodes.keySet()) {
            for (String nextKey : nodes.keySet()) {

                Node source = nodes.get(key);
                Node target = nodes.get(nextKey);

                source.linkTo(target);
                target.linkTo(source);
            }
        }

    }

    public Node getRoot() {
        return root;
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public Node getRandomNode(int weight_limit) {

        if (weight_limit != this.weight_limit) {

            this.weight_limit = Math.max(weight_limit, 0);

            if (weight_limit == 0) {
                valid_nodes = new ArrayList<>(nodes.values());
            } else {
                valid_nodes = new ArrayList<>();

                for (String name : nodes.keySet()) {
                    Node n = nodes.get(name);
                    if (n.getItem().getWeight() <= weight_limit) {
                        valid_nodes.add(n);
                    }
                }

                if (valid_nodes.size() == 0) {
                    valid_nodes = null;
                }
            }

        }

        return valid_nodes.get(Main.getRng().nextInt(valid_nodes.size()));

    }

    public String[] getNodeNames() {
        return nodes.keySet().toArray(new String[0]);
    }

    public int getTotalValue() {
        return total_value;
    }

    public int getTotalWeight() {
        return total_weight;
    }

    public Node createNode(Item item) {
        if (nodes.containsKey(item.getName())) {
            return null;
        }

        Node node = new Node(this, item);
        nodes.put(node.getName(), node);
        total_weight += item.getWeight();
        total_value += item.getValue();
        return node;
    }

    public Node createNode(Node parent, Item item) {
        if (parent.getGraph() != this || nodes.containsKey(item.getName())) {
            return null;
        }

        Node node = new Node(parent, item);
        nodes.put(node.getName(), node);
        parent.linkTo(node);
        return node;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Graph total: $" + total_value + ", " + total_weight + "g");

        for (String key : nodes.keySet()) {
            result.append("\n\t").append(nodes.get(key));
        }

        return result.toString();
    }
}
