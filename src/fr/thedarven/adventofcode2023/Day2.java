package fr.thedarven.adventofcode2023;

import java.util.*;

public class Day2 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        Scanner sc = new Scanner(System.in);

        String line;
        int result = 0;
        while (true) {
            line = sc.nextLine();
            if (line.isEmpty() || line.isBlank()) {
                break;
            }
            String[] winningNumbersS = line.split(": ")[1].split(" \\| ")[0].split("\\s+");
            String[] numbersS = line.split(": ")[1].split(" \\| ")[1].split("\\s+");
            List<Integer> winningNumbers = Arrays.stream(winningNumbersS).filter(s -> !s.isBlank()).map(Integer::parseInt).toList();
            List<Integer> numbers = Arrays.stream(numbersS).filter(s -> !s.isBlank()).map(Integer::parseInt).toList();

            int nbElement = 0;
            for (int num: numbers) {
                if (winningNumbers.contains(num)) {
                    nbElement++;
                }
            }
            if (nbElement > 0) {
                result += (int) Math.pow(2, nbElement - 1);
            }
        }
        System.out.println(result);
    }

    public static void part2() {
        Scanner sc = new Scanner(System.in);

        String line;
        int result = 0;
        List<Integer> amount = new ArrayList<>();
        amount.add(1);
        int cardId = 0;
        while (true) {
            line = sc.nextLine();
            if (line.isEmpty() || line.isBlank()) {
                break;
            }

            if (amount.size() == cardId) {
                amount.add(1);
            }

            result += amount.get(cardId);

            String[] winningNumbersS = line.split(": ")[1].split(" \\| ")[0].split("\\s+");
            String[] numbersS = line.split(": ")[1].split(" \\| ")[1].split("\\s+");
            List<Integer> winningNumbers = Arrays.stream(winningNumbersS).filter(s -> !s.isBlank()).map(Integer::parseInt).toList();
            List<Integer> numbers = Arrays.stream(numbersS).filter(s -> !s.isBlank()).map(Integer::parseInt).toList();

            int nbElement = 0;
            for (int num: numbers) {
                if (winningNumbers.contains(num)) {
                    nbElement++;
                }
            }
            for (int i = cardId + 1; i < cardId + nbElement + 1; i++) {
                if (amount.size() > i) {
                    amount.set(i, amount.get(i) + amount.get(cardId));
                } else {
                    amount.add(amount.get(cardId) + 1);
                }
            }

            cardId++;
        }
        System.out.println(result);
    }
}
