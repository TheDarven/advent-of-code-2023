package fr.thedarven.adventofcode2023;

import java.util.*;

public class Day9 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        Scanner sc = new Scanner(System.in);
        Long result = 0L;

        String line;
        while (!(line = sc.nextLine()).isBlank()) {
            List<List<Long>> history = new ArrayList<>();
            history.add(new ArrayList<>(Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList()));
            while (!history.get(history.size() - 1).stream().allMatch(e -> e == 0)) {
                List<Long> previousHistoryLine = history.get(history.size() - 1);
                List<Long> historyLine = new ArrayList<>();
                for (int i = 0; i < previousHistoryLine.size() - 1; i++) {
                    historyLine.add(previousHistoryLine.get(i + 1) - previousHistoryLine.get(i));
                }
                history.add(historyLine);
            }

            for (int i = history.size() - 1; i > 0; i--) {
                List<Long> previousHistoryLine = history.get(i - 1);
                List<Long> historyLine = history.get(i);
                previousHistoryLine.add(previousHistoryLine.get(previousHistoryLine.size() - 1) + historyLine.get(historyLine.size() - 1));
            }
            result += history.get(0).get(history.get(0).size() - 1);
        }

        System.out.println(result);
    }

    public static void part2() {
        Scanner sc = new Scanner(System.in);
        Long result = 0L;

        String line;
        while (!(line = sc.nextLine()).isBlank()) {
            List<List<Long>> history = new ArrayList<>();
            history.add(new ArrayList<>(Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList()));
            while (!history.get(history.size() - 1).stream().allMatch(e -> e == 0)) {
                List<Long> previousHistoryLine = history.get(history.size() - 1);
                List<Long> historyLine = new ArrayList<>();
                for (int i = 0; i < previousHistoryLine.size() - 1; i++) {
                    historyLine.add(previousHistoryLine.get(i + 1) - previousHistoryLine.get(i));
                }
                history.add(historyLine);
            }

            for (int i = history.size() - 1; i > 0; i--) {
                List<Long> previousHistoryLine = history.get(i - 1);
                List<Long> historyLine = history.get(i);
                previousHistoryLine.add(0, previousHistoryLine.get(0) - historyLine.get(0));
            }
            result += history.get(0).get(0);
        }

        System.out.println(result);
    }
}
