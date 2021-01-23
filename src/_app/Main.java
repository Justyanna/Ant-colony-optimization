package _app;

import algorithm.ACO;
import algorithm.Ant;
import algorithm.Item;
import data.Generator;
import graph.Graph;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {

    private static boolean seeded;
    private static long seed;
    private static Random rng;

    public static boolean isSeeded() {
        return seeded;
    }

    public static long getSeed() {
        return seed;
    }

    public static Random getRng() {
        return rng;
    }

    public static void main(String[] args) {

        //        seeded = false;
        //        seed = new Random().nextLong();

        seeded = true;
        seed = 0;

        rng = new Random(seed);
        int itemsAmount = 100;
        Item[] items = Generator.getInstance().createItems(itemsAmount);

        Graph graph = new Graph(items);
        System.out.println();
        System.out.println(graph);
        System.out.println();

        ACO aco = new ACO(graph, 10, 20, graph.getTotalWeight() * 0.5, 1, 5, Ant.Optimization.AKA2, 0.5);
        System.out.println(aco);

        testAmountToCapacity();
    }

    public static void testAmountToCapacity() {

        int[][] resultArr = new int[11][6];

        for (int i = 0; i < resultArr.length - 1; i++) {
            for (int j = 0; j < resultArr[0].length - 1; j++) {
                int itemsAmount = 5 + i * 5;
                resultArr[i + 1][0] = itemsAmount;
                double capacityRate = 0.2 + j * 0.15;
                resultArr[0][j + 1] = (int) (round(capacityRate, 2) * 100.0);

                Item[] items = Generator.getInstance().createItems(itemsAmount);

                Graph graph = new Graph(items);
                ACO aco = new ACO(graph, 10, 50, (int) (graph.getTotalWeight() * capacityRate), 1, 5,
                                  Ant.Optimization.AKA2, 0.5);
                resultArr[i + 1][j + 1] = aco.getScore();
            }
        }

        saveToCsv(resultArr, "amountToCapacity.csv");
    }

    private static void saveToCsv(int[][] resultArr, String filename) {
        try (FileWriter writer = new FileWriter("results/" + filename)) {

            for (int i = 0; i < resultArr.length; i++) {
                for (int j = 0; j < resultArr[0].length; j++) {
                    if (i == 0) {
                        if (j == 0) {
                            writer.append("\"amount/capacity\"");
                        } else {
                            writer.append("\"").append(Integer.toString(resultArr[i][j])).append("%\"");
                        }
                    } else if (j == 0) {
                        writer.append("\"").append(Integer.toString(resultArr[i][j])).append("\"");
                    } else {
                        writer.append(String.valueOf(resultArr[i][j]));
                    }
                    writer.append(",");
                }
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved to results/" + filename);
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
