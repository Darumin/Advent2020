// TODO: Clean up
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day3 {
    public static void main(String[] args) {
        try {
            File newFile = new File("input3.txt");
            Scanner in = new Scanner(newFile);
            ArrayList<String> hm = new ArrayList<>();

            while(in.hasNextLine()) { hm.add(in.nextLine()); }

            part1(hm);
            part2(hm);
        }
        catch (FileNotFoundException e) {
            System.out.println("Not safe!" + e.getMessage());
        }
    }

    public static void part1(ArrayList<String> hm) {
        int x = 0, trees = 0;
        for(String str: hm) {
            if(str.charAt(x % str.length()) == '#') { trees += 1; }
            x += 3;
        }

        System.out.println(trees);
    }

    public static void part2(ArrayList<String> hm) {
        int a = 0, b = 0, c = 0, d = 0, e = 0;
        int slope1 = 0, slope2 = 0, slope3 = 0, slope4 = 0, slope5 = 0;

        for(String str: hm) {
            if(str.charAt(a % str.length()) == '#') { slope1 += 1; }
            if(str.charAt(b % str.length()) == '#') { slope2 += 1; }
            if(str.charAt(c % str.length()) == '#') { slope3 += 1; }
            if(str.charAt(d % str.length()) == '#') { slope4 += 1; }
            a += 1;
            b += 3;
            c += 5;
            d += 7;
        }

        for(int i = 0; i < hm.size(); i++) {
            if(i % 2 == 0) {
                if(hm.get(i).charAt(e % hm.get(i).length()) == '#') { slope5 += 1; }
                e += 1;
            }
        }

        System.out.println(slope1 * slope2 * slope3 * slope4 * slope5);
    }
}
