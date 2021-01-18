package graph;

import algorithm.Item;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Node {

    private Graph graph;
    private Item item;
    private Set<Link> links;

    Node(Graph graph, Item item) {
        this.graph = graph;
        this.item = item;
        this.links = new HashSet<>();
    }

    Node(Node parent, Item item) {
        this(parent.getGraph(), item);
        parent.linkTo(this);
    }

    public boolean linkTo(Node target) {
        if (this == target) {
            return false;
        } else {
            return links.add(new Link(this, target, target.getItem().getWeight()));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Node node = (Node) o;
        return Objects.equals(item.getName(), node.item.getName());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.getItem().toString());

        for (Link link : links) {
            result.append("\n\t").append("-> ").append(link);
        }

        return result.toString();
    }

    public Graph getGraph() {
        return graph;
    }

    public Item getItem() {
        return item;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public String getName() {
        return item.getName();
    }
}
