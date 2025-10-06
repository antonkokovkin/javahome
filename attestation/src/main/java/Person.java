import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private double money;
    private List<Product> bag = new ArrayList<>();

    // Конструктор с валидацией
    public Person(String name, double money) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3 символов");
        }
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.name = name;
        this.money = money;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public List<Product> getBag() {
        return new ArrayList<>(bag); // Возвращаем копию для безопасности
    }

    // Метод для покупки продукта
    public void buyProduct(Product product) {
        if (product.getPrice() <= money) {
            bag.add(product);
            money -= product.getPrice();
            System.out.println(name + " купил " + product.getName());
        } else {
            System.out.println(name + " не может позволить себе " + product.getName());
        }
    }

    // Сеттеры (если нужно изменить, с валидацией)
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3 символов");
        }
        this.name = name;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }

    @Override
    public String toString() {
        String bagStr = bag.isEmpty() ? "пусто" : bag.toString();
        return name + " (деньги: " + money + ", пакет: " + bagStr + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.money, money) == 0 && Objects.equals(name, person.name) && Objects.equals(bag, person.bag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, money, bag);
    }
}