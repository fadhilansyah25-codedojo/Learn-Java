public class Day01 {
    private static int constant = 0;

    // create new method
    private static void helloFriends(String name) {
        System.out.println("Hello " + name + ", my friend!");
    }

    public static void main(String[] args) {
        System.out.println("Hello world");

        // Test constant variable
        System.out.println(constant);

        byte tinyNumber = 1;
        short smallNumber = 5;
        int mediumNumber = 20;
        long largeNumber = 1000000000;

        System.out.println(tinyNumber);
        System.out.println(smallNumber);
        System.out.println(mediumNumber);
        System.out.println(largeNumber);

        float mediumDecimal = 10.0f;
        double largeDecimal = 100.0d;

        System.out.println(mediumDecimal);
        System.out.println(largeDecimal);

        boolean truthy = true;
        boolean falsy = false;

        System.out.println(truthy);
        System.out.println(falsy);

        Integer mediumObjectNumber = 5;

        System.out.println(mediumObjectNumber.toString());

        String word = "Fadil"; // tipe data string di java bukan merupakan tipe data primitives melainkan
                               // sebuah object

        System.out.println(word.toUpperCase());

        helloFriends("Fadil");
    }
}