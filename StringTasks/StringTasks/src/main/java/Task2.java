import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Читаем строку
        String sequence = scanner.nextLine();

        // Считаем стрелки
        int arrowCount = 0;
        for (int i = 0; i <= sequence.length() - 5; i++) {
            String substring = sequence.substring(i, i + 5);
            if (substring.equals(">>-->") || substring.equals("<--<<")) {
                arrowCount++;
            }
        }

        // Выводим результат
        System.out.println(arrowCount);

        scanner.close();
    }
}