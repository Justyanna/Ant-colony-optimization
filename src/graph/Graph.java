package graph;

import _app.Main;
import algorithm.Item;

import java.util.HashMap;
import java.util.Map;

public class Graph {

    private final Map<String, Node> nodes;
    private final Node root;

    public Graph(Item item) {
        nodes = new HashMap<>();
        root = createNode(item);
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

    public Node getRandomNode() {

        int id = Main.getRng().nextInt(nodes.size());

        for(String name : nodes.keySet()) {
            if(id-- == 0) {
                return nodes.get(name);
            }
        }

        return null;
    }

    public String [] getNodeNames() {
        return nodes.keySet().toArray(new String[0]);
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
