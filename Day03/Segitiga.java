package Day03;

import Day03.BluePrint.BangunDatar;

public class Segitiga extends BangunDatar {
    private double sisiA;
    private double sisiB;
    private double sisiC;

    // Constructor
    Segitiga() {
    };

    Segitiga(double alas, double tinggi) {
        super();
    };

    Segitiga(double alas, double tinggi, double a, double b, double c) {
        this.setAlas(alas);
        this.setTinggi(tinggi);

        this.sisiA = a;
        this.sisiB = b;
        this.sisiC = c;
    };

    // Method
    @Override
    public double hitungLuas() {
        return (this.getAlas() * this.getTinggi()) * 0.5f;
    }

    @Override
    public double hitungKeliling() {
        if (sisiA != 0 && sisiB != 0 && sisiC != 0) {
            return sisiA + sisiB + sisiC;
        } else {
            return 0;
        }
    }
}