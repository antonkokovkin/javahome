import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        // Создаём объект Scanner для чтения ввода из консоли
        Scanner scanner = new Scanner(System.in);

        // Запрашиваем имя пользователя
        System.out.print("Введите ваше имя: ");
        String name = scanner.nextLine();  // Читаем строку ввода

        // Выводим приветствие в одну строку
        System.out.println("Привет, " + name);

        // Закрываем Scanner (хорошая практика)
        scanner.close();
    }
}