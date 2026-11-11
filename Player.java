import java.util.ArrayList;

public class Player {

    protected String name;
    protected int totalpoints;
    protected int totalrounds;
    protected ArrayList<Integer> pointshistory;

    public Player(String name) {
        this.name = name;
        this.totalpoints = 0;
        this.totalrounds = 0;
        this.pointshistory = new ArrayList<>();
    }

    // Regista os pontos feitos numa ronda e guarda no histórico
    public void addRound(int points) {
        this.totalpoints += points;
        this.totalrounds++;
        this.pointshistory.add(points);
    }

    // Devolve a melhor pontuação conseguida numa única ronda
    public int getBestScore() {
        if (this.pointshistory.isEmpty()) {
            return 0;
        }
        int best = this.pointshistory.get(0);
        for (int p : this.pointshistory) {
            if (p > best) {
                best = p;
            }
        }
        return best;
    }

    // Calcula a média de pontos por ronda
    public double getAverageScore() {
        if (this.totalrounds == 0) {
            return 0.0;
        }
        return (double) this.totalpoints / this.totalrounds;
    }

    // Métodos para o Polimorfismo (por padrão no Player normal estão desligados)
    public boolean hasSecondChance() {
        return false;
    }

    public void useSecondChance() {
    }

    public void resetSecondChance() {
    }

    public String getType() {
        return "Normal";
    }

    public String getName() {
        return this.name;
    }

    public int getTotalPoints() {
        return this.totalpoints;
    }

    public int getTotalRounds() {
        return this.totalrounds;
    }

    public ArrayList<Integer> getPointsHistory() {
        return this.pointshistory;
    }
}