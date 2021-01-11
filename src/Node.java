import java.util.List;

public class Node {

    private Item item;
    private List<Link> links;

    public Node(Item item, List<Link> links) {
        this.item = item;
        this.links = links;
    }

    public Node(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }

    public List<Link> getLinks() {
        return links;
    }

    void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return " \n" + "item = " + item;
    }
}
