package fr.thedarven.adventofcode2023;

import java.util.*;

public class Day7 {

    public static void main(String[] args) {
        part2();
    }

    public static void part1() {
        Scanner sc = new Scanner(System.in);
        String line;

        List<Hand> hands = new ArrayList<>();
        while (!(line = sc.nextLine()).isBlank()) {
            String cards = line.split("\\s+")[0];
            Map<Character, Integer> elements = new HashMap<>();
            for (char c: cards.toCharArray()) {
                if (elements.containsKey(c)) {
                    elements.put(c, elements.get(c) + 1);
                } else {
                    elements.put(c, 1);
                }
            }
            int power;
            if (elements.size() == 1) {
                power = 1;
            } else if (elements.size() == 2) {
                power = elements.values().stream().anyMatch(e -> e == 4) ? 2 : 3;
            } else if (elements.size() == 3) {
                power = elements.values().stream().anyMatch(e -> e == 3) ? 4 : 5;
            } else {
                power = elements.size() + 2;
            }

            hands.add(new Hand(cards, Integer.parseInt(line.split("\\s+")[1]), power));
        }
        hands.sort((o1, o2) -> {
            if (o2.power < o1.power) {
                return 1;
            }
            if (o2.power > o1.power) {
                return -1;
            }
            List<Character> characters = Arrays.asList('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2');

            for (int i = 0; i < 5; i++) {
                int o2CardPower = characters.indexOf(o2.cards.charAt(i));
                int o1CardPower = characters.indexOf(o1.cards.charAt(i));
                if (o2CardPower == o1CardPower) {
                    continue;
                }
                return o1CardPower < o2CardPower ? -1 : 1;
            }
            return 0;
        });

        long result = 0;
        for (int i = 0; i < hands.size(); i++) {
            result += (hands.size() - i) * (long) hands.get(i).bid;
        }
        System.out.println(result);
    }

    public static void part2() {
        Scanner sc = new Scanner(System.in);
        String line;

        List<Hand> hands = new ArrayList<>();
        while (!(line = sc.nextLine()).isBlank()) {
            String cards = line.split("\\s+")[0];
            Map<Character, Integer> elements = new HashMap<>();
            int nbJoker = 0;
            for (char c: cards.toCharArray()) {
                if (c == 'J') {
                    nbJoker++;
                    continue;
                }

                if (elements.containsKey(c)) {
                    elements.put(c, elements.get(c) + 1);
                } else {
                    elements.put(c, 1);
                }
            }
            int power;
            int finalNbJoker = nbJoker;
            if (elements.size() <= 1) {
                power = 1;
            } else if (elements.size() == 2) {
                power = elements.values().stream().anyMatch(e -> e == 4 - finalNbJoker) ? 2 : 3;
            } else if (elements.size() == 3) {
                power = elements.values().stream().anyMatch(e -> e == 3 - finalNbJoker) ? 4 : 5;
            } else {
                power = elements.size() + 2;
            }

            hands.add(new Hand(cards, Integer.parseInt(line.split("\\s+")[1]), power));
        }
        hands.sort((o1, o2) -> {
            if (o2.power < o1.power) {
                return 1;
            }
            if (o2.power > o1.power) {
                return -1;
            }
            List<Character> characters = Arrays.asList('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J');

            for (int i = 0; i < 5; i++) {
                int o2CardPower = characters.indexOf(o2.cards.charAt(i));
                int o1CardPower = characters.indexOf(o1.cards.charAt(i));
                if (o2CardPower == o1CardPower) {
                    continue;
                }
                return o1CardPower < o2CardPower ? -1 : 1;
            }
            return 0;
        });

        long result = 0;
        for (int i = 0; i < hands.size(); i++) {
            result += (hands.size() - i) * (long) hands.get(i).bid;
        }
        System.out.println(result);
    }
}

class Hand {
    String cards;
    int bid;
    int power;

    public Hand(String cards, int bid, int power) {
        this.cards = cards;
        this.bid = bid;
        this.power = power;
    }
}
