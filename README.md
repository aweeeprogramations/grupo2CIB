# Projeto HighLow - Parte 2: O Torneio

## O que é este projeto?
Este projeto é a segunda parte do trabalho de POO. Pegámos no jogo HighLow da Parte 1 e transformámo-lo num torneio onde vários jogadores podem jogar à vez. O objetivo continua a ser adivinhar se a próxima carta é mais alta (H) ou mais baixa (L).

## Como organizámos o código
Dividimos o programa em várias classes para aplicar a matéria de herança e polimorfismo:

* **Card.java**: Representa uma carta (com número e naipe). Transforma os números 11 a 14 em Valete, Rainha, Rei e Ás para ser mais fácil de ler no ecrã.
* **Deck.java**: O baralho com as 52 cartas. Baralha o baralho no início de cada ronda e vai dando cartas com o método `dealCard()`.
* **Player.java**: É a classe principal dos jogadores. Guarda o nome, os pontos acumulados, as rondas feitas e uma lista (`ArrayList`) com os pontos que o jogador fez em cada ronda.
* **NormalPlayer.java**: Herda do `Player`. É o jogador normal: se errar a previsão ou calhar carta igual, a ronda dele acaba logo.
* **SpecialPlayer.java**: Também herda do `Player`, mas tem a regra especial: se errar, tem direito a uma segunda tentativa para a mesma carta (pode usar esta ajuda uma vez por ronda).
* **HighLow.java**: É onde o jogo acontece. Regista os jogadores no início, mete cada um a jogar à vez (em rotação), conta os pontos e no fim mostra a média, o histórico e quem ganhou (incluindo empates).
* **Main.java**: Só serve para arrancar o programa.

## Como correr o jogo
1. Abrir a pasta no VS Code.
2. Ir ao ficheiro `Main.java` e carregar em **Run**.

---
## Repositório GitHub
* [Repositório GitHub - HighLow Torneio](https://github.com/aweeeprogramations/grupo2CIB)

---
**Trabalho feito por (Grupo 2):**
* Diogo Almeida - a22506107
* Gabriel Marques - a22600719
* Afonso Cabral - a22506076