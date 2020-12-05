import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Day2 {
    public static void main(String[] args) {
        try(Scanner in = new Scanner(new File("input2.txt"))){
            ArrayList<String> al = new ArrayList<>();
            while(in.hasNextLine()) al.add(in.nextLine());

            System.out.println("Part 1 -> " + part1(al));
            System.out.println("Part 2 -> " + part2(al));
        }
        catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    public static int part1(ArrayList<String> passwords) {
        int validPasswords = 0;
        for(String password: passwords){
            String[] spaces = password.split(" ");
            String[] hyphens = spaces[0].split("-");
            int a = Integer.parseInt(hyphens[0]), b = Integer.parseInt(hyphens[1]);
            char symbol = spaces[1].charAt(0);
            long count = spaces[2].chars().filter(ch -> ch == symbol).count();
            validPasswords += (count >= a && count <= b) ? 1 : 0;
        }

        return validPasswords;
    }

    public static int part2(ArrayList<String> passwords) {
        int validPasswords = 0;
        for(String password: passwords){
            String[] spaces = password.split(" ");
            String[] hyphens = spaces[0].split("-");
            int a = Integer.parseInt(hyphens[0]), b = Integer.parseInt(hyphens[1]);
            char symbol = spaces[1].charAt(0);
            validPasswords += (spaces[2].charAt(a - 1) == symbol) ^ (spaces[2].charAt(b - 1) == symbol) ? 1 : 0;
        }

        return validPasswords;
    }
}
