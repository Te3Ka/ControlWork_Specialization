package org.example;

import java.time.LocalDate;
import java.util.Set;

public class Camel extends PackAnimals {
    public Camel(String name, LocalDate birthday) {
        super(name, birthday, TypeAnimal.Camel);
    }

    public Camel(String name, LocalDate birthday, Set<String> commands) {
        super(name, birthday, commands, TypeAnimal.Camel);
    }
}
