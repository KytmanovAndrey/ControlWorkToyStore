package service;

import model.Toy;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class ToyService {
    private PriorityQueue<Toy> toys;
    private List<Toy> winningToys = new ArrayList<>();
    Random random = new Random();

    public List<Toy> getWinningToys() {
        return winningToys;
    }

    public PriorityQueue<Toy> getToys() {
        return toys;
    }

    public ToyService(PriorityQueue<Toy> toys) {
        this.toys = toys;
    }

    public Toy makeDraw() {
        int sumOfChances = 0;
        Toy currentToy = toys.peek();
        for (Toy toy : toys) {
            sumOfChances += toy.getChance();
        }
        int currentChance = random.nextInt(sumOfChances) + 1;
        System.out.println(currentChance);
        for (Toy toy : toys) {
            currentChance -= toy.getChance();
            if (currentChance <= 0) {
                currentToy = toy;
                break;
            }
        }
        winningToys.add(currentToy);
        return currentToy;
    }

    public void createToy(String description, int chance) {
        int maxId = 1;
        if (!toys.isEmpty()) {
            for (Toy toy : toys) {
                if (toy.getId() >= maxId) {
                    maxId = toy.getId() + 1;
                }
            }
        }
        Toy toy = new Toy(maxId, description, chance);
        toys.add(toy);
    }

    public void takeWinnerToFile() {
        try (FileWriter fileWriter = new FileWriter("output.txt", true)) {
            fileWriter.write(winningToys.getFirst() + "\n");
            winningToys.remove(winningToys.getFirst());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
