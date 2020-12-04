import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) throws FileNotFoundException {
        hashInputs("input4.txt");
    }

    public static void hashInputs(String fileName) {
        try {
            int part1 = 0;
            int part2 = 0;
            File newFile = new File(fileName);
            Scanner in = new Scanner(newFile);
            HashMap<String, String> hm = new HashMap<>();

            while(in.hasNextLine()) {
                String[] items = in.nextLine().split(" ");
                if(items[0].equals("")) {
                    if(validPassport(hm)) {
                        part1 += 1;
                        if (passportRules(hm)) part2 += 1;
                    }
                    hm.clear();
                }
                else {
                    for (String item : items) {
                        String[] kvPair = item.split(":");
                        hm.put(kvPair[0], kvPair[1]);
                    }
                }
            }

            System.out.println(part1);
            System.out.println(part2);
        } catch(FileNotFoundException e) {
            System.out.println("Not safe!" + e.getMessage());
        }
    }

    public static boolean validPassport(HashMap<String, String> inputs) {
        String[] fields = {"hcl", "iyr", "hgt", "byr", "pid", "cid", "eyr", "ecl"};

        for(String field: fields) {
            if(inputs.get(field) == null) {
                if(field.equals("cid")) continue;
                return false;
            }
        }

        return true;
    }

    public static boolean passportRules(HashMap<String, String> inputs) {

        boolean byrCompare = false,
                iyrCompare = false,
                eyrCompare = false,
                hgtCompare = false,
                hclCompare = false,
                eclCompare = false,
                pidCompare = false;

        String[] yearsGet = {inputs.get("byr"), inputs.get("iyr"), inputs.get("eyr")};
        if(isNumeric(yearsGet[0])) {
            int a = Integer.parseInt(yearsGet[0]);
            byrCompare = (a >= 1920 && a <= 2002);
        }

        if(isNumeric(yearsGet[1])) {
            int b = Integer.parseInt(yearsGet[1]);
            System.out.print(b + " ");
            iyrCompare = (b >= 2010 && b <= 2020);
        }

        if(isNumeric(yearsGet[2])) {
            int c = Integer.parseInt(yearsGet[2]);
            eyrCompare = (c >= 2020 && c <= 2030);
        }

        String hgt = inputs.get("hgt");
        String unit = hgt.substring(hgt.length() - 2);
        System.out.print(unit + " ");
        if(unit.equals("cm") || unit.equals("in")){
            int measurement = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
            if(unit.equals("cm")) hgtCompare = (measurement >= 150 && measurement <= 193);
            if(unit.equals("in")) hgtCompare = (measurement >= 59 && measurement <= 76);
        }

        String hcl = inputs.get("hcl").substring(1);
        hclCompare = (isAlphaNumeric(hcl)) && (hcl.length() == 6);

        String ecl = inputs.get("ecl");
        String[] validEcl = {"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
        for(String eye: validEcl) {
            if(ecl.equals(eye)) {
                eclCompare = true;
                break;
            }
        }

        String pid = inputs.get("pid");
        pidCompare = (isNumeric(pid) && pid.length() == 9);

        System.out.printf("%b %b %b %b %b %b %b\n",
                byrCompare, iyrCompare, eyrCompare, hgtCompare, hclCompare, eclCompare, pidCompare);
        return byrCompare && iyrCompare &&
                eyrCompare && hgtCompare &&
                hclCompare && eclCompare &&
                pidCompare;
    }

    public static boolean isAlphaNumeric(String input) { return input != null && input.matches("^[a-zA-Z0-9]*$"); }

    public static boolean isNumeric(String input) {
        if(input.length() < 1) return false;
        if(input.charAt(0) == '0') return isNumeric(input.substring(1));
        try {
            int a = Integer.parseInt(input);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
