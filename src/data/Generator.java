package data;

import _app.Main;
import algorithm.Item;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Generator {

    private static Generator instance;

    private final Random rng;

    private Generator() {

        rng = new Random(Main.getRng().nextLong());

    }

    public static Generator getInstance() {

        if (instance == null) {
            instance = new Generator();
        }

        return instance;

    }

    public Item[] createItems(int amount) {

        ItemTemplate[] templates;

        try {
            Scanner source = new Scanner(new File("res/template.txt"));

            Set<ItemTemplate> tmp = new HashSet<>();
            int errors = 0;
            int duplicates = 0;

            while (source.hasNextLine()) {
                try {
                    if (!tmp.add(new ItemTemplate(source.nextLine(), rng))) {
                        duplicates++;
                    }
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    errors++;
                }
            }

            if (errors > 0) {
                System.err.println("Generator warning : rejected corrupted templates (" + errors + ")");
            }

            if (duplicates > 0) {
                System.err.println("Generator warning : rejected duplicated templates (" + duplicates + ")");
            }

            templates = tmp.toArray(new ItemTemplate[0]);

        } catch (IOException e) {
            System.err.println("Generator error : 'res/template.txt' not found");
            return null;
        }

        Item[] items = new Item[amount];

        for (int i = 0; i < amount; i++) {
            items[i] = templates[(rng.nextInt(templates.length))].generate();
        }

        return items;

    }

}
