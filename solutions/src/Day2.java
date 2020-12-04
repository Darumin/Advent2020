import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Day2 {
    public static void main(String[] args) {
        try {
            File newFile = new File("input2.txt");
            Scanner in = new Scanner(newFile);
            ArrayList<String> hm = new ArrayList<>();

            while(in.hasNextLine()) {
                hm.add(in.nextLine());
            }

            part1(hm);
            part2(hm);
        }
        catch (FileNotFoundException e) {
            System.out.println("Not safe!" + e.getMessage());
        }
    }

    public static void part1(ArrayList<String> puzzleInput) {
        int validPasswords = 0;

        for(String str: puzzleInput){
            String[] spaces = str.split(" ");
            String[] hyphens = spaces[0].split("-");
            int a = Integer.parseInt(hyphens[0]), b = Integer.parseInt(hyphens[1]);
            char symbol = spaces[1].charAt(0);
            long count = spaces[2].chars().filter(ch -> ch == symbol).count();
            validPasswords += (count >= a && count <= b) ? 1 : 0;
        }

        System.out.println("Valid passwords: " + validPasswords);
    }

    public static void part2(ArrayList<String> puzzleInput) {
        int validPasswords = 0;

        for(String str: puzzleInput){
            String[] spaces = str.split(" ");
            String[] hyphens = spaces[0].split("-");
            int a = Integer.parseInt(hyphens[0]), b = Integer.parseInt(hyphens[1]);
            char symbol = spaces[1].charAt(0);
            validPasswords += (spaces[2].charAt(a - 1) == symbol) ^ (spaces[2].charAt(b - 1) == symbol) ? 1 : 0;
        }

        System.out.println("Valid passwords: " + validPasswords);
    }
}
