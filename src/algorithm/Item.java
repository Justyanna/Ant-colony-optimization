package algorithm;

import java.util.Objects;

public class Item {

    private String name;
    private int weight;
    private int price;
    private boolean flag;
    private double probability;

    public Item(String name, int weight, int price, boolean flag, double probability) {
        this.probability = probability;
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.flag = flag;
    }

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
        return " weight = " + weight + ", price = " + price + ", probability = " + probability;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }

    public boolean isFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }
}
