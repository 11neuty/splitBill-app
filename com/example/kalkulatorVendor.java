package com.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class kalkulatorVendor {

    // Method to calculate the total cost per person considering tax and discount
    public static Map<String, Double> calculateCostPerPerson(Map<String, Double> personToFoodCost, double totalTax, double totalDiscount) {
        double totalFoodCost = 0;

        for (double cost : personToFoodCost.values()) {
            totalFoodCost += cost;
        }

        Map<String, Double> personToTotalCost = new HashMap<>();

        for (Map.Entry<String, Double> entry : personToFoodCost.entrySet()) {
            String person = entry.getKey();
            double foodCost = entry.getValue();

            double proportionalTax = (foodCost / totalFoodCost) * totalTax;
            double proportionalDiscount = (foodCost / totalFoodCost) * totalDiscount;

            double finalCost = foodCost + proportionalTax - proportionalDiscount;
            personToTotalCost.put(person, finalCost);
        }

        return personToTotalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> personToFoodCost = new HashMap<>();

        // Input judul makan siang
        System.out.print("Masukkan judul makan siang (misalnya, 'Makan Siang Makan Masakan Padang'): ");
        String lunchTitle = scanner.nextLine();

        // Input jumlah orang dan harga makanan mereka
        System.out.print("Masukkan jumlah orang yang makan: ");
        int numberOfPeople = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        for (int i = 0; i < numberOfPeople; i++) {
            System.out.print("Masukkan nama orang ke-" + (i + 1) + ": ");
            String name = scanner.nextLine();

            System.out.print("Masukkan harga makanan untuk " + name + ": ");
            double foodCost = scanner.nextDouble();
            scanner.nextLine();  // Consume newline

            // Menyimpan data nama dan harga makanan
            personToFoodCost.put(name, foodCost);
        }

        // Review and edit if necessary
        boolean isReviewing = true;
        while (isReviewing) {
            System.out.println("\nReview data:");
            for (Map.Entry<String, Double> entry : personToFoodCost.entrySet()) {
                System.out.println(entry.getKey() + ": Rp " + String.format("%.2f", entry.getValue()));
            }

            System.out.print("\nApakah ada yang ingin diedit? (yes/no): ");
            String editResponse = scanner.nextLine();

            if (editResponse.equalsIgnoreCase("no")) {
                isReviewing = false;
            } else {
                System.out.print("Masukkan nama orang yang ingin diedit: ");
                String nameToEdit = scanner.nextLine();

                if (personToFoodCost.containsKey(nameToEdit)) {
                    System.out.print("Apakah Anda ingin mengedit nama atau harga? (nama/harga): ");
                    String editChoice = scanner.nextLine();

                    if (editChoice.equalsIgnoreCase("nama")) {
                        System.out.print("Masukkan nama baru: ");
                        String newName = scanner.nextLine();
                        double foodCost = personToFoodCost.remove(nameToEdit);
                        personToFoodCost.put(newName, foodCost);
                    } else if (editChoice.equalsIgnoreCase("harga")) {
                        System.out.print("Masukkan harga baru untuk " + nameToEdit + ": ");
                        double newFoodCost = scanner.nextDouble();
                        scanner.nextLine();  // Consume newline
                        personToFoodCost.put(nameToEdit, newFoodCost);
                    } else {
                        System.out.println("Pilihan tidak valid.");
                    }
                } else {
                    System.out.println("Nama tidak ditemukan.");
                }
            }
        }

        // Input total pajak dan diskon
        System.out.print("Masukkan total pajak: ");
        double totalTax = scanner.nextDouble();

        System.out.print("Masukkan total diskon: ");
        double totalDiscount = scanner.nextDouble();

        // Menghitung biaya per orang
        Map<String, Double> personToTotalCost = calculateCostPerPerson(personToFoodCost, totalTax, totalDiscount);

        // Menampilkan hasil akhir biaya per orang dengan judul makan siang
        System.out.println("\n" + lunchTitle);
        System.out.println("Biaya akhir lu pada udah diitung sama pajak dan diskon:");
        for (Map.Entry<String, Double> entry : personToTotalCost.entrySet()) {
            System.out.println(entry.getKey() + ": Rp " + String.format("%.2f", entry.getValue()));
        }
        System.out.println("\nTerima Kasih :) :) :)");
    }
}
