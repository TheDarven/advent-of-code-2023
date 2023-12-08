package fr.thedarven.adventofcode2023;

import java.util.*;

public class Day8 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        Scanner sc = new Scanner(System.in);
        char[] instructions = sc.nextLine().toCharArray();
        sc.nextLine();
        String line;


        String currentPath = "AAA";
        Map<String, String[]> paths = new HashMap<>();
        while (!(line = sc.nextLine()).isBlank()) {
            String node = line.split(" = ")[0];
            String[] elements = line.split("\\(")[1].replaceAll("\\)", "").split(",\\s+");
            paths.put(node, new String[] { elements[0], elements[1] });
        }

        int nbSequences = 0;
        while (!currentPath.equals("ZZZ")) {
            nbSequences++;
            for (char instruction: instructions) {
                currentPath = instruction == 'L' ? paths.get(currentPath)[0] : paths.get(currentPath)[1];
            }
        }

        System.out.println(nbSequences * instructions.length);
    }

    public static void part2() {
        Scanner sc = new Scanner(System.in);
        char[] instructions = sc.nextLine().toCharArray();
        sc.nextLine();
        String line;

        Set<String> currentPaths = new HashSet<>();
        Map<String, String[]> paths = new HashMap<>();
        while (!(line = sc.nextLine()).isBlank()) {
            String node = line.split(" = ")[0];
            String[] elements = line.split("\\(")[1].replaceAll("\\)", "").split(",\\s+");
            paths.put(node, new String[] { elements[0], elements[1] });

            if (node.endsWith("A")) {
                currentPaths.add(node);
            }
        }

        List<Integer> cycleSize = new ArrayList<>();
        for (String currentPath: currentPaths) {
            int i = 0;
            String current = currentPath;
            while (!current.endsWith("Z")) {
                current = paths.get(current)[instructions[i++ % instructions.length] == 'L' ? 0 : 1];
            }
            cycleSize.add(i);
        }

        long result = cycleSize.get(0);
        for (int i = 1; i < cycleSize.size(); i++) {
            result = lcm(result, cycleSize.get(i));
        }
        System.out.println(result);
    }

    private static long gcd(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private static long lcm(long a, long b) {
        return a * (b / gcd(a, b));
    }
}
