package algorithm;

import java.util.Objects;

public class Item {

    private String name;
    private int weight;
    private int price;
    private double pheromone;

    public Item(String name, int weight, int price) {

        this.name = name;
        this.weight = weight;
        this.price = price;
        this.pheromone = 0;

    }

    public int getWeight() { return weight; }
    public int getPrice() { return price; }
    public String getName() { return name; }
    public double getAttractiveness() { return (double)price / weight; }
    public double getPheromone() { return pheromone; }

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
        return name + " $" + price + " [" + weight + "lbs]";
    }

}
