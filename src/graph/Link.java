package graph;

import graph.Node;

public class Link {

    private int cost;
    private Node first, last;

    public Link(Node first, Node last, int cost) {
        this.first = first;
        this.last = last;
        this.cost = cost;
    }

    public Node getFirst() {
        return first;
    }

    public Node getLast() {
        return last;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "\n from = " + first.getItem().getUuid() + " to =" + last.getItem().getUuid() + " cost = "+cost;
    }
}
