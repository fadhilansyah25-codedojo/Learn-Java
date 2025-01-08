package Day03;

import Day03.BluePrint.BangunDatar;

public class JajarGenjang extends BangunDatar {

    public double sisiA;
    public double sisiB;

    // Constructor
    JajarGenjang() {
    };

    JajarGenjang(double alas, double tinggi) {
        this.setAlas(alas);
        this.setTinggi(tinggi);
    };

    JajarGenjang(double alas, double tinggi, double a, double b) {
        this.setAlas(alas);
        this.setTinggi(tinggi);

        this.sisiA = a;
        this.sisiB = b;
    };

    // Method
    @Override
    public double hitungLuas() {
        return (this.getAlas() * this.getTinggi()) * 0.5f;
    }

    @Override
    public double hitungKeliling() {
        if (sisiA != 0 && sisiB != 0) {
            return 2 * (sisiA + sisiB);
        } else {
            return 0;
        }
    }
}
