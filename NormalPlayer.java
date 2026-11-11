public class NormalPlayer extends Player {

    public NormalPlayer(String name) {
        super(name); // Passa o nome para o construtor do Pai (Player)
    }

    @Override
    public String getType() {
        return "Normal";
    }
}