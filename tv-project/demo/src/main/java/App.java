import java.util.Scanner;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // TV1: Параметры с клавиатуры
        System.out.print("Введите марку для TV1: ");
        String brand1 = scanner.nextLine();
        System.out.print("Введите размер экрана для TV1 (в дюймах): ");
        int size1 = scanner.nextInt();
        System.out.print("Это умный телевизор для TV1? (true/false): ");
        boolean isSmart1 = scanner.nextBoolean();
        Television tv1 = new Television(brand1, size1, isSmart1);

        // TV2: Случайные параметры
        String[] brands = {"Samsung", "LG", "Sony", "Philips"};
        String brand2 = brands[random.nextInt(brands.length)];
        int size2 = random.nextInt(30) + 30; // Случайный размер от 30 до 59 дюймов
        boolean isSmart2 = random.nextBoolean();
        Television tv2 = new Television(brand2, size2, isSmart2);

        // TV3: Жёстко заданные параметры
        Television tv3 = new Television("Toshiba", 55, true);

        // Проверка работы: Вывод свойств и вызов методов
        System.out.println("\nTV1: Марка - " + tv1.getBrand() + ", Размер - " + tv1.getSize() + ", Умный - " + tv1.isSmart());
        tv1.turnOn();
        tv1.changeChannel(10);
        tv1.randomChannel(); // Случайная смена канала
        tv1.turnOff();

        System.out.println("\nTV2 (случайный): Марка - " + tv2.getBrand() + ", Размер - " + tv2.getSize() + ", Умный - " + tv2.isSmart());
        tv2.turnOn();
        tv2.changeChannel(5);
        tv2.turnOff();

        System.out.println("\nTV3: Марка - " + tv3.getBrand() + ", Размер - " + tv3.getSize() + ", Умный - " + tv3.isSmart());
        tv3.turnOn();
        tv3.changeChannel(7);
        tv3.turnOff();

        scanner.close();
    }
}