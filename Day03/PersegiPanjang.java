package Day03;

import Day03.BluePrint.BangunDatar;

public class PersegiPanjang extends BangunDatar {
    PersegiPanjang(double alas, double tinggi) {
        super();
    }

    @Override
    public double hitungLuas() {
        return this.getAlas() * this.getTinggi();
    }

    @Override
    public double hitungKeliling() {
        return 2 * (this.getAlas() + this.getTinggi());
    }
}
