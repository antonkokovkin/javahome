import java.util.Random;

public class Task2 {
    public static void main(String[] args) {
        // Создаём объект Random для генерации случайных чисел
        Random random = new Random();

        // Генерируем выборы: 0 - камень, 1 - ножницы, 2 - бумага
        int vasyaChoice = random.nextInt(3);  // От 0 до 2
        int petyaChoice = random.nextInt(3);

        // Выводим выборы
        System.out.println("Выбор Васи: " + getFigureName(vasyaChoice));
        System.out.println("Выбор Пети: " + getFigureName(petyaChoice));

        // Определяем победителя
        if (vasyaChoice == petyaChoice) {
            System.out.println("Ничья!");
        } else if ((vasyaChoice == 0 && petyaChoice == 1) ||  // Камень бьёт ножницы
                (vasyaChoice == 1 && petyaChoice == 2) ||  // Ножницы бьют бумагу
                (vasyaChoice == 2 && petyaChoice == 0)) {  // Бумага бьёт камень
            System.out.println("Выиграл Вася!");
        } else {
            System.out.println("Выиграл Петя!");
        }
    }

    // Метод для получения названия фигуры по номеру
    private static String getFigureName(int choice) {
        return switch (choice) {
            case 0 -> "Камень";
            case 1 -> "Ножницы";
            case 2 -> "Бумага";
            default -> "Неизвестно";
        };
    }
}