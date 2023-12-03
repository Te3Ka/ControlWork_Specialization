package org.example;

import java.time.LocalDate;
import java.util.Set;

public class Dog extends PetsAnimals {
    public Dog(String name, LocalDate birthday) {
        super(name, birthday, TypeAnimal.Dog);
    }

    public Dog(String name, LocalDate birthday, Set<String> commands) {
        super(name, birthday, commands, TypeAnimal.Dog);
    }
}
