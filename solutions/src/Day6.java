import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6 {
    public static void main(String[] args) {
        try(Scanner in = new Scanner(new File("input6.txt"))){
            // Because of the way this is designed, only one of the functions will work. Turn one off
            // to see results of the other.
            part1(in);
            part2(in);
        }
        catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    public static void part1(Scanner in) {
        int yesCount = 0;
        int groupYes = 0;
        StringBuilder previous = new StringBuilder();

        while(in.hasNextLine()) {
            String person = in.nextLine();

            if (person.isEmpty()) {
                yesCount += groupYes;
                groupYes = 0;
                previous = new StringBuilder();
                continue;
            }

            for(int i = 0; i < person.length(); i++) {
                if(previous.toString().indexOf(person.charAt(i)) < 0) { groupYes += 1; }
            }
            previous.append(person);
        }

        System.out.println(yesCount);
    }

    public static void part2(Scanner in) {
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
}

