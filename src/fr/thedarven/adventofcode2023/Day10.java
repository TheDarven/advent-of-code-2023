package fr.thedarven.adventofcode2023;

import java.util.*;

public class Day10 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        Scanner sc = new Scanner(System.in);
        String line;
        List<String> game = new ArrayList<>();
        int startX = 0, startY = 0;
        while (!(line = sc.nextLine()).isBlank()) {
            if (line.indexOf('S') > -1) {
                startX = line.indexOf('S');
                startY = game.size();
            }
            game.add(line);
        }

        List<Character> top = Arrays.asList('|', 'F', '7');
        List<Character> bottom = Arrays.asList('|', 'J', 'L');
        List<Character> left = Arrays.asList('-', 'J', '7');
        List<Character> right = Arrays.asList('-', 'F', 'L');

        int x = startX, y = startY;
        Direction from = Direction.TOP;
        if (startY > 0 && top.contains(game.get(startY - 1).charAt(startX))) {
            x = startX;
            y = startY - 1;
            from = Direction.BOTTOM;
        } else if (startY < game.size() - 1 && bottom.contains(game.get(startY + 1).charAt(startX))) {
            x = startX;
            y = startY + 1;
            from = Direction.TOP;
        } else if (startX > 0 && left.contains(game.get(startY).charAt(startX - 1))) {
            x = startX - 1;
            y = startY;
            from = Direction.RIGHT;
        } else if (startX < game.get(0).length() - 1 && right.contains(game.get(startY).charAt(startX + 1))) {
            x = startX + 1;
            y = startY;
            from = Direction.LEFT;
        }

        int i = 1;
        while (x != startX || y != startY) {
            switch (game.get(y).charAt(x)) {
                case '|':
                    if (from == Direction.TOP) y++;
                    else y--;
                    break;
                case '-':
                    if (from == Direction.LEFT) x++;
                    else x--;
                    break;
                case 'L':
                    if (from == Direction.TOP) {
                        x++;
                        from = Direction.LEFT;
                    } else {
                        y--;
                        from = Direction.BOTTOM;
                    }
                    break;
                case 'J':
                    if (from == Direction.TOP) {
                        x--;
                        from = Direction.RIGHT;
                    } else {
                        y--;
                        from = Direction.BOTTOM;
                    }
                    break;
                case '7':
                    if (from == Direction.BOTTOM) {
                        x--;
                        from = Direction.RIGHT;
                    } else {
                        y++;
                        from = Direction.TOP;
                    }
                    break;
                case 'F':
                    if (from == Direction.BOTTOM) {
                        x++;
                        from = Direction.LEFT;
                    } else {
                        y++;
                        from = Direction.TOP;
                    }
                    break;
            }
            i++;
        }
        System.out.println(i / 2);
    }

    public static void part2() {
        Scanner sc = new Scanner(System.in);
        String line;
        List<String> game = new ArrayList<>();
        int startX = 0, startY = 0;
        while (!(line = sc.nextLine()).isBlank()) {
            if (line.indexOf('S') > -1) {
                startX = line.indexOf('S');
                startY = game.size();
            }
            game.add(line);
        }

        List<Character> top = Arrays.asList('|', 'F', '7');
        List<Character> bottom = Arrays.asList('|', 'J', 'L');
        List<Character> left = Arrays.asList('-', 'J', '7');
        List<Character> right = Arrays.asList('-', 'F', 'L');

        Map<Integer, Set<Integer>> validPipes = new HashMap<>();

        int x = startX, y = startY;
        Direction from = Direction.TOP;
        if (startY > 0 && top.contains(game.get(startY - 1).charAt(startX))) {
            x = startX;
            y = startY - 1;
            from = Direction.BOTTOM;
        } else if (startY < game.size() - 1 && bottom.contains(game.get(startY + 1).charAt(startX))) {
            x = startX;
            y = startY + 1;
            from = Direction.TOP;
        } else if (startX > 0 && left.contains(game.get(startY).charAt(startX - 1))) {
            x = startX - 1;
            y = startY;
            from = Direction.RIGHT;
        } else if (startX < game.get(0).length() - 1 && right.contains(game.get(startY).charAt(startX + 1))) {
            x = startX + 1;
            y = startY;
            from = Direction.LEFT;
        }

        while (x != startX || y != startY) {
            if (!validPipes.containsKey(y)) {
                validPipes.put(y, new HashSet<>());
            }
            validPipes.get(y).add(x);

            switch (game.get(y).charAt(x)) {
                case '|':
                    if (from == Direction.TOP) y++;
                    else y--;
                    break;
                case '-':
                    if (from == Direction.LEFT) x++;
                    else x--;
                    break;
                case 'L':
                    if (from == Direction.TOP) {
                        x++;
                        from = Direction.LEFT;
                    } else {
                        y--;
                        from = Direction.BOTTOM;
                    }
                    break;
                case 'J':
                    if (from == Direction.TOP) {
                        x--;
                        from = Direction.RIGHT;
                    } else {
                        y--;
                        from = Direction.BOTTOM;
                    }
                    break;
                case '7':
                    if (from == Direction.BOTTOM) {
                        x--;
                        from = Direction.RIGHT;
                    } else {
                        y++;
                        from = Direction.TOP;
                    }
                    break;
                case 'F':
                    if (from == Direction.BOTTOM) {
                        x++;
                        from = Direction.LEFT;
                    } else {
                        y++;
                        from = Direction.TOP;
                    }
                    break;
            }
        }
        if (!validPipes.containsKey(startY)) {
            validPipes.put(startY, new HashSet<>());
        }
        validPipes.get(startY).add(startX);

        // Remove invalid pipes
        for (y = 0; y < game.size(); y++) {
            for (x = 0; x < game.get(0).length(); x++) {
                if (!validPipes.containsKey(y) || !validPipes.get(y).contains(x)) {
                    game.set(y, game.get(y).substring(0, x) + "." + game.get(y).substring(x + 1));
                }
            }
        }

        for (y = 0; y < game.size(); y++) {
            for (x = 0; x < game.get(0).length() - 1; x++) {
                char tile = game.get(y).charAt(x);
                char rightTile = game.get(y).charAt(x + 1);
                if ((tile == '|' || tile == '7' || tile == 'J' || tile == 'S') && (rightTile == '|' || rightTile == 'L' ||rightTile == 'F' || tile == 'S')) {
                    addColumn(game, x);
                }
            }
        }

        for (x = 0; x < game.get(0).length(); x++) {
            for (y = 0; y < game.size() - 1; y++) {
                char tile = game.get(y).charAt(x);
                char bottomTile = game.get(y + 1).charAt(x);
                if ((tile == '-' || tile == 'J' || tile == 'L' || tile == 'S') && (bottomTile == '-' || bottomTile == 'F' ||bottomTile == '7' || tile == 'S')) {
                    addRow(game, y);
                }
            }
        }

        replaceOutsideTile(game);

        int result = 0;
        for (String gameLine: game) {
            for (char c: gameLine.toCharArray()) {
                if (c == '.') {
                    result++;
                }
            }
        }
        System.out.println(result);
    }

    private static void addColumn(List<String> game, int column) {
        for (int y = 0; y < game.size(); y++) {
            char tile = game.get(y).charAt(column);
            char rightTile = game.get(y).charAt(column + 1);
            char newTile = '*';
            if (tile == '-' || tile == 'F' || tile == 'L') {
                newTile = '-';
            } else if (tile == 'S' && (rightTile == '-' || rightTile == '7' || rightTile == 'J')) {
                newTile = '-';
            }
            game.set(y, game.get(y).substring(0, column + 1) + newTile + game.get(y).substring(column + 1));
        }
    }

    private static void addRow(List<String> game, int row) {
        StringBuilder newRow = new StringBuilder();
        for (int x = 0; x < game.get(0).length(); x++) {
            char tile = game.get(row).charAt(x);
            char bottomTile = game.get(row + 1).charAt(x);
            char newTile = '*';
            if (tile == '|' || tile == 'F' || tile == '7') {
                newTile = '|';
            } else if (tile == 'S' && (bottomTile == '|' || bottomTile == 'J' || bottomTile == 'L')) {
                newTile = '|';
            }
            newRow.append(newTile);
        }
        game.add(row + 1, newRow.toString());
    }

    private static void replaceOutsideTile(List<String> game) {
        Stack<int[]> coords = new Stack<>();
        for (int y = 0; y < game.size(); y++) {
            if (game.get(y).charAt(0) == '.' || game.get(y).charAt(0) == '*') {
                coords.add(new int[]{ 0, y });
            }
            if (game.get(y).charAt(game.get(y).length() - 1) == '.' || game.get(y).charAt(game.get(y).length() - 1) == '*') {
                coords.add(new int[]{ game.get(y).length() - 1, y });
            }
        }
        for (int x = 1; x < game.get(0).length() - 1; x++) {
            if (game.get(0).charAt(x) == '.' || game.get(0).charAt(x) == '*') {
                coords.add(new int[]{ 0, x });
            }
            if (game.get(game.size() - 1).charAt(x) == '.' || game.get(game.size() - 1).charAt(x) == '*') {
                coords.add(new int[]{ x, game.size() - 1 });
            }
        }
        while (!coords.isEmpty()) {
            int[] coord = coords.pop();
            if (coord[1] < 0 || coord[1] >= game.size() || coord[0] < 0 || coord[0] >= game.get(0).length()) {
                continue;
            }
            if (game.get(coord[1]).charAt(coord[0]) != '.' && game.get(coord[1]).charAt(coord[0]) != '*') {
                continue;
            }
            game.set(coord[1], game.get(coord[1]).substring(0, coord[0]) + "o" + game.get(coord[1]).substring(coord[0] + 1));
            coords.add(new int[]{coord[0] + 1, coord[1]});
            coords.add(new int[]{coord[0] - 1, coord[1]});
            coords.add(new int[]{coord[0], coord[1] + 1});
            coords.add(new int[]{coord[0], coord[1] - 1});
        }
    }
}

enum Direction {
    LEFT,
    RIGHT,
    TOP,
    BOTTOM
}
