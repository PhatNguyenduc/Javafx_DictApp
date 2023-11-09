package Controller;
import java.util.Scanner; import java.util.Random; import java.util.Arrays;

public class anagram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        String[] words = {"apple", "banana", "cherry", "orange", "pear"};
        String word = words[rand.nextInt(words.length)];
        char[] letters = word.toCharArray();
        for (int i = 0; i < letters.length; i++) {
            int j = rand.nextInt(letters.length);
            char temp = letters[i];
            letters[i] = letters[j];
            letters[j] = temp;
        }
        boolean check = true;
        while(check) {
            System.out.println("Guess the word: " + new String(letters));
            String guess = input.nextLine();
            if (guess.equals(word)) {
                System.out.println("Congratulations! You guessed the word.");
                check = false;
            } else {
                System.out.println("Sorry, the word was " + word + ".");
            }
        }
    }
}