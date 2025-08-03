import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        // Создаём Scanner для чтения ввода
        Scanner scanner = new Scanner(System.in);

        // Читаем символ (маленькая буква)
        String input = scanner.nextLine();
        char letter = input.charAt(0);

        // Порядок букв на клавиатуре (замкнутая)
        char[] keyboard = {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'm'};

        // Находим букву слева
        for (int i = 0; i < keyboard.length; i++) {
            if (keyboard[i] == letter) {
                // Если это первая буква (q), берём последнюю (m), иначе берём предыдущую
                int leftIndex = (i == 0) ? keyboard.length - 1 : i - 1;
                System.out.println(keyboard[leftIndex]);
                break;
            }
        }

        scanner.close();
    }
}