import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class Main {
    static List<Integer> keyList = new LinkedList<>(); // List of keys used to encrypt chars
    static String binaryNumbers = ""; // Used to return encrypted binary codes
    static Scanner prompt = new Scanner(System.in);

    public static void main(String[] args) {
        // Request input
        System.out.print("Enter 'D' to Decrypt or 'E' to Encrypt: ");
        String start = prompt.nextLine();
        System.out.println();

        if (start.equalsIgnoreCase("D")){
            // Request input
            System.out.print("Enter binary keys separated by spaces: ");
            String binaryString = prompt.nextLine();
            System.out.print("Enter keys separated by spaces: ");
            String key = prompt.nextLine();

            // Splitting string input into array of elements
            String[] binaryLine = binaryString.split(" ");
            String[] keys = key.split(" ");
            String newString = ""; // Used to return final string

            // Decrypt word 1 char at a time
            for (int i = 0; i < keys.length; i++){
                char decryptedChar = DecryptChar(binaryLine[i], Integer.parseInt(keys[i]));
                newString += decryptedChar; // Add char to new string
            }
            System.out.println("Secret Message: " + newString);

        } else if (start.equalsIgnoreCase("E")) {
            // Request input
            System.out.print("Enter the word you wish to encrypt: ");
            String usrWord = prompt.nextLine();

            for (int i = 0; i < usrWord.length(); i++){ // Incrementally convert chars to binary codes
                char letter = usrWord.charAt(i);
                String encryptedLetter = EncryptChar(letter);
                binaryNumbers += encryptedLetter + " "; // Add all binary codes to 1 string
            }
            System.out.println("Binary Codes: " + binaryNumbers); // Print result
            System.out.println("Keys: " + keyList);

        } else {
            System.out.println("Invalid input!");
        }

    }
    private static String EncryptChar(char letter){
        /**
         * Creates random int between 0 & 26, then adds it to the list
         * Adds the int to the char given in parameter
         * Returns new int in binary form
         */
        Random random = new Random();
        int key = random.nextInt(27); // Random int between 0-26 (inclusive)
        keyList.add(key);
        int encryptedNum = letter + key;
        return Integer.toBinaryString(encryptedNum);
    }
    private static char DecryptChar(String binary, int key){
        /**
         * Takes binary string & key
         * Converts binary to ascii
         * Subtracts key from ascii
         * returns char of ascii value
         */
        int encryptedNum = Integer.parseInt(binary, 2);
        int realAscii = encryptedNum - key;
        return (char) realAscii;
    }
}

/** Ways to IMPROVE:
 * put code in do-while loop
 * Perhaps try using arrays instead (use length of input word to create array length)
 * create back up for if wrong input is given
 * Perhaps make a function that uses decrypt and encrypted to return the wanted outputs
 */