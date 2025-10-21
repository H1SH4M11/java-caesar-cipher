import java.util.Scanner;

/**
 * A simple program to encrypt or decrypt messages using the Caesar Cipher.
 * This was created as a basic Java project to practice core concepts.
 */
public class CaesarCipher {

    /**
     * The main logic that handles the character shifting.
     * @param plainText The text to be processed.
     * @param shiftAmount The number of places to shift letters. Can be positive (encrypt) or negative (decrypt).
     * @return The resulting text after the shift.
     */
    public static String processText(String plainText, int shiftAmount) {
        StringBuilder cipherText = new StringBuilder();

        for (char character : plainText.toCharArray()) {
            // Only shift letters, leave everything else as is
            if (Character.isLetter(character)) {
                // Determine if we're working with uppercase or lowercase
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                
                // Find the letter's position in the alphabet (0-25)
                int originalAlphabetPos = character - base;
                
                // Shift the letter, and use modulo to wrap around the alphabet
                int newAlphabetPos = (originalAlphabetPos + shiftAmount) % 26;

                // The modulo operator in Java can return a negative number,
                // so we need to correct for that if it happens.
                if (newAlphabetPos < 0) {
                    newAlphabetPos = newAlphabetPos + 26;
                }
                
                // Convert the new position back to a character
                char shiftedChar = (char) (base + newAlphabetPos);
                cipherText.append(shiftedChar);
            } else {
                cipherText.append(character);
            }
        }

        return cipherText.toString();
    }

    // The main entry point for the program
    public static void main(String[] args) {
        Scanner inputReader = new Scanner(System.in);

        System.out.println("--- Caesar Cipher ---");
        System.out.print("Do you want to (e)ncrypt or (d)ecrypt? ");
        String choice = inputReader.nextLine().toLowerCase();

        System.out.print("Enter your message: ");
        String userMessage = inputReader.nextLine();

        System.out.print("Enter your secret key (a number): ");
        int key = inputReader.nextInt();
        
        String resultMessage = "";

        if (choice.startsWith("e")) {
            resultMessage = processText(userMessage, key);
            System.out.println("\nYour encrypted message is:");
            System.out.println(resultMessage);
        } else if (choice.startsWith("d")) {
            // To decrypt, we just shift in the opposite direction
            resultMessage = processText(userMessage, -key);
            System.out.println("\nYour decrypted message is:");
            System.out.println(resultMessage);
        } else {
            System.out.println("Sorry, that's not a valid choice.");
        }
        
        inputReader.close();
    }
}
