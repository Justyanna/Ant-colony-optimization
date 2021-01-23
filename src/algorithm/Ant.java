package algorithm;

import _app.Main;
import graph.Link;
import graph.Node;

import java.util.ArrayList;
import java.util.List;

public class Ant {

    private Node location;
    private final List<Node> path;
    private List<Node> neighbourhood;
    private int capacity;
    private int score;
    private final int alpha;
    private final int beta;

    public Ant(int capacity, Node startingNode, int alpha, int beta) {

        this.capacity = capacity;
        this.score = 0;

        this.path = new ArrayList<>();
        this.takeItem(startingNode);

        this.alpha = alpha;
        this.beta = beta;
    }

    public Node getLocation() { return location; }

    public List<Node> getPath() { return new ArrayList<>(path); }

    public int getCapacity() { return capacity; }

    public int getScore() { return score; }

    public void work() {

        while (!neighbourhood.isEmpty()) {
            Node next = chooseNext();
            if (next == null) {
                break;
            }
            takeItem(chooseNext());
        }
    }

    private void takeItem(Node source) {

        location = source;
        path.add(source);

        Item item = source.getItem();
        capacity -= item.getWeight();
        score += item.getPrice();

        updateNeighbourhood();
    }

    private void updateNeighbourhood() {

        neighbourhood = new ArrayList<>();

        for (Link link : location.getLinks()) {
            Node node = link.getTarget();
            if (link.getCost() <= capacity && !path.contains(node)) {
                neighbourhood.add(node);
            }
        }
    }

    private Node chooseNext() {

        int n = neighbourhood.size();
        double[] scores = new double[n];
        double sum = 0;

        for (int i = 0; i < n; i++) {
            scores[i] = rateItem(neighbourhood.get(i).getItem());
            sum += scores[i];
        }

        for (int i = 0; i < n; i++) {
            scores[i] /= sum;
        }

        //        -- cumsum
        for (int i = 1; i < n; i++) {
            scores[i] += scores[i - 1];
        }

        //        -- roll
        double roll = Main.getRng().nextDouble();

        //        -- substitute distribution
        for (int i = 0; i < n; i++) {
            if (roll < scores[i]) {
                return neighbourhood.get(i);
            }
        }

        return null;
    }

    private double rateItem(Item item) {
        return Math.pow(item.getAttractiveness(), alpha) * Math.pow(item.getPheromone(), beta);
    }
}
