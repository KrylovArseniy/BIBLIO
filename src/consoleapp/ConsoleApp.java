package consoleapp;

import dictionary.Dictionary;
import dictionary.Language1Validator;
import dictionary.Language2Validator;

import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dictionary language1Dictionary = new Dictionary("language1.txt", new Language1Validator());
        Dictionary language2Dictionary = new Dictionary("language2.txt", new Language2Validator());

        while (true) {
            System.out.println("\nВыберите словарь:");
            System.out.println("1 - Язык 1 (4 латинские буквы)");
            System.out.println("2 - Язык 2 (5 цифр)");
            System.out.println("0 - Выход");
            System.out.print("> ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            Dictionary currentDictionary = (choice == 1) ? language1Dictionary : language2Dictionary;
            if (currentDictionary == null) {
                System.out.println("Неверный выбор!");
                continue;
            }

            System.out.println("\nВыберите действие:");
            System.out.println("1 - Просмотр словаря");
            System.out.println("2 - Добавление записи");
            System.out.println("3 - Поиск записи");
            System.out.println("4 - Удаление записи");
            System.out.print("> ");

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1:
                    currentDictionary.print();
                    break;
                case 2:
                    handleAddEntry(scanner, currentDictionary);
                    break;
                case 3:
                    handleFindEntry(scanner, currentDictionary);
                    break;
                case 4:
                    handleRemoveEntry(scanner, currentDictionary);
                    break;
                default:
                    System.out.println("Неверное действие!");
            }
        }
        scanner.close();
    }

    private static void handleAddEntry(Scanner scanner, Dictionary dictionary) {
        System.out.print("Введите ключ: ");
        String key = scanner.nextLine();
        System.out.print("Введите значение: ");
        String value = scanner.nextLine();

        if (dictionary.add(key, value)) {
            System.out.println("Запись успешно добавлена!");
        } else {
            System.out.println("Ошибка: неверный формат ключа!");
        }
    }

    private static void handleFindEntry(Scanner scanner, Dictionary dictionary) {
        System.out.print("Введите ключ для поиска: ");
        String key = scanner.nextLine();
        String value = dictionary.findEntry(key);

        System.out.println(value != null ?
                "Результат: " + value :
                "Запись не найдена!");
    }

    private static void handleRemoveEntry(Scanner scanner, Dictionary dictionary) {
        System.out.print("Введите ключ для удаления: ");
        String key = scanner.nextLine();

        if (dictionary.removeEntry(key)) {
            System.out.println("Запись успешно удалена!");
        } else {
            System.out.println("Запись не найдена!");
        }
    }
}