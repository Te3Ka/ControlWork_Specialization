package org.example;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.StringJoiner;

public class Animal {
    protected String name;
    protected LocalDate birthday;
    protected Set<String> commands;
    private TypeAnimal typeAnimal;

    protected Animal() {
    }

    protected Animal(String name, LocalDate birthday, TypeAnimal typeAnimal) {
        this.name = name;
        this.birthday = birthday;
        this.typeAnimal = typeAnimal;
        this.commands = new LinkedHashSet<>();
    }

    protected Animal(String name, LocalDate birthday, Set<String> commands, TypeAnimal typeAnimal) {
        this.name = name;
        this.birthday = birthday;
        this.commands = commands;
        this.typeAnimal = typeAnimal;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public int getAge() {
        return Period.between(birthday, LocalDate.now()).getYears();
    }

    public Set<String> getCommands() {
        return commands;
    }

    public boolean addNewCommand(String newCommand) {
        return commands.add(newCommand.toLowerCase());
    }

    public TypeAnimal getTypeAnimal() {
        return typeAnimal;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner("///");

        sj.add(typeAnimal.name());
        sj.add(name);
        sj.add(birthday.toString());

        String commandsAll = "";
        for (String command : commands) {
            if (commandsAll != "")
                commandsAll += ", ";
            commandsAll += command.toLowerCase();
        }
        if (commandsAll == "")
            commandsAll = " ";

        sj.add(commandsAll);
        sj.add("");

        return sj.toString();
    }

    public static Animal parseString(CharSequence input) throws RuntimeException {
        String[] splitStrings = input.toString().split("///");
        Set<String> commands = new LinkedHashSet<>();

        if (splitStrings.length >= 4) {
            String[] splitCommands = splitStrings[3].split(", ");
            for (String command : splitCommands) {
                String commandTrim = command.trim().replaceAll("\\s+", " ");
                if (commandTrim != "")
                    commands.add(commandTrim);
            }
        }
        try {
            splitStrings[0] = splitStrings[0].trim().replaceAll("\\s+", " ");
            splitStrings[1] = splitStrings[1].trim().replaceAll("\\s+", " ");
            splitStrings[2] = splitStrings[2].trim().replaceAll("\\s+", " ");
            String cap = splitStrings[0].substring(0, 1).toUpperCase() + splitStrings[0].substring(1);
            TypeAnimal type = TypeAnimal.valueOf(cap);
            String name = splitStrings[1];
            LocalDate birthday = LocalDate.parse(splitStrings[2]);

            switch (type) {
                case Dog:
                    return new Dog(name, birthday, commands);
                case Cat:
                    return new Cat(name, birthday, commands);
                case Hamster:
                    return new Hamster(name, birthday, commands);
                case Horse:
                    return new Horse(name, birthday, commands);
                case Camel:
                    return new Camel(name, birthday, commands);
                case Donkey:
                    return new Donkey(name, birthday, commands);
                default:
                    throw new RuntimeException();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Неверный ввод типа животного. ", ex);
        }
    }
}
