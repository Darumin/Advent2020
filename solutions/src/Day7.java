import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day7 {
    private static int part1 = 0;
    private static int part2 = 0;

    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("input7.txt"))) {
            List<String> inputs = new ArrayList<>();
            while(in.hasNextLine()) inputs.add(in.nextLine());
            part1(inputs);
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    public static void part1(List<String> rules) {
        HashMap<String, String> bags = new HashMap<>();

        for(String rule : rules) {
            String[] split = rule.split(" bags contain ");
            bags.put(split[0], split[1]);
        }

        for(String contents : bags.values()) part1 += recursiveCheckBag(0, bags, contents);
        System.out.println(part1);

        countRecursions(bags, bags.get("shiny gold"));
        System.out.println("Part 2 => " + --part2);
        System.out.println("Part 2 => " + countBags(bags, "shiny gold"));
    }

    public static int recursiveCheckBag(int count, HashMap<String, String> bags, String contents) {
        String[] bagsToCheck = contents.split(", ");
        for (String s : bagsToCheck) {
            String[] split = s.split(" ");
            String identifier = split[1] + " " + split[2];
            if (identifier.equals("shiny gold")) return 1;
            if (bags.get(identifier) != null) count = recursiveCheckBag(count, bags, bags.get(identifier));
        }

        return count;
    }

    public static void countRecursions(HashMap<String, String> bags, String contents) {
        String[] bagsToCheck = contents.split(", ");
        part2 += 1;

        for (String s : bagsToCheck) {
            String[] split = s.split(" ");
            String identifier = split[1] + " " + split[2];
            if (bags.get(identifier) == null) return;
            int number = !(split[0]).equals("no") ? Integer.parseInt(split[0]) : 0;
            for(int i = 0; i < number; i++) countRecursions(bags, bags.get(identifier));
        }
    }

    public static int countBags(HashMap<String, String> bags, String bagType) {
        int count = 0;

        if (bags.get(bagType) != null) {
            for (String s : bags.get(bagType).split(", ")) {
                String[] tokenSplit = s.split(" ");
                String newBagType = tokenSplit[1] + " " + tokenSplit[2];
                int numberOfBags = !(tokenSplit[0].equals("no")) ? Integer.parseInt(tokenSplit[0]) : 0;
                count += numberOfBags * (countBags(bags, newBagType) + 1);
            }
        }

        return count;
    }
}
