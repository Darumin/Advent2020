import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5 {
    public static void main(String[] args) {
        try(Scanner in = new Scanner(new File("input5.txt"))){
            ArrayList<String> al = new ArrayList<>();
            while(in.hasNextLine()) al.add(in.nextLine());

            String[][] layout = part1(al);
            for (String[] strings : layout) {
                for (String string : strings) {
                    if (string == null) System.out.print(" XX ");
                    else System.out.print(string + " ");
                }
                System.out.println();
            }
        }
        catch (FileNotFoundException e) {
            e.getStackTrace();
        }
    }

    public static String[][] part1(ArrayList<String> passes) {
        final int ROWS = 127;
        String[][] layout = new String[8][127];
        ArrayList<Integer> passList = new ArrayList<>();
        int count = 0;

        for(String pass : passes) {
            int split = ROWS / 2;
            int top = ROWS;
            int bottom = 0;
            char direction = ' ';
            int[] rangeOne = new int[0];
            int[] rangeTwo = new int[0];

            for(int i = 0; i < 8; i++) {
                direction = pass.charAt(i);

                switch (direction) {
                    case 'F' -> {
                        rangeOne = new int[]{bottom, split};
                    }
                    case 'B' -> {
                        rangeOne = new int[]{split, top};
                    }
                }
                bottom = rangeOne[0];
                top = rangeOne[1];
                split = (top + bottom) / 2;
            }

            int column = rangeOne[0];
//            int column = direction == 'F' ? rangeOne[0] : rangeOne[1];

            bottom = 0;
            top = 7;
            split = top / 2;

            for(int i = 7; i < 10; i++) {
                direction = pass.charAt(i);

                switch (direction) {
                    case 'L' -> {
                        rangeTwo = new int[]{bottom, split};
                    }
                    case 'R' -> {
                        rangeTwo = new int[]{split, top};
                    }
                }
                bottom = rangeTwo[0];
                top = rangeTwo[1];
                split = (top + bottom) / 2;
            }

            int row = rangeTwo[0];
//            int row = direction == 'L' ? rangeTwo[0] : rangeTwo[1];

            layout[row][column] = row + ":" + column;
            passList.add(column * 8 + row);
            count++;
        }

        System.out.println(Collections.max(passList));

        return layout;
    }
}
