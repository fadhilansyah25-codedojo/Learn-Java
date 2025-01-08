package Day03;

import Day03.BluePrint.BangunDatar;

public class Lingkaran extends BangunDatar {
    // Constructor
    Lingkaran(double diameter) {
        this.setAlas(diameter);
    };

    @Override
    public double hitungLuas() {
        return Math.PI * Math.pow((this.getAlas() / 2), 2);
    }

    @Override
    public double hitungKeliling() {
        return Math.PI * (this.getAlas() / 2);
    }
}
