import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Day4 {
    public static void main(String[] args) {
        var passports = buildPassports("input4.txt");
        System.out.println(passports);
//        System.out.println(part1(passports));
    }

    public static ArrayList<String> buildPassports(String fileName) {
        ArrayList<String> passports = new ArrayList<>();
        StringBuilder pass = new StringBuilder();

        try {
            File newFile = new File(fileName);
            Scanner in = new Scanner(newFile);

            while(in.hasNextLine()) {
                String line = in.nextLine();
                if(line.isEmpty()) {
                    passports.add(pass.substring(0, pass.length() - 1));
                    pass = new StringBuilder();
                    continue;
                }
                pass.append(line);
                pass.append(" ");
            }
            passports.add(pass.substring(0, pass.length() - 1));

            return passports;
        } catch(FileNotFoundException e) {
            System.out.println("Not safe!" + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static int part1(ArrayList<String> passports) {
        int valid = 0;
        for(String passport : passports) {
            String[] items = passport.split(" ");
            Map<String, String> mapped = Arrays.stream(items).map(s -> s.split(":")).collect(
                    Collectors.toMap(s -> s[0].trim(), s -> s[1].trim()));
            valid += (items.length == 8 || items.length == 7 && mapped.get("cid") == null) ? 1 : 0;
        }
        return valid;
    }

    public static int part2(ArrayList<String> passports) {
        int valid = 0;
//        for(String passport : passports) valid += followsNewRules(passport) ? 1 : 0;
        return valid;
    }

//    public static boolean followsNewRules() {
//
//    }
    public static boolean passportRules(HashMap<String, String> inputs) {

        boolean byrCompare = false,
                iyrCompare = false,
                eyrCompare = false,
                hgtCompare = false,
                hclCompare = false,
                eclCompare = false,
                pidCompare = false;

        String[] yearsGet = {inputs.get("byr"), inputs.get("iyr"), inputs.get("eyr")};
        if(isInteger(yearsGet[0])) {
            int a = Integer.parseInt(yearsGet[0]);
            byrCompare = (a >= 1920 && a <= 2002);
        }

        if(isInteger(yearsGet[1])) {
            int b = Integer.parseInt(yearsGet[1]);
            System.out.print(b + " ");
            iyrCompare = (b >= 2010 && b <= 2020);
        }

        if(isInteger(yearsGet[2])) {
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
        pidCompare = (isInteger(pid) && pid.length() == 9);

        System.out.printf("%b %b %b %b %b %b %b\n",
                byrCompare, iyrCompare, eyrCompare, hgtCompare, hclCompare, eclCompare, pidCompare);
        return byrCompare && iyrCompare &&
                eyrCompare && hgtCompare &&
                hclCompare && eclCompare &&
                pidCompare;
    }

    public static boolean isAlphaNumeric(String input) { return input != null && input.matches("^[a-zA-Z0-9]*$"); }

    public static boolean isInteger(String input) {
        if(input.length() < 1) return false;
        if(input.charAt(0) == '0') return isInteger(input.substring(1));
        try {
            int a = Integer.parseInt(input);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

}
