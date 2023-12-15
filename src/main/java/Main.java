import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static AtomicInteger lengthThree = new AtomicInteger(0);
    public static AtomicInteger lengthFour = new AtomicInteger(0);
    public static AtomicInteger lengthFive = new AtomicInteger(0);

    public static void main (String[] args ) throws InterruptedException {

        Random random = new Random();
        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));

        new Thread(() -> {
            for (String word: texts) {
                if (isPalindrome(word) == true) {
                    counter(word.length());

                }
            }
        }).start();

        new Thread(() -> {
            for (String word: texts) {
                if (isOneLetter(word) == true) {
                    counter(word.length());
                }
            }

        }).start();

        new Thread(() -> {
            for (String word: texts) {
                if (isIncrementalLettersWord(word)) {
                    counter(word.length());
                }
            }
        }).start();


        System.out.println("Красивых слов с длиной 3: " + lengthThree + " шт");
        System.out.println("Красивых слов с длиной 4: " + lengthFour + " шт");
        System.out.println("Красивых слов с длиной 5: " + lengthFive + " шт");
        }
    }
    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }

    static boolean isPalindrome(String word) {
        int length = word.length();
        for (int i = 0; i < (length / 2); i++) {
            if (word.charAt(i) != word.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }

    static boolean isOneLetter (String word) {
        char[] charArray = word.toCharArray();
        char letterForChecking = charArray[0];
        for (char letter : charArray) {
            if (letter != letterForChecking) {
                return false;
            }
        }
        return true;
    }

    public static boolean isIncrementalLettersWord(String word) {
        for (int i = 0; i < word.length() - 1; i++) {
            if (word.charAt(i) > word.charAt(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public static void counter (int wordLength) {
        if (wordLength == 3) {
            lengthThree.getAndIncrement();
        } else if ((wordLength == 4)) {
            lengthFour.getAndIncrement();
        } else if ((wordLength == 5)) {
            lengthFive.getAndIncrement();
        }
    }
}
