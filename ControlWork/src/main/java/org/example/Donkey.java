package org.example;

import java.time.LocalDate;
import java.util.Set;

public class Donkey extends PackAnimals {
    public Donkey(String name, LocalDate birthday) {
        super(name, birthday, TypeAnimal.Donkey);
    }

    public Donkey(String name, LocalDate birthday, Set<String> commands) {
        super(name, birthday, commands, TypeAnimal.Donkey);
    }
}
