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
        //        int itemsAmount = 100;
        //        Item[] items = Generator.getInstance().createItems(itemsAmount);
        //
        //        Graph graph = new Graph(items);
        //        System.out.println();
        //        System.out.println(graph);
        //        System.out.println();
        //
        //        ACO aco = new ACO(graph, 10, 20, graph.getTotalWeight() * 0.5, 1, 5, Ant.Optimization.AKA2, 0.5);
        //        System.out.println(aco);

        //        testAmountToCapacity();
        //        testAmountToAnts();
        //        testAmountToCycles();
        //        testCapacityToAnts();
        //        testCapacityToCycles();
        //        testCapacityToAttractiveness();
        //        testEvaporationToAttractiveness();
        //        testABRatioToAttractiveness();
        testABRatioToEvaporation();
    }

    public static void testABRatioToEvaporation() {

        int[][] resultArr = new int[6][12];
        int itemsAmount = 100;
        Item[] items = Generator.getInstance().createItems(itemsAmount);
        Graph graph = new Graph(items);

        int[] alpha = {5, 3, 1, 1, 1};
        int[] beta = {1, 1, 1, 3, 5};

        for (int i = 0; i < resultArr.length - 1; i++) {
            for (int j = 0; j < resultArr[0].length - 1; j++) {
                double evaporationRate = j * 0.1;
                resultArr[i + 1][0] = alpha[i] * 10 + beta[i];
                resultArr[0][j + 1] = (int) (round(evaporationRate, 2) * 100.0);

                ACO aco = new ACO(graph, 15, 10, graph.getTotalWeight() * 0.2, alpha[i], beta[i], Ant.Optimization.AKA2,
                                  evaporationRate);
                resultArr[i + 1][j + 1] = aco.getScore();
            }
            System.out.println((double) (i + 1) / (resultArr.length - 1));
        }

        saveToCsv(resultArr, "ABRatioToEvaporation.csv", "ab_ratio/evaporation", false);
    }

    public static void testABRatioToAttractiveness() {

        int[][] resultArr = new int[6][4];
        int itemsAmount = 100;
        Item[] items = Generator.getInstance().createItems(itemsAmount);
        Graph graph = new Graph(items);

        Ant.Optimization[] att = {Ant.Optimization.AKA1, Ant.Optimization.AKA2, Ant.Optimization.AKA3};
        int[] alpha = {5, 3, 1, 1, 1};
        int[] beta = {1, 1, 1, 3, 5};

        for (int i = 0; i < resultArr.length - 1; i++) {
            for (int j = 0; j < resultArr[0].length - 1; j++) {

                resultArr[i + 1][0] = alpha[i] * 10 + beta[i];
                resultArr[0][j + 1] = j + 1;

                ACO aco = new ACO(graph, 15, 10, graph.getTotalWeight() * 0.2, alpha[i], beta[i], att[j], 0.5);
                resultArr[i + 1][j + 1] = aco.getScore();
            }
            System.out.println((double) (i + 1) / (resultArr.length - 1));
        }

        saveToCsv(resultArr, "ABRatioToAttractiveness.csv", "ab_ratio/attractiveness", false);
    }

    public static void testEvaporationToAttractiveness() {

        int[][] resultArr = new int[12][4];
        int itemsAmount = 100;
        Item[] items = Generator.getInstance().createItems(itemsAmount);
        Graph graph = new Graph(items);

        Ant.Optimization[] att = {Ant.Optimization.AKA1, Ant.Optimization.AKA2, Ant.Optimization.AKA3};

        for (int i = 0; i < resultArr.length - 1; i++) {
            for (int j = 0; j < resultArr[0].length - 1; j++) {

                double evaporationRate = i * 0.1;
                resultArr[i + 1][0] = (int) (round(evaporationRate, 2) * 100.0);
                resultArr[0][j + 1] = j + 1;

                ACO aco = new ACO(graph, 15, 10, graph.getTotalWeight() * 0.2, 5, 1, att[j], evaporationRate);
                resultArr[i + 1][j + 1] = aco.getScore();
            }
            System.out.println((double) (i + 1) / (resultArr.length - 1));
        }

        saveToCsv(resultArr, "evaporationToAttractiveness.csv", "evaporation/attractiveness", false);
    }

    public static void testCapacityToAttractiveness() {

        int[][] resultArr = new int[12][4];
        int itemsAmount = 100;
        Item[] items = Generator.getInstance().createItems(itemsAmount);
        Graph graph = new Graph(items);

        Ant.Optimization[] att = {Ant.Optimization.AKA1, Ant.Optimization.AKA2, Ant.Optimization.AKA3};

        for (int i = 0; i < resultArr.length - 1; i++) {
            for (int j = 0; j < resultArr[0].length - 1; j++) {
                double capacityRate = 0.05 + i * 0.05;
                resultArr[i + 1][0] = (int) (round(capacityRate, 2) * 100.0);
                resultArr[0][j + 1] = j + 1;

                ACO aco = new ACO(graph, 15, 10, graph.getTotalWeight() * capacityRate, 1, 5, att[j], 0.5);
                resultArr[i + 1][j + 1] = aco.getScore();
            }
            System.out.println((double) (i + 1) / (resultArr.length - 1));
        }

        saveToCsv(resultArr, "capacityToAttractiveness.csv", "capacity/attractiveness", false);
    }

    public static void testCapacityToCycles() {

        int[][] resultArr = new int[12][6];
        int itemsAmount = 100;
        Item[] items = Generator.getInstance().createItems(itemsAmount);
        Graph graph = new Graph(items);

        for (int i = 0; i < resultArr.length - 1; i++) {
            int cyclesNum = 5;
            for (int j = 0; j < resultArr[0].length - 1; j++) {
                double capacityRate = 0.05 + i * 0.05;
                resultArr[i + 1][0] = (int) (round(capacityRate, 2) * 100.0);
                resultArr[0][j + 1] = cyclesNum;

                ACO aco = new ACO(graph, 15, cyclesNum, graph.getTotalWeight() * capacityRate, 1, 5,
                                  Ant.Optimization.AKA2, 0.5);
                resultArr[i + 1][j + 1] = aco.getScore();
                cyclesNum *= 2;
            }
            System.out.println((double) (i + 1) / (resultArr.length - 1));
        }

        saveToCsv(resultArr, "capacityToCycles.csv", "capacity/cycles", false);
    }

    public static void testCapacityToAnts() {

        int[][] resultArr = new int[9][6];
        int itemsAmount = 100;
        Item[] items = Generator.getInstance().createItems(itemsAmount);
        Graph graph = new Graph(items);

        for (int i = 0; i < resultArr.length - 1; i++) {
            for (int j = 0; j < resultArr[0].length - 1; j++) {
                int antsNum = 5 + j * 5;
                double capacityRate = 0.2 + i * 0.05;
                resultArr[i + 1][0] = (int) (round(capacityRate, 2) * 100.0);
                resultArr[0][j + 1] = antsNum;

                ACO aco =
                        new ACO(graph, antsNum, 10, graph.getTotalWeight() * capacityRate, 1, 5, Ant.Optimization.AKA2,
                                0.5);
                resultArr[i + 1][j + 1] = aco.getScore();
            }
            System.out.println((double) (i + 1) / (resultArr.length - 1));
        }

        saveToCsv(resultArr, "capacityToAnts.csv", "capacity/ants", false);
    }

    public static void testAmountToCycles() {

        int[][] resultArr = new int[11][6];

        for (int i = 0; i < resultArr.length - 1; i++) {
            int cyclesNum = 5;
            int itemsAmount = 25 + i * 25;
            Item[] items = Generator.getInstance().createItems(itemsAmount);
            Graph graph = new Graph(items);

            for (int j = 0; j < resultArr[0].length - 1; j++) {

                resultArr[i + 1][0] = itemsAmount;
                resultArr[0][j + 1] = cyclesNum;

                ACO aco = new ACO(graph, 15, cyclesNum, graph.getTotalWeight() * 0.5, 1, 5, Ant.Optimization.AKA2, 0.5);
                resultArr[i + 1][j + 1] = aco.getScore();
                cyclesNum *= 2;
            }
            System.out.println((double) (i + 1) / (resultArr.length - 1));
        }

        saveToCsv(resultArr, "amountToCycles.csv", "amount/cycles", false);
    }

    public static void testAmountToAnts() {

        int[][] resultArr = new int[11][9];

        for (int i = 0; i < resultArr.length - 1; i++) {
            int itemsAmount = 25 + i * 25;
            Item[] items = Generator.getInstance().createItems(itemsAmount);
            Graph graph = new Graph(items);

            for (int j = 0; j < resultArr[0].length - 1; j++) {
                int antsNum = 5 + j * 5;

                resultArr[i + 1][0] = itemsAmount;
                resultArr[0][j + 1] = antsNum;

                ACO aco = new ACO(graph, antsNum, 5, graph.getTotalWeight() * 0.5, 1, 5, Ant.Optimization.AKA2, 0.5);
                resultArr[i + 1][j + 1] = aco.getScore();
            }
            System.out.println((double) (i + 1) / (resultArr.length - 1));
        }

        saveToCsv(resultArr, "amountToAnts.csv", "amount/ants", false);
    }

    public static void testAmountToCapacity() {

        int[][] resultArr = new int[11][6];

        for (int i = 0; i < resultArr.length - 1; i++) {
            int itemsAmount = 25 + i * 25;
            Item[] items = Generator.getInstance().createItems(itemsAmount);
            Graph graph = new Graph(items);

            for (int j = 0; j < resultArr[0].length - 1; j++) {

                double capacityRate = 0.2 + j * 0.15;

                resultArr[i + 1][0] = itemsAmount;
                resultArr[0][j + 1] = (int) (round(capacityRate, 2) * 100.0);

                ACO aco =
                        new ACO(graph, 15, 50, graph.getTotalWeight() * capacityRate, 1, 5, Ant.Optimization.AKA2, 0.5);
                resultArr[i + 1][j + 1] = aco.getScore();
            }
            System.out.println((double) (i + 1) / (resultArr.length - 1));
        }

        saveToCsv(resultArr, "amountToCapacity.csv", "amount/capacity", true);
    }

    private static void saveToCsv(int[][] resultArr, String filename, String tableDescription, boolean percentage) {
        try (FileWriter writer = new FileWriter("results/" + filename)) {

            for (int i = 0; i < resultArr.length; i++) {
                for (int j = 0; j < resultArr[0].length; j++) {
                    if (i == 0) {
                        if (j == 0) {
                            writer.append("\"").append(tableDescription).append("\"");
                        } else {
                            writer.append("\"")
                                    .append(Integer.toString(resultArr[i][j]))
                                    .append(percentage ? "%\"" : "\"");
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
