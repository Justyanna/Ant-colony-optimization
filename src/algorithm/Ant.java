package algorithm;

import graph.Node;

import java.util.List;

public class Ant {

    private Node currentNode;
    private List<Node> path;
    private List<Node> nodesToVisit;

    public Ant(Node currentNode, List<Node> path, List<Node> nodesToVisit) {
        this.currentNode = currentNode;
        this.path = path;
        this.nodesToVisit = nodesToVisit;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public List<Node> getPath() {
        return path;
    }

    public List<Node> getNodesToVisit() {
        return nodesToVisit;
    }
}
