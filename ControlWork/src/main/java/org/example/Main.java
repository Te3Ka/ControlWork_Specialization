package org.example;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static AnimalManager animalManager;
    private static Scanner in;

    public static void main(String[] args) {
        animalManager = new AnimalManager();
        in = new Scanner(System.in);
        while (true) {
            printOperations();
            selectOperation(getInt("Выбрана опция: "));
            getText("Нажмите Enter для продолжения.");
        }
    }

    public static void printOperations() {
        System.out.println("=".repeat(20));
        System.out.println("Выберите действие:");
        System.out.println("1) Загрузить список животных из файла.");
        System.out.println("2) Сохранить список животных в файл.");
        System.out.println("3) Вывести на экран список животных.");
        System.out.println("4) Добавить новое животное в список.");
        System.out.println("5) Обучить животное новым командам.");
        System.out.println("6) Поиск животного по дате рождения.");
        System.out.println("7) Посчитать общее количество животных.");
        System.out.println("0) Закрыть программу.");
        System.out.print(">>: ");
    }

    public static void selectOperation(int numberOperation) {
        try {
            switch (numberOperation){
                case 1:
                    loadFileAnimals();
                    break;
                case 2:
                    saveFileAnimals();
                    break;
                case 3:
                    printListAnimals();
                    break;
                case 4:
                    addNewAnimals();
                    break;
                case 5:
                    addNewCommands();
                    break;
                case 6:
                    birthdayFindAnimals();
                    break;
                case 7:
                    countAnimals();
                    break;
                case 0:
                    System.out.println("Спасибо за работу!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Команда не найдена. Повторите ввод.");
                    break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    protected static int getInt() {
        while (true) {
            try {
                return Integer.parseInt(getText());
            } catch (Exception ex) {
                System.out.println("Неверный ввод данных.");
            }
        }
    }

    protected static int getInt(String string) {
        System.out.print(string);
        return getInt();
    }

    protected static String getText() {
        return in.nextLine();
    }

    protected static String getText(String string) {
        System.out.print(string);
        return getText();
    }

    protected static LocalDate getDate() {
        while (true) {
            try {
                System.out.println("Введите дату в форсмате YYYY-MM-DD: ");
                return LocalDate.parse(in.nextLine());
            } catch (Exception ex) {
                continue;
            }
        }
    }

    public static void loadFileAnimals() throws IOException {
        animalManager.loadListAnimals();
        System.out.println("Список животных загружен из файла.");
    }

    public static void saveFileAnimals() throws IOException {
        animalManager.saveListAnimals();
        System.out.println("Список животных сохранён успешно!");
    }

    protected static Animal getNewAnimal() {
        while (true) {
            try {
                System.out.println("Введите данные о новом животном.");
                System.out.print("Введите породу животного (Cat, Dog, Hamster, Horse, Camel, Donkey): ");
                String type = in.nextLine();
                System.out.print("Введите кличку животного: ");
                String name = in.nextLine();
                System.out.print("Введите дату рождения в формате YYYY-MM-DD: ");
                String birthday = in.nextLine();
                String result = type + "///" + name + "///" + birthday + "///";
                return Animal.parseString(result);
            } catch (Exception ex) {
                continue;
            }
        }
    }

    public static void addNewAnimals() {
        animalManager.addNewAnimal(getNewAnimal());
        System.out.println("Новое животное успешно добавлено!");
    }

    public static void printListAnimals() {
        int index = 0;
        System.out.println();
        for (Animal animal : animalManager.getListAnimal()) {
            index += 1;
            String line = animal.toString().replace("///", " ");
            System.out.println(index + ") " + line);
        }
        System.out.println();
    }

    protected static Animal getIndexAnimal() {
        while (true) {
            try {
                return animalManager.getListAnimal().get(getInt("Введите номер животного: ") - 1);
            } catch (Exception ex) {
                System.out.println("Такого номера не существует в программе.");
            }
        }
    }

    public static void addNewCommands() {
        printListAnimals();
        Animal animal = getIndexAnimal();
        System.out.print("Введите новую команду: ");
        String newCommand = in.nextLine();
        animal.addNewCommand(newCommand);
        System.out.println("Успешно добавлено!");
    }

    public static void birthdayFindAnimals() {
        LocalDate date = getDate();
        for (Animal animal : animalManager.getListAnimal()) {
            if (animal.getBirthday().compareTo(date) != 0)
                continue;
            String string = animal.toString().replace("///", " ");
            System.out.println(string);
        }
    }

    public static void countAnimals() {
        int all = 0, pet = 0, pack = 0;
        for (Animal animal : animalManager.getListAnimal()) {
            all++;
            if (animal instanceof PetsAnimals)
                pet++;
            else if (animal instanceof PackAnimals)
                pack++;
        }
        System.out.println("Всего животных: " + all);
        System.out.println("Домашних: " + pet);
        System.out.println("Вьючных: " + pack);
    }
}