package game;

import java.util.Scanner;

public class Utils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String readLineTrimmed() {
        return scanner.nextLine().trim();
    }

    public static int readChoice(int min, int max) {
        int choice = -1;
        while (true) {
            System.out.print("Выберите действие (" + min + "-" + max + "): ");
            String input = scanner.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
                if (choice >= min && choice <= max) break;
                else System.out.println("Введите число в диапазоне от " + min + " до " + max + "!");
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное число!");
            }
        }
        return choice;
    }
}
