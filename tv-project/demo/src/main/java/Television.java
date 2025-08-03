import java.util.Random; // Для дополнительной генерации случайных значений (если нужно в классе)

public class Television {
    // Private поля
    private String brand;      // Марка телевизора
    private int size;          // Размер экрана в дюймах
    private boolean isSmart;   // Умный телевизор или нет
    private int currentChannel; // Текущий канал (по умолчанию 1)

    // Конструктор для инициализации полей
    public Television(String brand, int size, boolean isSmart) {
        this.brand = brand;
        this.size = size;
        this.isSmart = isSmart;
        this.currentChannel = 1; // Начальный канал
    }

    // Методы
    public void turnOn() {
        System.out.println("Телевизор " + brand + " включён.");
    }

    public void turnOff() {
        System.out.println("Телевизор " + brand + " выключён.");
    }

    public void changeChannel(int newChannel) {
        if (newChannel > 0) {
            currentChannel = newChannel;
            System.out.println("Канал изменён на " + currentChannel + " для телевизора " + brand);
        } else {
            System.out.println("Неверный канал для телевизора " + brand);
        }
    }

    // Геттеры (для доступа к свойствам)
    public String getBrand() {
        return brand;
    }

    public int getSize() {
        return size;
    }

    public boolean isSmart() {
        return isSmart;
    }

    public int getCurrentChannel() {
        return currentChannel;
    }

    // Дополнительный метод: Случайная смена канала (для демонстрации)
    public void randomChannel() {
        Random random = new Random();
        int randomChannel = random.nextInt(100) + 1; // Случайный канал от 1 до 100
        changeChannel(randomChannel);
    }
}