package fr.thedarven.adventofcode2023;

import java.util.*;

public class Day3 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        Scanner sc = new Scanner(System.in);

        List<String> lines = new ArrayList<>();
        Map<Integer, Map<Integer, Element>> numbers = new HashMap<>();
        Set<Element> alreadyUsedNumbers = new HashSet<>();

        int lineIndex = 0;
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty() || line.isBlank()) {
                break;
            }
            lines.add(line);


            char[] splittedLine = line.toCharArray();
            for (int startX = 0; startX < splittedLine.length; startX++) {
                int asciiValue = splittedLine[startX];
                if (asciiValue < 48 || asciiValue > 57) {
                    continue;
                }

                int endX = startX;
                for (; endX < splittedLine.length; endX++) {
                    try {
                        Integer.parseInt(line.substring(startX, endX + 1));
                    } catch (Exception e) {
                        break;
                    }
                }
                endX = Math.min(endX, splittedLine.length);

                int number = Integer.parseInt(line.substring(startX, endX));

                Element element = new Element(number);
                for (int i = startX; i < endX; i++) {
                    if (!numbers.containsKey(lineIndex)) {
                        numbers.put(lineIndex, new HashMap<>());
                    }
                    numbers.get(lineIndex).put(i, element);
                }
                startX = endX;
            }
            lineIndex++;
        }

        int[][] arround = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}};
        int result = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                int asciiValue = line.charAt(j);
                if ((asciiValue >= 48 && asciiValue <= 57) || line.charAt(j) == '.') {
                    continue;
                }

                for (int k = 0; k < arround.length; k++) {
                    int x = j + arround[k][0];
                    int y = i + arround[k][1];
                    if (x < 0 || x >= line.length() || y < 0 || y >= lines.size()) {
                        continue;
                    }

                    if (!numbers.containsKey(y) || !numbers.get(y).containsKey(x)) {
                        continue;
                    }

                    Element element = numbers.get(y).get(x);
                    if (alreadyUsedNumbers.contains(element)) {
                        continue;
                    }

                    alreadyUsedNumbers.add(element);
                    result += element.value;
                }
            }
        }
        System.out.println(result);
    }

    public static void part2() {
        Scanner sc = new Scanner(System.in);

        List<String> lines = new ArrayList<>();
        Map<Integer, Map<Integer, Element>> numbers = new HashMap<>();
        int lineIndex = 0;
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty() || line.isBlank()) {
                break;
            }
            lines.add(line);


            char[] splittedLine = line.toCharArray();
            for (int startX = 0; startX < splittedLine.length; startX++) {
                int asciiValue = splittedLine[startX];
                if (asciiValue < 48 || asciiValue > 57) {
                    continue;
                }

                int endX = startX;
                for (; endX < splittedLine.length; endX++) {
                    try {
                        Integer.parseInt(line.substring(startX, endX + 1));
                    } catch (Exception e) {
                        break;
                    }
                }
                endX = Math.min(endX, splittedLine.length);

                int number = Integer.parseInt(line.substring(startX, endX));

                Element element = new Element(number);
                for (int i = startX; i < endX; i++) {
                    if (!numbers.containsKey(lineIndex)) {
                        numbers.put(lineIndex, new HashMap<>());
                    }
                    numbers.get(lineIndex).put(i, element);
                }
                startX = endX;
            }
            lineIndex++;
        }

        int[][] arround = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}};
        int result = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            for (int j = 0; j < line.length(); j++) {
                if (line.charAt(j) != '*') {
                    continue;
                }

                Set<Element> elements = new HashSet<>();
                for (int k = 0; k < arround.length; k++) {
                    int x = j + arround[k][0];
                    int y = i + arround[k][1];
                    if (x < 0 || x >= line.length() || y < 0 || y >= lines.size()) {
                        continue;
                    }

                    if (!numbers.containsKey(y) || !numbers.get(y).containsKey(x)) {
                        continue;
                    }

                    elements.add(numbers.get(y).get(x));
                }

                if (elements.size() == 2) {
                    int v = 1;
                    for (Element element: elements) {
                        v *= element.value;
                    }
                    result += v;
                }
            }
        }
        System.out.println(result);
    }
}

class Element {
    int value;
    public Element(int value) {
        this.value = value;
    }
}