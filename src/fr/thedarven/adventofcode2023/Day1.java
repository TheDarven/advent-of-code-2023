package fr.thedarven.adventofcode2023;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day1 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        Scanner sc = new Scanner(System.in);

        char[] characters;
        int result = 0;
        while (true) {
            characters = sc.nextLine().toCharArray();
            if (characters.length == 0) {
                break;
            }

            boolean isFirstFound = false;
            boolean isLastFound = false;
            int first = 0, last = 0;
            for (char c: characters) {
                int intChar = (int) c;
                if (intChar >= 48 && intChar <= 57) {
                    int num = intChar - 48;
                    if (!isFirstFound) {
                        isFirstFound = true;
                        first = num;
                    } else {
                        isLastFound = true;
                        last = num;
                    }
                }
            }
            if (isLastFound) {
                result += (first * 10) + last;
            } else {
                result += first + first * 10;
            }
        }
        System.out.println(result);
    }

    public static void part2() {
        Scanner sc = new Scanner(System.in);

        Map<String, Integer> elements = new HashMap<>();
        elements.put("1", 1);
        elements.put("2", 2);
        elements.put("3", 3);
        elements.put("4", 4);
        elements.put("5", 5);
        elements.put("6", 6);
        elements.put("7", 7);
        elements.put("8", 8);
        elements.put("9", 9);
        elements.put("one", 1);
        elements.put("two", 2);
        elements.put("three", 3);
        elements.put("four", 4);
        elements.put("five", 5);
        elements.put("six", 6);
        elements.put("seven", 7);
        elements.put("eight", 8);
        elements.put("nine", 9);

        String line;
        int result = 0;
        while (true) {
            line = sc.nextLine();
            if (line.isEmpty()) {
                break;
            }

            int firstIndex = line.length();
            int lastIndex = -1;
            int first = 0, last = 0;
            for (String e: elements.keySet()) {
                int index = line.indexOf(e);
                if (index != -1) {
                    if (index < firstIndex) {
                        firstIndex = index;
                        first = elements.get(e);
                    }
                }

                index = line.lastIndexOf(e);
                if (index != -1) {
                    if (index > lastIndex) {
                        lastIndex = index;
                        last = elements.get(e);
                    }
                }
            }
            result += (first * 10) + last;
        }
        System.out.println(result);
    }
}
