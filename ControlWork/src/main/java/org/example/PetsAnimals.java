package org.example;

import java.time.LocalDate;
import java.util.Set;

public class PetsAnimals extends Animal {
    protected PetsAnimals(String name, LocalDate birthday, TypeAnimal type) {
        super(name, birthday, type);
    }

    protected PetsAnimals(String name, LocalDate birthday, Set<String> commands, TypeAnimal type) {
        super(name, birthday, commands, type);
    }
}
