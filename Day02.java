import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Day02 {
    public static void main(String[] args) {
        int[] arr = new int[5];

        arr[0] = 1;
        arr[1] = 2;
        arr[2] = 3;
        arr[3] = 4;
        arr[4] = 5;

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        System.out.println();

        ArrayList<String> motorbike = new ArrayList<>();
        motorbike.add("Supra bapack");
        motorbike.add("Shogun Kebo");
        motorbike.add("Vespa Senja");

        for (int i = 0; i < motorbike.size(); i++) {
            System.out.println(motorbike.get(i));
        }
        System.out.println();

        motorbike.set(2, "Vixion Jari-jari");

        System.out.println(motorbike + "\n");

        for (String i : motorbike) {
            System.out.println(i);
        }

        System.out.println();

        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(11);
        numbers.add(12);
        numbers.add(13);

        for (Integer integer : numbers) {
            System.out.println(integer);
        }

        Integer totalInteger = 0;
        for (int i = 0; i < numbers.size(); i++) {
            totalInteger += numbers.get(i);
        }
        System.out.println(totalInteger);
        for (Integer integer : numbers) {
            totalInteger += integer;
        }
        System.out.println(totalInteger);

        System.out.println();
        // String
        String nEscapeChar = "Pembekalan\nJava";
        System.out.println(nEscapeChar);
        String tEscapeChar = "Pembekalan\tJava";
        System.out.println(tEscapeChar);
        String escapeChar = "\"Football is simple. But playing simple football is the hardest thing to do\"\nJohan Cruyff";
        System.out.println(escapeChar);
        String FirstName = "Muhammad";
        String name = "Fadil";
        System.out.println(name + "\rSamara"); // nimpa string sebelum escape expression contoh Fadil + \rSam => Samil

        System.out.println(FirstName + " " + name);
        System.out.println(FirstName + "\r" + name);

        String str = "Pembekalan";
        str = str.concat("java");
        System.out.println(str);

        String s1 = "Hello";
        String s2 = "HELLO";

        if (s1.equals(s2)) {
            System.out.println("s1 and s2 are equal");
        } else {
            System.out.println("s1 and s2 are not equal");
        }

        if (s1.equalsIgnoreCase(s2)) {
            System.out.println("s1 and s2 are ignore case equal");
        } else {
            System.out.println("s1 and s2 are not ignore case equal");
        }

        int number = 10;
        String strNumber = String.valueOf(number);
        System.out.println(strNumber);

        Integer numInteger = 1;
        String numStr = String.valueOf(numInteger);
        System.out.println(numStr.getClass());

        System.out.println();
        // String Replace
        String gigi = "Tooth";
        System.out.println(gigi.replace('o', 'e'));
        System.out.println(gigi.replace("oo", "ab"));

        StringBuilder strB = new StringBuilder(gigi);
        strB.setCharAt(1, 'e');
        gigi = strB.toString();
        System.out.println(gigi);

        System.out.println();
        // Replace using Regex
        String xsis = "PT. Xsis Mitra Utama 123456789 !@#$%^&**";
        System.out.println(xsis);
        System.out.println(xsis.replaceAll("[A-Za-z]", ""));
        System.out.println(xsis.replaceAll("[^A-Za-z]", ""));
        System.out.println(xsis.replaceAll("[0-9]", ""));
        System.out.println(xsis.replaceAll("[^0-9]", ""));
        System.out.println(xsis.replaceAll("[^A-Za-z0-9\s]", ""));
        System.out.println(xsis.replaceAll("[A-Z&&[A-D]]", "Damn"));

        System.out.println();
        // HasMap
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("status", "200");
        responseMap.put("message", "success");
        responseMap.put("data", "asus");
        System.out.println(String.valueOf(responseMap));
        System.out.println(responseMap.get("status"));

        System.out.println();
        for (Map.Entry<String, String> entry : responseMap.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }
    }
}
