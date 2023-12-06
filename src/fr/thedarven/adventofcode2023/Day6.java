package fr.thedarven.adventofcode2023;

import java.util.*;

public class Day6 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        Scanner sc = new Scanner(System.in);
        String[] splittedLine = sc.nextLine().split("Time:")[1].split("\\s+");
        List<Integer> times = Arrays.stream(splittedLine).filter(e -> !e.isBlank()).map(Integer::parseInt).toList();

        splittedLine = sc.nextLine().split("Distance:")[1].split("\\s+");
        List<Integer> distances = Arrays.stream(splittedLine).filter(e -> !e.isBlank()).map(Integer::parseInt).toList();

        int index = 0;
        int result = 1;
        for (int time: times) {
            int nbSuccess = 0;
            for (int i = 0; i < time; i++) {
                int finalDistance = (time - i) * i;
                if (finalDistance > distances.get(index)) {
                    nbSuccess++;
                }
            }
            result *= nbSuccess;
            index++;
        }
        System.out.println(result);
    }

    public static void part2() {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine().split("Time:")[1].replaceAll("\\s+", "");
        long time = Long.parseLong(line);

        line = sc.nextLine().split("Distance:")[1].replaceAll("\\s+", "");
        long distance = Long.parseLong(line);

        long result = 0L;
        for (long i = 0L; i < time; i++) {
            long finalDistance = (time - i) * i;
            if (finalDistance > distance) {
                result++;
            }
        }
        System.out.println(result);
    }
}
