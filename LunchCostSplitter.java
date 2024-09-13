import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LunchCostSplitter {

    // Method to calculate the total cost per person considering tax and discount
    public static Map<String, Double> calculateCostPerPerson(Map<String, Double> personToFoodCost, double totalTax, double totalDiscount) {
        double totalFoodCost = 0;

        // Hitung total biaya makanan keseluruhan
        for (double cost : personToFoodCost.values()) {
            totalFoodCost += cost;
        }

        // Membuat map untuk menyimpan hasil biaya per orang
        Map<String, Double> personToTotalCost = new HashMap<>();

        // Menghitung biaya akhir per orang berdasarkan proporsi
        for (Map.Entry<String, Double> entry : personToFoodCost.entrySet()) {
            String person = entry.getKey();
            double foodCost = entry.getValue();

            // Menghitung proporsi pajak dan diskon untuk tiap orang
            double proportionalTax = (foodCost / totalFoodCost) * totalTax;
            double proportionalDiscount = (foodCost / totalFoodCost) * totalDiscount;

            // Biaya akhir per orang
            double finalCost = foodCost + proportionalTax - proportionalDiscount;
            personToTotalCost.put(person, finalCost);
        }

        return personToTotalCost;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Double> personToFoodCost = new HashMap<>();

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

        // Input total pajak dan diskon
        System.out.print("Masukkan total pajak: ");
        double totalTax = scanner.nextDouble();

        System.out.print("Masukkan total diskon: ");
        double totalDiscount = scanner.nextDouble();

        // Menghitung biaya per orang
        Map<String, Double> personToTotalCost = calculateCostPerPerson(personToFoodCost, totalTax, totalDiscount);

        // Menampilkan hasil akhir biaya per orang
        System.out.println("\nBiaya akhir per orang setelah pajak dan diskon:");
        for (Map.Entry<String, Double> entry : personToTotalCost.entrySet()) {
            System.out.println(entry.getKey() + ": Rp " + String.format("%.2f", entry.getValue()));
        }
    }
}
