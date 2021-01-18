package graph;

import java.util.List;

public class Graph {

    private List<Node> nodes;
    private Node source;

    public Graph(List<Node> nodes, Node source) {
        this.nodes = nodes;
        this.source = source;
    }

    private List<Node> getNodes() {
        return nodes;
    }

    @Override
    public String toString() {
        return "graph.Graph{" + "nodes=" + nodes + "\nsource=" + source + "}";
    }
}
