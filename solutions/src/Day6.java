import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("input6.txt"))) {
            part1(in);
            oldPart2(in);
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    public static void part1(Scanner in) {
        int yesCount = 0;
        StringBuilder previous = new StringBuilder();
        Set<Character> answers = new HashSet<>();

        while (in.hasNextLine()) {
            String person = in.nextLine();

            if (person.isEmpty()) {
                for (int i = 0; i < previous.length(); i++) answers.add(previous.charAt(i));
                yesCount += answers.size();
                answers = new HashSet<>();
                previous = new StringBuilder();
                continue;
            }

            previous.append(person);
        }

        System.out.println(yesCount);
    }

    public static void oldPart2(Scanner in) {
        int yesCount = 0;
        int groupCount = 0;
        StringBuilder previous = new StringBuilder();

        while(in.hasNextLine()) {
            String person = in.nextLine();

            if (person.isEmpty()) {
                HashMap<Character, Integer> hm = new HashMap<>();
                for(int i = 0; i < previous.length(); i++) {
                    char key = previous.charAt(i);
                    int value = 0;
                    if(!(hm.get(key) == null)) value = hm.get(key);
                    hm.put(key, value + 1);
                }

                for(int value : hm.values()) if(value == groupCount) yesCount += 1;
                groupCount = 0;
                previous = new StringBuilder();
                continue;
            }

            groupCount += 1;
            previous.append(person);
        }

        System.out.println(yesCount);
    }

    public static void part2(Scanner in) {
        int yesCount = 0;
        Set<Character> answers = new HashSet<>();
        Set<Character> previousAnswers = new HashSet<>();

        while (in.hasNextLine()) {
            String person = in.nextLine();

            if (person.isEmpty()) {
                yesCount += previousAnswers.size();
                System.out.println(previousAnswers);
                previousAnswers.clear();
                answers.clear();
                continue;
            }

            if (previousAnswers.isEmpty()) {
                for (int i = 0; i < person.length(); i++) previousAnswers.add(person.charAt(i));
            } else {
                for (int i = 0; i < person.length(); i++) answers.add(person.charAt(i));
                previousAnswers.retainAll(answers);
                answers.clear();
            }
        }

        System.out.println(yesCount);
    }
}

