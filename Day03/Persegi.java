package Day03;

import Day03.BluePrint.BangunDatar;

public class Persegi extends BangunDatar {
    // Constructor
    Persegi(double sisi) {
        setAlas(sisi);
    }

    @Override
    public double hitungLuas() {
        return Math.pow(getAlas(), 2);
    }

    @Override
    public double hitungKeliling() {
        return 4 * getAlas();
    }
}
