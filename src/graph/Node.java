package graph;

import java.util.ArrayList;
import java.util.List;

public class Node {

    private Graph graph;
    private Item item;
    private List<Link> links;

    Node(Graph graph, Item item) {
        this.graph = graph;
        this.item = item;
        this.links = new ArrayList<>();
    }

    Node(Node parent, Item item) {
        this(parent.getGraph(), item);
        parent.linkTo(this);
    }

    public Graph getGraph() {
        return graph;
    }

    public Item getItem() {
        return item;
    }

    public List<Link> getLinks() {
        return links;
    }

    public String getName() {
        return item.getUuid();
    }

    public void linkTo(Node target) {
        links.add(new Link(this, target, target.getItem().getWeight()));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(this.getName() + " [" + this.getItem() + "]");

        for(Link link : links) {
            result.append("\n\t").append("-> ").append(link);
        }

        return result.toString();
    }
}
