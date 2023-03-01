package be.intecbrussel;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BlackJackApp {
    private static final String[] spade = {"1s", "2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s", "10s", "11s", "12s", "13s", "14s"};
    private static final String[] club = {"1c", "2c", "3c", "4c", "5c", "6c", "7c", "8c", "9c", "10c", "11c", "12c", "13c", "14c"};
    private static final String[] diamond = {"1d", "2d", "3d", "4d", "5d", "6d", "7d", "8d", "9d", "1d", "11d", "12d", "13d", "14d"};
    private static final String[] heart = {"1h", "2h", "3h", "4h", "5h", "6h", "7h", "8h", "9h", "10h", "11h", "12h", "13h", "14h"};
    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("""
                Welcome to our Black Jack Game!
                You will start the game with 100 coins!
                To quit the game write 'quit'!
                Else write 'start'""");
        boolean isPlaying = true;

        int totalCoins = 100;
        int accumutatedBet = 0;
        int totalCardsToShow = 2;
        int dealerCardsValue = 0;
        int playerCardsValue = 0;
        int playerBet = 0;
        String playerInput = "";
        playerInput = scanner.nextLine();
        if (playerInput.equalsIgnoreCase("quit")) {
            System.out.println("You quit the game!");
            isPlaying = false;
        }


        while (isPlaying) {

            // generate dealer and player cards - generate one time per game
            String[] generatedTableCards = generateRandomCards();
            //System.out.println(Arrays.toString(generatedTableCards));
            String[] dealerCards = Arrays.copyOfRange(generatedTableCards, 0, 5);
            String[] playerCards = Arrays.copyOfRange(generatedTableCards, 5, 10);
            System.out.println(Arrays.toString(dealerCards));
            System.out.println(Arrays.toString(playerCards));
            //take the input from our user

            System.out.println("Total Coins: " + totalCoins);
            System.out.println("Please make your bet!");
            // check with functiuon hasNextInt
            playerBet = scanner.nextInt();
            while (playerBet <= totalCoins) {
                totalCoins -= playerBet;

                // print for the user the first card
                System.out.print("Dealer cards: ");
                int len = (dealerCards.length - 1) - (totalCardsToShow);
                int indexDealer = 0;

                for (; indexDealer < len; indexDealer++) {
                    System.out.print((indexDealer == len - 1) ? "X" : dealerCards[indexDealer] + " ");

                    int cardSize = dealerCards[indexDealer].length();
                    if (cardSize > 2) {
                        dealerCardsValue += Integer.parseInt((dealerCards[indexDealer].charAt(0)) + "" + (dealerCards[indexDealer].charAt(1)));
                    } else {
                        dealerCardsValue += Integer.parseInt(String.valueOf(dealerCards[indexDealer].charAt(0)));
                    }

                }

                System.out.println();
                System.out.print("Player cards: ");
                for (int j = 0; j < playerCards.length - 1 - totalCardsToShow; j++) {
                    System.out.print(playerCards[j] + " ");
                    int cardSize = playerCards[j].length();
                    if (cardSize > 2) {
                        playerCardsValue += Integer.parseInt((playerCards[j].charAt(0)) + "" + (playerCards[j].charAt(1)));
                    } else {
                        playerCardsValue += Integer.parseInt(String.valueOf(playerCards[j].charAt(0)));
                    }
                }
                System.out.println();
                System.out.println("*****************************");
                System.out.println("dealer value " + dealerCardsValue);
                System.out.println("player value " + playerCardsValue);
                // we have only maximum (hands)
                totalCardsToShow--;

                // if any of total value is higher than 21 -> busting
                if (dealerCardsValue == 21) {
                    break;
                } else if (playerCardsValue > 21 || dealerCardsValue > 21) {
                    break;
                }


                System.out.println("Do you want to HIT or STAND");
                scanner.nextLine();
                String input = scanner.nextLine().toLowerCase();

                if (input.equals("hit")) {
                    accumutatedBet += playerBet;
                    dealerCardsValue = 0;
                    playerCardsValue = 0;
                } else if (dealerCardsValue > playerCardsValue) {
                    System.out.println("Dealer won !");
                    break;
                } else {
                    int cardSize = dealerCards[indexDealer].length();
                    if (cardSize > 2) {
                        dealerCardsValue += Integer.parseInt((dealerCards[indexDealer].charAt(0)) + "" + (dealerCards[indexDealer].charAt(1)));
                    } else {
                        dealerCardsValue += Integer.parseInt(String.valueOf(dealerCards[indexDealer].charAt(0)));
                    }
                }
            }

            if (dealerCardsValue == 21) {
                System.out.println("Dealer won ! Blackjack !");
                break;
            } else if (playerCardsValue == 21) {
                System.out.println("Player won ! Blackjack !");
                totalCoins += accumutatedBet;
                System.out.println("Total coins " + totalCoins);
                break;
            } else if (playerCardsValue > 21) {
                System.out.println("Dealer won ! Player Bust !");
                break;
            } else if (dealerCardsValue > 21) {
                System.out.println("Player won ! Dealer Bust !");
                totalCoins += accumutatedBet;
                System.out.println("Total coins " + totalCoins);
                break;
            }


            System.out.print("Dealer cards: ");
            for (int i = 0; i < (dealerCards.length - 1); i++) {
                System.out.print( dealerCards[i] + " ");
            }
            System.out.println();
            System.out.print("Player cards: ");
            for (int i = 0; i < (playerCards.length - 1); i++) {
                System.out.print(playerCards[i] + " ");
            }
            System.out.println();
            System.out.println("*****************************");

            System.out.println("Total Coins: " + totalCoins);
            System.out.println("Please make your bet!");
            // check with function hasNextInt
            playerBet = scanner.nextInt();

            System.out.println("Do you want to play again ? Type Yes or No");
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equals("no")) {
                System.out.println("You quit the game!");
                isPlaying = false;
            } else {
                dealerCardsValue = 0;
                playerCardsValue = 0;
            }
        }



    }

    //generate an array with 10 cards
    // card's must be unique and from our 4 stacks of cards
    private static String[] generateRandomCards() {
        String[] randomStackCardsTable = new String[10];
        // add to our
        for (int i = 0; i < randomStackCardsTable.length; i++) {
            String randomString = String.valueOf(getCardStack());
            // add a card to our array only if the card it's not already array
            for (String s : randomStackCardsTable) {
                if (randomString.equals(s)) {
                    randomString = String.valueOf(getCardStack());
                }
            }
            randomStackCardsTable[i] = randomString;
        }
        return randomStackCardsTable;

    }


    private static int getCardNumber() {
        return random.nextInt(13);
    }

    // generate a card from our deck arrays of cards
    public static String getCardStack() {
        int cardStack = random.nextInt(4);
        int cardNumber;
        String cardValue;
        switch (cardStack) {
            case 0:
                cardNumber = getCardNumber();
                cardValue = spade[cardNumber];
                break;
            case 1:
                cardNumber = getCardNumber();
                cardValue = club[cardNumber];
                break;
            case 2:
                cardNumber = getCardNumber();
                cardValue = diamond[cardNumber];
                break;
            case 3:
                cardNumber = getCardNumber();
                cardValue = heart[cardNumber];
                break;
            default:
                cardValue = "ERROR!";
        }
        return cardValue;
    }
}
