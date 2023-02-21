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
                Else write start""");
        boolean isPlaying = true;

        int totalCoins = 100;
        int playerBet = 0;
        String playerInput = "";

        while (isPlaying) {
            playerInput = scanner.nextLine();
            if (playerInput.equalsIgnoreCase("quit")) {
                isPlaying = false;
                System.out.println("You quit the game!");
            } else {
                System.out.println("Total Coins: " + totalCoins);
                System.out.println("Please make your bet!");
                // check with functiuon hasNextInt
                playerBet = scanner.nextInt();
                while (playerBet <= totalCoins) {
                    totalCoins -= playerBet;
                    String[] generatedArray = generateRandomCards();
                    System.out.println(Arrays.toString(generatedArray));
                }


                /*System.out.println("""
                       Invalid input!
                        Try again!""");
                   }*/
            }

        }
    }


    private static String[] generateRandomCards() {
        String[] arrayRandom = new String[5];

        for (int i = 0; i < arrayRandom.length; i++) {
            String randomString = String.valueOf(getCardStack());
            for (String s : arrayRandom ) {
                if (randomString.equals(s)){
                    randomString = String.valueOf(getCardStack());
                }
            }
            arrayRandom[i] = randomString;
        }
        return arrayRandom;

    }


    private static int getCardNumber() {
        return random.nextInt(13);
    }

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
