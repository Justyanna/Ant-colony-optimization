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
    private int alpha;
    private int beta;
    private double evaporationRate;

    private List<Node> solution;
    private int score;

    public ACO(Graph data, int numAnts, int numCycles, int totalKnapsackCapacity, int alpha, int beta, double evaporationRate) {
        this.data = data;
        this.workers = new Ant[numAnts];
        this.numCycles = numCycles;
        this.totalKnapsackCapacity = totalKnapsackCapacity;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;

        compute();
    }

    private final void compute() {

        solution = new ArrayList<>();
        score = 0;

        for (int c = 0; c < numCycles; c++) {

            unleashAnts();

            List<Node> cycleSolution = new ArrayList<>();
            int cycleRecord = 0;

            for (Ant w : workers) {
                w.work();
                if (w.getScore() > cycleRecord) {
                    cycleSolution = w.getPath();
                    cycleRecord = w.getScore();
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

        for(int i = 0; i < getNumAnts(); i++) {
            workers[i] = new Ant(totalKnapsackCapacity, data.getRandomNode(), alpha, beta);
        }

    }

    private void evaporate() {

        for(String name : data.getNodeNames()) {
            data.getNode(name).getItem().evaporatePheromone(evaporationRate);
        }

    }

    private void updatePheromones() {

        for(Ant w : workers) {

            double strength = 1 / (1 + (score - w.getScore()) / score);

            for(Node node : w.getPath()) {
                node.getItem().addPheromone(strength);
            }

        }

    }

    public int getNumAnts() { return workers.length; }

    public int getNumCycles() { return numCycles; }

    public List<Node> getSolution() { return new ArrayList<>(solution); }

    public int getScore() { return score; }
}
