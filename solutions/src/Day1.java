import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Day1 {
    public static void main(String[] args) {
        try(Scanner in = new Scanner(new File("input1.txt"))){
            ArrayList<Integer> al = new ArrayList<>();
            while(in.hasNextLine()) al.add(Integer.parseInt(in.nextLine()));

            System.out.println("Part 1 -> " + part1(al));
            System.out.println("Part 2 -> " + part2(al));
        }
        catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    public static int part1(ArrayList<Integer> years) {
        final int PRINCIPAL = 2020;
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int year : years) hm.put(year, PRINCIPAL - year);
        for(int key : hm.keySet()) if(hm.containsKey(PRINCIPAL - key)) return key * hm.get(key);
        return 0;
    }

    public static int part2(ArrayList<Integer> years) {
        for(int i: years) for(int j: years) for(int k: years) if(i + j + k == 2020) return i * j * k;
        return 0;
    }
}
