package fr.thedarven.adventofcode2023;

import java.util.*;

public class Day5 {

    public static void main(String[] args) {
        part2();
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
        Scanner sc = new Scanner(System.in);

        List<Long> seeds = new ArrayList<>(Arrays.stream(sc.nextLine().split(": ")[1].split("\\s+")).map(Long::parseLong).toList());
        Set<Range> sources = new HashSet<>();
        for (int i = 0; i < seeds.size(); i+=2) {
            sources.add(new Range(seeds.get(i), seeds.get(i) + seeds.get(i + 1) - 1));
        }
        sources = mergeAll(sources);

        String line;
        List<Long> numbers;
        sc.nextLine();
        for (int i = 0; i < 8; i++) {
            Set<Range> destinations = new HashSet<>();
            sc.nextLine();
            while (!(line = sc.nextLine()).isBlank()) {
                numbers = Arrays.stream(line.split("\\s+")).map(Long::parseLong).toList();
                Range source = new Range(numbers.get(1), numbers.get(1) + numbers.get(2) - 1);

                long destinationStart = numbers.get(0);
                for (Range range: new HashSet<>(sources)) {
                    List<Range> ranges = new Range(range.start, range.end).intersection(source);
                    if (ranges.size() == 1) {
                        Range newRange = new Range(destinationStart + (ranges.get(0).start - source.start), destinationStart + (ranges.get(0).end - source.start));
                        destinations.add(newRange);
                        sources.remove(range);
                        if (range.start < ranges.get(0).start) {
                            sources.add(new Range(range.start, ranges.get(0).start - 1));
                        }
                        if (range.end > ranges.get(0).end) {
                            sources.add(new Range(ranges.get(0).end + 1, range.end));
                        }
                    }
                }
            }
            destinations.addAll(sources);
            sources = mergeAll(destinations);
        }
        System.out.println("\nMinimum : " + sources.stream().map(s -> s.start).min(Long::compare).orElse(-1L));
    }

    private static Set<Range> mergeAll(Set<Range> ranges) {
        Stack<Range> toBeMerge = new Stack<>();
        toBeMerge.addAll(ranges);

        Set<Range> result = new HashSet<>();
        while (!toBeMerge.isEmpty()) {
            boolean isMerge = false;
            Range toBeMergeRange = toBeMerge.pop();
            for (Range range: result) {
                List<Range> mergeResult = toBeMergeRange.union(range);
                if (mergeResult.size() == 1) {
                    result.remove(range);
                    toBeMerge.add(mergeResult.get(0));
                    isMerge = true;
                    break;
                }
            }
            if (!isMerge) {
                result.add(toBeMergeRange);
            }
        }
        return result;
    }
}

class Range {
    Long start;
    Long end;
    public Range(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    boolean isSuperimposed(Range range) {
        return this.end >= range.start && range.end >= this.start;
    }

    List<Range> union(Range range) {
        if (!isSuperimposed(range)) {
            return Arrays.asList(this, range);
        }

        this.start = Math.min(this.start, range.start);
        this.end = Math.max(this.end, range.end);
        return List.of(this);
    }

    List<Range> intersection(Range range) {
        if (!isSuperimposed(range)) {
            return Arrays.asList(this, range);
        }

        this.start = Math.max(this.start, range.start);
        this.end = Math.min(this.end, range.end);
        return List.of(this);
    }

    public String toString() {
        return "[" + this.start + ", " + this.end + "]";
    }
}
