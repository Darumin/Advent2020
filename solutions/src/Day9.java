import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day9 {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(new File("input9.txt"))) {
            var list = fillList(in);
            var preamble = getPreamble(list);
            var invalid = getInvalid(preamble, list);
            var info = getContiguousList(list, invalid);

            System.out.println("Part 1 -> " + invalid);
            System.out.println("Part 2 -> " + getWeakness(info, list));
        } catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    private static class Bounds {
        final int first;
        final int last;

        public Bounds(int first, int last) {
            this.first = first;
            this.last = last;
        }
    }

    public static Long getWeakness(Bounds info, List<Long> list) {
        List<Long> range = new ArrayList<>();
        for(int i = info.first; i < info.last + 1; i++) range.add(list.get(i));
        return Collections.max(range) + Collections.min(range);
    }

    public static Bounds getContiguousList(List<Long> list, long invalid) {
        int first = 0, last = 0;
        long runningTotal = 0;

        for(int i = 0; i < list.size(); i++) {
            runningTotal += list.get(i);

            if(runningTotal == invalid) {
                last = i;
                break;
            }

            if(list.get(i) == invalid) {
                runningTotal = 0;
                first += 1;
                i = first - 1;
            }
        }

        return new Bounds(first, last);
    }

    public static long getInvalid(Queue<Long> preamble, List<Long> inputList) {
        long principal;

        for(int i = preamble.size(); i < inputList.size(); i++) {
            principal = inputList.get(i);
            boolean withinPreamble = false;
            for(long code : preamble) {
                if(preamble.contains(principal - code) && principal != code) {
                    withinPreamble = true;
                    break;
                }
            }
            if(!withinPreamble) return principal;

            // Dequeue and shift
            preamble.remove();
            preamble.add(principal);
        }

        return -1;
    }

    public static Queue<Long> getPreamble(List<Long> inputList) {
        Queue<Long> preamble = new ArrayDeque<>();
        final int PREAMBLE_SIZE = 25;
        for(int i = 0; i < PREAMBLE_SIZE; i++) preamble.add(inputList.get(i));
        return preamble;
    }

    public static List<Long> fillList(Scanner in) {
        List<Long> listCodes = new ArrayList<>();
        while(in.hasNextLine()) { listCodes.add(Long.parseLong(in.nextLine())); }
        return listCodes;
    }

}
