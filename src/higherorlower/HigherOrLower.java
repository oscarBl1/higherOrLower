package higherorlower;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

public class HigherOrLower {

    static int random1, random2, random3;
    static int score = 0;
    static int highScoreNum;
    static String card1, card2;
    static String suit;
    static String choice;
    static String initials;
    static Scanner scan = new Scanner(System.in);
    static Random rand = new Random();
    static boolean lose;
    static char highScoreCh;

    public static void cardNumber1() {
        random1 = rand.nextInt(13) + 1;
        if (random1 == 11) {
            card1 = "Jack";
        } else if (random1 == 12) {
            card1 = "Queen";
        } else if (random1 == 13) {
            card1 = "King";
        } else if (random1 == 1) {
            card1 = "Ace";
        } else {
            card1 = Integer.toString(random1);
        }

    }

    public static void cardNumber2() {
        random2 = rand.nextInt(13) + 1;
        if (random2 == 11) {
            card2 = "Jack";
            random2 = 10;
        } else if (random2 == 12) {
            card2 = "Queen";
            random2 = 10;
        } else if (random2 == 13) {
            card2 = "King";
            random2 = 10;
        } else if (random2 == 1) {
            card2 = "Ace";
        } else {
            card2 = Integer.toString(random2);
        }

    }

    public static void suit() {
        random3 = rand.nextInt(4);
        if (random3 == 0) {
            suit = "Hearts";
        } else if (random3 == 1) {
            suit = "Diamonds";
        } else if (random3 == 2) {
            suit = "Clubs";
        } else if (random3 == 3) {
            suit = "Spades";
        }
    }

    public static void comparison() {
        if (random1 < random2 && choice.equalsIgnoreCase("higher")) {
            System.out.println("Correct!");
            score++;
            System.out.println("Score: " + score);
        } else if (random1 < random2 && choice.equalsIgnoreCase("lower")) {
            System.out.println("Incorrect: Game Over");
            playAgain();
        } else if (random1 > random2 && choice.equalsIgnoreCase("higher")) {
            System.out.println("Incorrect: Game Over");
            playAgain();
        } else if (random1 > random2 && choice.equalsIgnoreCase("lower")) {
            System.out.println("Correct!");
            score++;
            System.out.println("Score: " + score);
        } else if (random1 == random2) {
            System.out.println("Lucky! +2 points");
            score += 2;
            System.out.println("Score: " + score);
        }
    }

    public static void playAgain() {
        readFile();
        highestScore();
        while (true) {
            System.out.println("Would you like to play again? y/n");
            String play = scan.next();
            if (play.equalsIgnoreCase("y")) {
                System.out.println("Final score is " + score);
                score = 0;
                break;
            } else if (play.equalsIgnoreCase("n")) {
                lose = true;
                System.out.println("Final score is " + score);
                break;
            } else {
                System.out.println("Incorrect answer try again");
            }
        }

    }

    public static void readFile() {
        try {
            File highScoreFile = new File("highScore.txt");
            BufferedReader reader = new BufferedReader(new FileReader("highScore.txt"));
            int highScore = reader.read();
            highScoreCh = (char) highScore;
            String highScoreInit = reader.readLine();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void highestScore() {
        if (score > highScoreNum) { 
            System.out.println("New High Score of " + score);
            writeFile();
        }
    }

    public static void writeFile() {
        try {
            FileWriter myWriter = new FileWriter("highScore.txt");
            myWriter.write(score + " " + initials);
            myWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readFile();
        System.out.println("Current High Score is : " + highScoreCh);
        System.out.println("Enter your initials: ");
        initials = scan.next();

        lose = false;
        cardNumber1();
        suit();
        System.out.println("Your card is " + card1 + " of " + suit);
        while (!lose) {
            System.out.println("Is the next card higher or lower than " + card1);
            boolean highOrLow = false;
            while (!highOrLow) {
                choice = scan.next();
                if (choice.toLowerCase().equals("higher") || choice.toLowerCase().equals("lower")) {
                    highOrLow = true;
                } else {
                    System.out.println("Please try again");
                }
                suit();
                cardNumber2();
                System.out.println("Your other card is " + card2 + " of " + suit);
            }
            comparison();
            random1 = random2;
            card1 = card2;
        }
    }

}
