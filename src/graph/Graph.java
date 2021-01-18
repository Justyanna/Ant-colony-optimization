package graph;


import java.util.HashMap;
import java.util.Map;

public class Graph {

    private Map<String, Node> nodes;
    private Node root;

    public Graph(Item item) {
        nodes = new HashMap<>();
        root = new Node(this, item);
        registerNode(root);
    }

    public Node getRoot() {
        return root;
    }

    public Node getNode(String name) {
        return nodes.get(name);
    }

    public boolean registerNode(Node node) {
        if(node.getGraph() == this) {
            nodes.put(root.getName(), root);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Graph:");

        for(String key : nodes.keySet()) {
            result.append("\n\t").append(nodes.get(key));
        }

        return result.toString();
    }
}
