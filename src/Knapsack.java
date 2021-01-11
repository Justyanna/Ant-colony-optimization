import java.util.ArrayList;
import java.util.List;

/**
 * @author: Anna Kuczyńska
 */

public class Knapsack {

    private int totalCapacity;
    private int currentCapacity;
    private List<Item> items;

    public Knapsack(int totalCapacity) {
        this.totalCapacity = totalCapacity;
        this.currentCapacity = totalCapacity;
        items = new ArrayList<>();
    }

    private boolean canBeLoaded(Item item) {
        return item.getWeight() <= this.currentCapacity;
    }

    public void addItem(Item item) {
        if (canBeLoaded(item)) {
            items.add(item);
            currentCapacity -= item.getWeight();
        }
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public List<Item> getItems() {
        return items;
    }
}
