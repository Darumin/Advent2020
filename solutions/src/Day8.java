import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8 {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("input8.txt"))) {
            List<String> instructions = new ArrayList<>();
            while(in.hasNextLine()) instructions.add(in.nextLine());

            System.out.println(part1(instructions).ACC);
            System.out.println(part2(instructions));
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    public static Info part1(List<String> instructions) {
        int part1 = 0;
        List<Integer> repeats = new ArrayList<>();

        for (int i = 0; i < instructions.size(); i++) {
            if(repeats.contains(i)) break;
            else repeats.add(i);

            String[] tokens = instructions.get(i).split(" ");
            String code = tokens[0];
            int value = Integer.parseInt(tokens[1]);

            switch (code) {
                case "jmp":
                    i += value - 1;
                    break;
                case "acc":
                    part1 += value;
                    break;
                case "nop":
                default:
                    break;
            }
        }

       return new Info(part1, repeats.get(repeats.size() - 1));
    }

    public static int part2(List<String> instructions) {
        for (int i = 0; i < instructions.size(); i++) {
            String operation = instructions.get(i);

            // Flip
            if(!flipCode(operation, instructions, i)) continue;
            Info inf = part1(instructions);

            // If has a normal exit code
            if(inf.LAST_CODE == instructions.size() - 1) return inf.ACC;

            // Else flip back
            operation = instructions.get(i);
            flipCode(operation, instructions, i);
        }

        return 0;
    }

    public static boolean flipCode(String operation, List<String> instructions, int i) {
        String[] tokens = operation.split(" ");
        String command = tokens[0];
        String value = tokens[1];

        switch (command) {
            case "jmp":
                instructions.set(i, "nop " + value);
                return true;
            case "nop":
                instructions.set(i, "jmp " + value);
                return true;
            case "acc":
            default:
                return false;
        }
    }

    static class Info {
        final int ACC;
        final int LAST_CODE;

        public Info(int acc, int lastInstruction) {
            this.ACC = acc;
            this.LAST_CODE = lastInstruction;
        }
    }

}
