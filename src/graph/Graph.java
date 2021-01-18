package graph;

import algorithm.Item;

import java.util.HashMap;
import java.util.Map;

public class Graph {

    private Map<String, Node> nodes;
    private Node root;

    public Graph(Item item) {
        nodes = new HashMap<>();
        root = createNode(item);
    }

    public Node getRoot() {
        return root;
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public Map<String, Node> getNodes() {
        return nodes;
    }

    public Node createNode(Item item) {
        if (nodes.containsKey(item.getName())) {
            return null;
        }

        Node node = new Node(this, item);
        nodes.put(node.getName(), node);
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
        StringBuilder result = new StringBuilder("Graph:");

        for (String key : nodes.keySet()) {
            result.append("\n\t").append(nodes.get(key));
        }

        return result.toString();
    }
}
