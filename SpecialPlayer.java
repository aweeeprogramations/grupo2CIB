public class SpecialPlayer extends Player {

    // Guarda se ainda tem a vida extra nesta ronda
    private boolean secondChanceAvailable;

    public SpecialPlayer(String name) {
        super(name); 
        this.secondChanceAvailable = true;
    }

    @Override
    public boolean hasSecondChance() {
        return this.secondChanceAvailable;
    }

    @Override
    public void useSecondChance() {
        this.secondChanceAvailable = false;
    }

    @Override
    public void resetSecondChance() {
        this.secondChanceAvailable = true;
    }

    @Override
    public String getType() {
        return "Special";
    }
}