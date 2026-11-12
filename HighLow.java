import java.util.ArrayList;
import java.util.Scanner;

public class HighLow {

    private ArrayList<Player> players = new ArrayList<>();
    private Scanner input = new Scanner(System.in);
    private Deck deck = new Deck();

    private int totalRoundsCompleted = 0;
    private int totalCorrectGuesses = 0;
    private int totalPointsAwarded = 0;

    public void startGame() {
        registerPlayers();

        int currentPlayerIndex = 0;
        boolean continueTournament = true;

        while (continueTournament) {
            Player currentPlayer = players.get(currentPlayerIndex);

            System.out.println("\nVez do jogador: " + currentPlayer.getName() + " (" + currentPlayer.getType() + ")");

            playRound(currentPlayer);
            totalRoundsCompleted++;

            continueTournament = askToContinue();

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }

        showFinalReport();
    }

    public void registerPlayers() {
        System.out.print("Quantos jogadores vao jogar: ");
        int count = Integer.parseInt(input.nextLine());

        for (int i = 1; i <= count; i++) {
            System.out.print("Nome do jogador " + i + ": ");
            String name = input.nextLine();

            String type = "";
            while (!type.equals("N") && !type.equals("S")) {
                System.out.print("Perfil - Normal (N) ou Especial (S): ");
                type = input.nextLine().toUpperCase();
            }

            if (type.equals("N")) {
                players.add(new NormalPlayer(name));
            } else {
                players.add(new SpecialPlayer(name));
            }
        }
    }

    public void playRound(Player player) {
        deck.shuffle();
        player.resetSecondChance();

        Card cardplay = deck.dealCard();
        int roundPoints = 0;
        boolean roundActive = true;

        while (roundActive && deck.cardsLeft() > 0) {
            System.out.println("A carta que calhou foi: " + cardplay);
            System.out.println("Cartas restantes no baralho: " + deck.cardsLeft());

            String choice = "";
            while (!choice.equals("H") && !choice.equals("L")) {
                System.out.print("Acha que a proxima carta e mais alta (H) ou baixa (L): ");
                choice = input.nextLine().toUpperCase();
            }

            Card nextcard = deck.dealCard();
            if (nextcard == null) {
                System.out.println("O baralho de cartas acabou");
                break;
            }

            System.out.println("A carta era: " + nextcard);

            boolean correct = false;
            if (choice.equals("H") && nextcard.getValue() > cardplay.getValue()) {
                correct = true;
            } else if (choice.equals("L") && nextcard.getValue() < cardplay.getValue()) {
                correct = true;
            }

            if (correct) {
                System.out.println("Acertou");
                roundPoints++;
                totalCorrectGuesses++;
                cardplay = nextcard;
            } else {
                // Se errou e for jogador especial
                if (player.hasSecondChance()) {
                    System.out.println("Errou. Jogador especial tem segunda oportunidade:");
                    player.useSecondChance();

                    String secondChoice = "";
                    while (!secondChoice.equals("H") && !secondChoice.equals("L")) {
                        System.out.print("Segunda oportunidade (H/L): ");
                        secondChoice = input.nextLine().toUpperCase();
                    }

                    boolean correctSecond = false;
                    if (secondChoice.equals("H") && nextcard.getValue() > cardplay.getValue()) {
                        correctSecond = true;
                    } else if (secondChoice.equals("L") && nextcard.getValue() < cardplay.getValue()) {
                        correctSecond = true;
                    }

                    if (correctSecond) {
                        System.out.println("Acertou na segunda oportunidade");
                        roundPoints++;
                        totalCorrectGuesses++;
                        cardplay = nextcard;
                    } else {
                        System.out.println("Errou na segunda oportunidade");
                        roundActive = false;
                    }
                } else {
                    System.out.println("Errou ou e igual");
                    roundActive = false;
                }
            }
        }

        player.addRound(roundPoints);
        totalPointsAwarded += roundPoints;
        System.out.println("Acabou com: " + roundPoints + " pontos");
    }

    public boolean askToContinue() {
        System.out.print("Deseja continuar o torneio (S/N): ");
        String answer = input.nextLine().toUpperCase();

        while (!answer.equals("S") && !answer.equals("N")) {
            System.out.print("Input errado. Deseja continuar o torneio (S/N): ");
            answer = input.nextLine().toUpperCase();
        }

        return answer.equals("S");
    }

    public void showFinalReport() {
        System.out.println("\nResultados individuais:");
        for (Player p : players) {
            System.out.println("Jogador: " + p.getName() + " (" + p.getType() + ")");
            System.out.println("Pontos acumulados: " + p.getTotalPoints());
            System.out.println("Rondas realizadas: " + p.getTotalRounds());
            System.out.printf("Media de pontos: %.2f\n", p.getAverageScore());
            System.out.println("Melhor ronda: " + p.getBestScore());
            System.out.println("Historico: " + p.getPointsHistory());
            System.out.println();
        }

        System.out.println("Estatisticas globais:");
        System.out.println("Jogadores inscritos: " + players.size());
        System.out.println("Rondas concluidas: " + totalRoundsCompleted);
        System.out.println("Previsoes corretas: " + totalCorrectGuesses);
        System.out.println("Pontos atribuidos: " + totalPointsAwarded);

        // Vencedor
        int maxScore = -1;
        for (Player p : players) {
            if (p.getTotalPoints() > maxScore) {
                maxScore = p.getTotalPoints();
            }
        }

        System.out.println("\nVencedor(es) com " + maxScore + " pontos:");
        for (Player p : players) {
            if (p.getTotalPoints() == maxScore) {
                System.out.println(p.getName() + " (" + p.getType() + ")");
            }
        }
    }
}