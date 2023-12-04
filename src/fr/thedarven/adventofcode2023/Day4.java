package fr.thedarven.adventofcode2023;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day4 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        Scanner sc = new Scanner(System.in);

        Map<String, Integer> filters = new HashMap<>();
        filters.put("red", 12);
        filters.put("green", 13);
        filters.put("blue", 14);

        String line;
        String[] sets, elements, value;
        int result = 0;
        int gameNumber = 1;
        while (true) {
            boolean isGamePossible = true;
            line = sc.nextLine();
            if (line.isEmpty()) {
                break;
            }
            sets = line.split(": ")[1].split("; ");
            for (String set: sets) {
                elements = set.split(", ");
                for (String element: elements) {
                    value = element.split(" ");
                    if (filters.get(value[1]) < Integer.parseInt(value[0])) {
                        isGamePossible = false;
                        break;
                    }
                }
                if (!isGamePossible) {
                    break;
                }
            }
            if (isGamePossible) {
                result += gameNumber;
            }
            gameNumber++;
        }
        System.out.println(result);
    }

    public static void part2() {
        Scanner sc = new Scanner(System.in);

        String line;
        String[] sets, elements, value;
        int result = 0, nbRed, nbGreen, nbBlue;
        while (true) {
            nbRed = 0; nbGreen = 0; nbBlue = 0;
            line = sc.nextLine();
            if (line.isEmpty()) {
                break;
            }
            sets = line.split(": ")[1].split("; ");
            for (String set: sets) {
                elements = set.split(", ");
                for (String element: elements) {
                    value = element.split(" ");
                    switch (value[1]) {
                        case "red":
                            nbRed = Math.max(nbRed, Integer.parseInt(value[0]));
                            break;
                        case "green":
                            nbGreen = Math.max(nbGreen, Integer.parseInt(value[0]));
                            break;
                        case "blue":
                            nbBlue = Math.max(nbBlue, Integer.parseInt(value[0]));
                            break;
                    }
                }
            }
            result += nbRed * nbGreen * nbBlue;
        }
        System.out.println(result);
    }
}
