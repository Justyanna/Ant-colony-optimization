package graph;

import java.util.Objects;

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
    public int hashCode() {
        return Objects.hash(source, target);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Link link = (Link) o;
        return source.equals(link.source) && target.equals(link.target);
    }

    @Override
    public String toString() {
        return "<" + getCost() + "> " + getTarget().getName();
    }
}
