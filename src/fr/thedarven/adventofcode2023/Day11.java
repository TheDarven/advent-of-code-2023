package fr.thedarven.adventofcode2023;

import java.util.*;

public class Day11 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        Scanner sc = new Scanner(System.in);
        Long result = 0L;

        List<String> space = new ArrayList<>();
        String line;
        while (!(line = sc.nextLine()).isBlank()) {
            space.add(line);
            if (!line.contains("#")) {
                space.add(line);
            }
        }
        int nbColumn = space.get(0).length();
        for (int column = 0; column < nbColumn; column++) {
            boolean hasGalaxy = false;
            for (String s : space) {
                if (s.charAt(column) == '#') {
                    hasGalaxy = true;
                    break;
                }
            }
            if (!hasGalaxy) {
                for (int row = 0; row < space.size(); row++) {
                    space.set(row, space.get(row).substring(0, column) + "." + space.get(row).substring(column));
                }
                nbColumn++;
                column++;
            }
        }

        List<int[]> galaxies = new ArrayList<>();
        for (int row = 0; row < space.size(); row++) {
            for (int column = 0; column < space.get(row).length(); column++) {
                if (space.get(row).charAt(column) == '#') {
                    galaxies.add(new int[]{ column, row });
                }
            }
        }

        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                int y = Math.abs(galaxies.get(i)[0] - galaxies.get(j)[0]);
                int x = Math.abs(galaxies.get(i)[1] - galaxies.get(j)[1]);
                result += x + y;
            }
        }

        System.out.println(result);
    }

    public static void part2() {
        Scanner sc = new Scanner(System.in);
        Long result = 0L;

        Set<Integer> emptyRows = new HashSet<>();
        Set<Integer> emptyColumns = new HashSet<>();

        List<String> space = new ArrayList<>();
        String line;
        int row = 0;
        while (!(line = sc.nextLine()).isBlank()) {
            space.add(line);
            if (!line.contains("#")) {
                emptyRows.add(row);
            }
            row++;
        }
        for (int column = 0; column < space.get(0).length(); column++) {
            boolean hasGalaxy = false;
            for (String s : space) {
                if (s.charAt(column) == '#') {
                    hasGalaxy = true;
                    break;
                }
            }
            if (!hasGalaxy) {
                emptyColumns.add(column);
            }
        }

        List<int[]> galaxies = new ArrayList<>();
        for (row = 0; row < space.size(); row++) {
            for (int column = 0; column < space.get(row).length(); column++) {
                if (space.get(row).charAt(column) == '#') {
                    galaxies.add(new int[]{ column, row });
                }
            }
        }

        for (int i = 0; i < galaxies.size() - 1; i++) {
            for (int j = i + 1; j < galaxies.size(); j++) {
                int fromRow = Math.min(galaxies.get(i)[1], galaxies.get(j)[1]);
                int toRow = Math.max(galaxies.get(i)[1], galaxies.get(j)[1]);
                for (row = fromRow; row < toRow; row++) {
                    result += emptyRows.contains(row) ? 1_000_000 : 1;
                }

                int fromColumn = Math.min(galaxies.get(i)[0], galaxies.get(j)[0]);
                int toColumn = Math.max(galaxies.get(i)[0], galaxies.get(j)[0]);
                for (int column = fromColumn; column < toColumn; column++) {
                    result += emptyColumns.contains(column) ? 1_000_000 : 1;
                }
            }
        }

        System.out.println(result);
    }
}
