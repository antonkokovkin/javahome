import java.util.Arrays;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Читаем строку
        String input = scanner.nextLine();

        // Разделяем на слова
        String[] words = input.split(" ");

        // Обрабатываем каждое слово
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            // Приводим к нижнему регистру
            word = word.toLowerCase();
            // Преобразуем в массив символов и сортируем
            char[] chars = word.toCharArray();
            Arrays.sort(chars);
            // Добавляем отсортированное слово в результат
            result.append(new String(chars)).append(" ");
        }

        // Выводим результат, убрав лишний пробел в конце
        System.out.println(result.toString().trim());

        scanner.close();
    }
}