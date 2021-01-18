package algorithm;

import graph.Graph;
import graph.Node;

import java.util.ArrayList;
import java.util.List;

public class ACO {

    private Graph data;
    private Ant[] workers;
    private int numCycles;
    private int totalKnapsackCapacity;
    private int itemsAmount;

    private List<Node> solution;
    private int score;


    public ACO(Graph data, int numAnts, int numCycles, int totalKnapsackCapacity, int itemsAmount) {
        this.data = data;
        this.workers = new Ant[numAnts];
        this.numCycles = numCycles;
        this.totalKnapsackCapacity = totalKnapsackCapacity;
        this.itemsAmount = itemsAmount;

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

    public int getNumWorkers() { return workers.length; }

    public int getNumCycles() { return numCycles; }

    public List<Node> getSolution() { return new ArrayList<>(solution); }

    public int getScore() { return score; }
}
