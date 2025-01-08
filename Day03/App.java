package Day03;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Program menghitung bangun datar ===");
        System.out.println("Silahkan pilih bangun datar");
        System.out.println("1. Lingkaran");
        System.out.println("2. Persegi");
        System.out.println("3. Persegi Panjang");
        System.out.println("4. Segitiga");
        System.out.println("5. Jajargenjang");
        System.out.println("=======================================");

        Scanner s = new Scanner(System.in);

        int selected = s.nextInt();

        switch (selected) {
            case 1:
                double diameter;
                System.out.println("Masukan diameter lingkaran: ");
                diameter = s.nextDouble();

                Lingkaran circle = new Lingkaran(diameter);

                System.out.println("Jari-Jari Lingkaran: " + circle.getAlas() / 2);
                System.out.println("Luas lingkaran: " + circle.hitungLuas());
                System.out.println("Keliling lingkaran: " + circle.hitungKeliling());

                break;

            case 2:
                System.out.println("Masukan sisi persegi: ");
                double sisi = s.nextDouble();

                Persegi square = new Persegi(sisi);

                System.out.println("Sisi Persegi: " + square.getAlas());
                System.out.println("Luas Persegi: " + square.hitungLuas());
                System.out.println("Keliling Persegi: " + square.hitungKeliling());
                break;
            case 3:
                System.out.println("Masukan alas persegi panjang: ");
                double alas = s.nextDouble();
                System.out.println("Masukan tinggi persegi panjang: ");
                double tinggi = s.nextDouble();

                PersegiPanjang rectangle = new PersegiPanjang(alas, tinggi);

                System.out.println("Alas Persegi Panjang: " + rectangle.getAlas());
                System.out.println("Tinggi Persegi Panjang: " + rectangle.getTinggi());
                System.out.println("Luas Persegi Panjang: " + rectangle.hitungLuas());
                System.out.println("Keliling Persegi Panjang: " + rectangle.hitungKeliling());
                break;
            case 4:
                System.out.println("Masukan alas Segitiga: ");
                double alas1 = s.nextDouble();
                System.out.println("Masukan tinggi Segitiga: ");
                double tinggi1 = s.nextDouble();

                Segitiga triangle = new Segitiga(alas1, tinggi1);

                System.out.println("Alas Segitiga: " + triangle.getAlas());
                System.out.println("Tinggi Segitiga: " + triangle.getTinggi());
                System.out.println("Luas Segitiga: " + triangle.hitungLuas());
                System.out.println("Keliling Segitiga: " + triangle.hitungKeliling());
                break;
            default:
                System.out.println("Masukan alas Jajar Genjang: ");
                double alas2 = s.nextDouble();
                System.out.println("Masukan tinggi Jajar Genjang: ");
                double tinggi2 = s.nextDouble();

                JajarGenjang Parallelogram = new JajarGenjang(alas2, tinggi2);

                System.out.println("Alas Jajar Genjang: " + Parallelogram.getAlas());
                System.out.println("Tinggi Jajar Genjang: " + Parallelogram.getTinggi());
                System.out.println("Luas Jajar Genjang: " + Parallelogram.hitungLuas());
                System.out.println("Keliling Jajar Genjang: " + Parallelogram.hitungKeliling());
                break;
        }

        s.close();
    }
}
