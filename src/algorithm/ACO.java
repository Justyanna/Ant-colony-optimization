package algorithm;

import graph.Graph;
import graph.Node;

import java.util.ArrayList;
import java.util.List;


public class ACO {

    private final Graph data;
    private final Ant[] workers;
    private final int numCycles;
    private final int totalKnapsackCapacity;
    private final int alpha;
    private final int beta;
    private final double evaporationRate;

    private List<Node> solution;
    private int score;

    public ACO(Graph data, int numAnts, int numCycles, int totalKnapsackCapacity, int alpha, int beta,
               double evaporationRate) {
        this.data = data;
        this.workers = new Ant[numAnts];
        this.numCycles = numCycles;
        this.totalKnapsackCapacity = totalKnapsackCapacity;
        this.alpha = alpha;
        this.beta = beta;
        this.evaporationRate = evaporationRate;

        compute();
    }

    private void compute() {

        solution = new ArrayList<>();
        score = 0;

        for (int c = 0; c < numCycles; c++) {

            try {
                unleashAnts();
            } catch (NullPointerException e) {
                System.err.println("ACO error : knapsack capacity too low");
                return;
            }

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
            workers[i] = new Ant(totalKnapsackCapacity, data.getRandomNode(totalKnapsackCapacity), alpha, beta);
        }

    }

    private void evaporate() {

        for(String name : data.getNodeNames()) {
            data.getNode(name).getItem().evaporatePheromone(evaporationRate);
        }

    }

    private void updatePheromones() {

        for(Ant w : workers) {

            double strength = 1.0 / (1.0 + (double) (score - w.getScore()) / score);

            for(Node node : w.getPath()) {
                node.getItem().addPheromone(strength);
            }

        }

    }

    public int getNumAnts() {
        return workers.length;
    }

    public int getNumCycles() {
        return numCycles;
    }

    public List<Node> getSolution() {
        return new ArrayList<>(solution);
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {

        if (solution.size() < 1) {
            return "No solution found.";
        }

        StringBuilder result = new StringBuilder("Result: $" + getScore() + "\nSteps: ");
        for (Node step : solution) {
            result.append(" ").append(step.getName());
        }
        return result.toString();

    }
}
