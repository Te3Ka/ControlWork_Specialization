package org.example;

import java.io.*;
import java.util.ArrayList;

public class AnimalManager {
    private ArrayList<Animal> listAnimal;

    public AnimalManager() {
        listAnimal = new ArrayList<Animal>();
    }

    public void loadListAnimals() throws IOException {
        File file = new File("Animals.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        line = br.readLine();
        while (line != null) {
            listAnimal.add(Animal.parseString(line));
            line = br.readLine();
        }
        br.close();
    }

    public void saveListAnimals() throws IOException {
        FileWriter fw = new FileWriter("Animals.txt", false);
        for (Animal animal : listAnimal) {
            fw.append(animal.toString() + "\n");
        }
        fw.flush();
        fw.close();
    }

    public ArrayList<Animal> getListAnimal() {
        return listAnimal;
    }

    public void addNewAnimal(Animal newAnimal) {
        listAnimal.add(newAnimal);
    }
}
