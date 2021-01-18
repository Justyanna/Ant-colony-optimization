package algorithm;

import graph.Graph;
import graph.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ACO {

    private Graph data;
    private Ant[] workers;
    private int numCycles;
    private List<Node> solution;
    private int score;
    private int totalKnapsackCapacity;
    private int itemsAmount;

    public ACO(int numAnts, int numCycles, int totalKnapsackCapacity, int itemsAmount) {

        this.workers = new Ant[numAnts];
        this.numCycles = numCycles;
        this.totalKnapsackCapacity = totalKnapsackCapacity;
        this.itemsAmount = itemsAmount;
        this.data = buildGraph();

        unleashAnts();

        compute();
    }

    private final void compute() {

        solution = new ArrayList<>();
        score = 0;

        for (int c = 0; c < numCycles; c++) {

            List<Node> cycleSolution = new ArrayList<>();
            int cycleRecord = 0;

            for (Ant w : workers) {
                //                TODO: Ant business
                int antScore = 0;
                if (antScore > cycleRecord) {
                    cycleSolution = w.getPath();
                    cycleRecord = antScore;
                }
            }

            if (cycleRecord > score) {
                solution = cycleSolution;
                score = cycleRecord;
            }

            evaporate();
            updatePheromones();
        }
    }

    private void unleashAnts() {
        //        TODO: Ants' initialization
    }

    private void evaporate() {
        //        TODO: Evaporation
    }

    private void updatePheromones() {
        //        TODO: Pheromones
    }

    private Item[] generateItems() {

        Random random = new Random();
        Item[] items = new Item[Math.min(itemsAmount, Data.itemsNames.length)];

        for (int i = 0; i < items.length; i++) {
            items[i] = new Item(Data.itemsNames[i], random.nextInt(10) + 1, random.nextInt(100) + 1, false,
                                random.nextDouble());
        }
        return items;
    }

    private Graph buildGraph() {
        Item[] items = generateItems();
        Graph graph = new Graph(items[0]);
        Map<String, Node> nodes = graph.getNodes();

        for (int i = 1; i < items.length; i++) {
            graph.createNode(graph.getRoot(), items[i]);
        }

        for (String key : nodes.keySet()) {
            for (String nextKey : nodes.keySet()) {

                Node source = graph.getNodes().get(key);
                Node target = graph.getNodes().get(nextKey);

                source.linkTo(target);
                target.linkTo(source);
            }
        }

        return graph;
    }

    public int getNumWorkers() { return workers.length; }

    public int getNumCycles() { return numCycles; }

    public List<Node> getSolution() { return new ArrayList<>(solution); }

    public int getScore() { return score; }

    public Graph getData() {
        return data;
    }

    Ant[] getWorkers() {
        return workers;
    }

    int getTotalKnapsackCapacity() {
        return totalKnapsackCapacity;
    }

    int getItemsAmount() {
        return itemsAmount;
    }
}
