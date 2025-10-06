import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> persons = new ArrayList<>();

        // Ввод покупателей (до ввода "END" для имени)
        System.out.println("Вводите покупателей. Для завершения введите END в имя.");
        while (true) {
            System.out.print("Введите имя покупателя: ");
            String name = scanner.nextLine().trim();
            if (name.equalsIgnoreCase("END")) {
                break;
            }
            System.out.print("Введите сумму денег: ");
            String moneyStr = scanner.nextLine().trim();
            try {
                double money = Double.parseDouble(moneyStr);
                Person person = new Person(name, money);
                persons.add(person);
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат денег. Повторите ввод.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage() + ". Повторите ввод.");
            }
        }

        // Процесс покупки для каждого покупателя
        for (Person person : persons) {
            boolean boughtSomething = false;
            System.out.println("\nПокупки для " + person.getName() + ". Введите END для завершения.");
            while (true) {
                System.out.print("Введите название продукта: ");
                String productName = scanner.nextLine().trim();
                if (productName.equalsIgnoreCase("END")) {
                    break;
                }
                System.out.print("Введите цену продукта: ");
                String priceStr = scanner.nextLine().trim();
                try {
                    double price = Double.parseDouble(priceStr);
                    Product product = new Product(productName, price);
                    person.buyProduct(product);
                    boughtSomething = true; // Если купил, но в buyProduct уже проверка, здесь отмечаем попытку, но лучше проверить размер bag после
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат цены. Повторите ввод.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage() + ". Повторите ввод.");
                }
            }
            if (person.getBag().isEmpty()) {
                System.out.println(person.getName() + " Ничего не куплено");
            }
        }

        // Вывод результатов для всех покупателей
        System.out.println("\nРезультаты:");
        for (Person person : persons) {
            System.out.println(person);
        }

        scanner.close();
    }
}