package by.it.group410971.maleckij.lesson02;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class C_GreedyKnapsack {
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        InputStream inputStream = C_GreedyKnapsack.class.getResourceAsStream("greedyKnapsack.txt");
        double costFinal = new C_GreedyKnapsack().calc(inputStream);
        long finishTime = System.currentTimeMillis();
        System.out.printf("Общая стоимость %f (время %d)", costFinal, finishTime - startTime);
    }

    double calc(InputStream inputStream) throws FileNotFoundException {
        Scanner input = new Scanner(inputStream);
        int n = input.nextInt();
        int W = input.nextInt();
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) {
            items[i] = new Item(input.nextInt(), input.nextInt());
        }
        Arrays.sort(items, (a, b) -> Double.compare((double)b.cost / b.weight, (double)a.cost / a.weight)); // Сортируем по удельной стоимости
        double result = 0;
        int remainingWeight = W;
        for (Item item : items) {
            if (remainingWeight <= 0) break;
            int takeWeight = Math.min(item.weight, remainingWeight);
            result += takeWeight * ((double)item.cost / item.weight);
            remainingWeight -= takeWeight;
        }
        return result;
    }

    static class Item {
        int cost;
        int weight;

        Item(int cost, int weight) {
            this.cost = cost;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Item{" + "cost=" + cost + ", weight=" + weight + '}';
        }
    }
}