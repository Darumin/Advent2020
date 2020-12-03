import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

public class Advent2020Day1 {
    public static void main(String[] args) {
        try {
            File newFile = new File("puzzleinput1.txt");
            Scanner in = new Scanner(newFile);
            HashMap<Integer, Integer> hm = new HashMap<>();
            ArrayList<Integer> al = new ArrayList<>();

            while(in.hasNextLine()) { al.add(Integer.parseInt(in.nextLine())); }

            for(int i: al) {
                for(int j: al) {
                    for(int k: al) {
                        if(i + j + k == 2020) {
                            System.out.printf("%d, %d, %d\n", i, j, k);
                            System.out.printf("%d\n", i * j * k);
                            break;
                        }
                    }
                }
            }

            in.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Not safe!" + e.getMessage());
        }


    }
}
