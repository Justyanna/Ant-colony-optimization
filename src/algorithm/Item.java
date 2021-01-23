package algorithm;

import java.util.Objects;

public class Item {

    private final String name;
    private final int value;
    private final int weight;
    private double pheromone;

    public Item(String name, int value, int weight) {

        this.name = name;
        this.value = value;
        this.weight = weight;
        this.pheromone = 0;

    }

    public int getValue() {
        return value;
    }

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    public double getPheromone() {
        return pheromone;
    }

    public void addPheromone(double amount) { pheromone += amount; }
    public void evaporatePheromone(double rate) { pheromone *= rate; }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name);
    }

    @Override
    public String toString() {
        return name + " $" + value + " [" + weight + "g]";
    }

}
