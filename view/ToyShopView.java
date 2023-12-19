package view;

import model.Toy;
import service.ToyService;

import java.util.Scanner;

public class ToyShopView {
    private final ToyService toyService;

    public ToyShopView(ToyService toyService) {
        this.toyService = toyService;
    }

    public void showUI() {
        Scanner scanner = new Scanner(System.in, "ibm866");
        String cmd = "";
        while (!cmd.equals("5")) {
            System.out.println("\nМеню:\n" +
            "1. Добавить игрушку в список\n" +
            "2. Изменить вес выпадения игрушки\n" +
            "3. Провести розыгрыш игрушки и запись в призовой список\n" +
            "4. Произвести выдачу игрушки и запись ее в файл\n" +
            "5. Выход\n" +
            "Введите номер операции: ");
            cmd = scanner.next();
            switch (cmd) {
                case "1":
                    System.out.println("Введите описание игрушки: ");
                    String description = scanner.next();
                    System.out.println("Введите вес выпадения игрушки: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Некорректный ввод");
                        System.out.print("Введите вес выпадения игрушки: ");
                        scanner.next();
                    }
                    int chance = scanner.nextInt();
                    toyService.createToy(description, chance);
                    break;
                case "2":
                    for (Toy toy :toyService.getToys() ) {
                        System.out.println(toy);
                    }
                    System.out.println("Введите id игрушки: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Некорректный ввод");
                        System.out.print("Введите id игрушки: ");
                        scanner.next();
                    }
                    int id = scanner.nextInt();
                    System.out.println("Введите новый вес выпадения игрушки: ");
                    while (!scanner.hasNextInt()) {
                        System.out.println("Некорректный ввод");
                        System.out.print("Введите новый вес выпадения игрушки: ");
                        scanner.next();
                    }
                    int newChance = scanner.nextInt();
                    for (Toy toy :toyService.getToys() ) {
                        if (toy.getId() == id) {
                            toy.setChance(newChance);
                        }
                    }
                    break;
                case "3":
                    System.out.println("Выпала игрушка:");
                    System.out.println(toyService.makeDraw());
                    break;
                case "4":
                    if (toyService.getWinningToys().isEmpty()) {
                        System.out.println("Призовой список пуст, выдавать нечего");
                    } else {
                        toyService.takeWinnerToFile();
                    }
                    break;
                case "5":
                    break;
                default:
                    System.out.println("Некорректный ввод");
                    break;
            }
        }
    }
}
