package data;

import algorithm.Item;

import java.util.Objects;
import java.util.Random;

public class ItemTemplate {

    private final Random rng;
    private final String name;
    private final int min_value;
    private final int max_value;
    private final int min_weight;
    private final int max_weight;
    private int count;

    public ItemTemplate(String template, Random rng) {

        String[] data = template.split(" ");

        name = data[0];
        min_value = Integer.parseInt(data[1]);
        max_value = Integer.parseInt(data[2]);
        min_weight = Integer.parseInt(data[3]);
        max_weight = Integer.parseInt(data[4]);

        this.rng = rng;

    }

    public Item generate() {

        String name = this.name + " " + (++count);
        int value = rng.nextInt(max_value - min_value + 1) + min_value;
        int weight = rng.nextInt(max_weight - min_weight + 1) + min_weight;

        return new Item(name, weight, value);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemTemplate template = (ItemTemplate) o;
        return name.equals(template.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
