import java.util.Scanner;

public class WordAnimator {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a word to animate:");
        String word = scanner.nextLine();

        System.out.println("\nChoose an animation:");
        System.out.println("1. Bouncing word");
        System.out.println("2. Growing word");
        System.out.println("3. Colorful word (if supported)");
        System.out.println("4. Typing animation");
        System.out.println("5. Spinning word");
        System.out.print("Enter your choice (1-5): ");

        int choice = scanner.nextInt();

        switch(choice) {
            case 1:
                bounceAnimation(word);
                break;
            case 2:
                growAnimation(word);
                break;
            case 3:
                colorfulAnimation(word);
                break;
            case 4:
                typingAnimation(word);
                break;
            case 5:
                spinningAnimation(word);
                break;
            default:
                System.out.println("Invalid choice!");
        }

        scanner.close();
    }

    // Animation 1: Bouncing word
    public static void bounceAnimation(String word) throws InterruptedException {
        int height = 5;
        for (int i = 0; i < 20; i++) {
            clearConsole();

            // Calculate current position in the bounce
            int position = (int)(Math.abs(Math.sin(i * 0.2)) * height);

            // Print empty lines to position the word
            for (int j = 0; j < position; j++) {
                System.out.println();
            }

            System.out.println(centerWord(word, 50));

            Thread.sleep(100);
        }
    }

    // Animation 2: Growing word
    public static void growAnimation(String word) throws InterruptedException {
        for (int i = 1; i <= 10; i++) {
            clearConsole();
            String spaces = repeat(" ", i);
            System.out.println(spaces + word + spaces);
            Thread.sleep(200);
        }

        for (int i = 10; i >= 1; i--) {
            clearConsole();
            String spaces = repeat(" ", i);
            System.out.println(spaces + word + spaces);
            Thread.sleep(200);
        }
    }

    // Animation 3: Colorful word (works in terminals that support ANSI colors)
    public static void colorfulAnimation(String word) throws InterruptedException {
        String[] colors = {
                "\u001B[31m", // Red
                "\u001B[32m", // Green
                "\u001B[33m", // Yellow
                "\u001B[34m", // Blue
                "\u001B[35m", // Purple
                "\u001B[36m"  // Cyan
        };

        String reset = "\u001B[0m";

        for (int i = 0; i < 20; i++) {
            clearConsole();
            for (int j = 0; j < word.length(); j++) {
                String color = colors[(i + j) % colors.length];
                System.out.print(color + word.charAt(j) + reset);
            }
            System.out.println();
            Thread.sleep(200);
        }
    }

    // Animation 4: Typing animation
    public static void typingAnimation(String word) throws InterruptedException {
        for (int i = 1; i <= word.length(); i++) {
            clearConsole();
            System.out.println(word.substring(0, i));
            Thread.sleep(200);
        }

        for (int i = word.length() - 1; i > 0; i--) {
            clearConsole();
            System.out.println(word.substring(0, i));
            Thread.sleep(100);
        }
    }

    // Animation 5: Spinning word
    public static void spinningAnimation(String word) throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            clearConsole();
            int offset = i % word.length();
            String rotated = word.substring(offset) + word.substring(0, offset);
            System.out.println(centerWord(rotated, 50));
            Thread.sleep(200);
        }
    }

    // Helper method to center the word
    private static String centerWord(String word, int width) {
        int padding = (width - word.length()) / 2;
        if (padding <= 0) return word;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) {
            sb.append(" ");
        }
        sb.append(word);
        return sb.toString();
    }

    // Helper method to repeat a string
    private static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }

    // Helper method to clear console (works in most terminals)
    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}