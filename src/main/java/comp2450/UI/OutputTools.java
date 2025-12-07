package comp2450.UI;

public class OutputTools {

    public static void red(String message) {
        System.out.printf("\u001B[31m%s\n\u001B[0m", message);
    }
    public static void green(String message) {
        System.out.printf("\u001B[32m%s\n\u001B[0m", message);
    }
}
