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

        int nbSequences = 0;
        while (!currentPaths.stream().allMatch(path -> path.endsWith("Z"))) {
            nbSequences++;
            for (char instruction: instructions) {
                Set<String> nextPaths = new HashSet<>();

                for (String currentPath: currentPaths) {
                    nextPaths.add(instruction == 'L' ? paths.get(currentPath)[0] : paths.get(currentPath)[1]);
                }
                currentPaths = nextPaths;
            }
        }

        System.out.println(nbSequences * instructions.length);
    }
}
