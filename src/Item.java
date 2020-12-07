import java.util.Objects;
import java.util.UUID;

public class Item {
    private  UUID uuid;
    private int weight;
    private int price;
    private boolean flag;

    public Item(int weight, int price, boolean flag) {
        uuid = uuid.randomUUID();
        this.weight = weight;
        this.price = price;
        this.flag = flag;
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
    public int hashCode() {
        return Objects.hash(uuid);
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
}
