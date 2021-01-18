package graph;

public class Link {

    private int cost;
    private Node source;
    private Node target;

    public Link(Node source, Node target, int cost) {
        this.source = source;
        this.target = target;
        this.cost = cost;
    }

    public Node getSource() {
        return source;
    }

    public Node getTarget() {
        return target;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "<" + getCost() + "> " + getTarget().getName();
    }
}
