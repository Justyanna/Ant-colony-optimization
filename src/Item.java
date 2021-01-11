import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.UUID;

/**
 * @author: Anna Kuczyńska
 */
public class Item {

    private String uuid;
    private int weight;
    private int price;
    private boolean flag;
    private double probability;

    public Item(int weight, int price, boolean flag, double probability) {
        this.probability = probability;
        UUID randomUUID = UUID.randomUUID();
        long l = ByteBuffer.wrap(randomUUID.toString().getBytes()).getLong();
        uuid = Long.toString(l, Character.MAX_RADIX);
        this.weight = weight;
        this.price = price;
        this.flag = flag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
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
        return Objects.equals(uuid, item.uuid);
    }

    @Override
    public String toString() {
        return "Item{" + "uuid = " + uuid + ", weight = " + weight + ", price = " + price + ", probability = " +
                probability + '}';
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

    String getUuid() {
        return uuid;
    }
}
