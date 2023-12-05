package fr.thedarven.adventofcode2023;

import java.util.*;

public class Day5 {

    public static void main(String[] args) {
        part1();
    }

    public static void part1() {
        Scanner sc = new Scanner(System.in);

        Set<Long> sources = new HashSet<>(Arrays.stream(sc.nextLine().split(": ")[1].split("\\s+")).map(Long::parseLong).toList());
        String line;
        List<Long> numbers;

        sc.nextLine();
        for (int i = 0; i < 8; i++) {
            Set<Long> destinations = new HashSet<>();
            sc.nextLine();
            while (!(line = sc.nextLine()).isBlank()) {
                numbers = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
                long sourceStart = numbers.get(1);
                long sourceEnd = numbers.get(1) + numbers.get(2) - 1;

                long destinationStart = numbers.get(0);
                for (long source: new HashSet<>(sources)) {
                    if (source >= sourceStart && source <= sourceEnd) {
                        destinations.add(destinationStart + (source - sourceStart));
                        sources.remove(source);
                    }
                }
            }
            destinations.addAll(sources);
            sources = destinations;
        }
        System.out.println("\nMinimum : " + sources.stream().min(Long::compare).orElse(-1L));
    }

    public static void part2() {

    }
}

class Range {
    int start;
    int end;
    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
