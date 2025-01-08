package Day03;

public class PersegiPanjang extends BangunDatar {
    private float alas;
    private float tinggi;

    PersegiPanjang() {
    };

    PersegiPanjang(float alas, float tinggi) {
        this.alas = alas;
        this.tinggi = tinggi;

        this.hitungLuas();
        this.hitungKeliling();
    }

    @Override
    public void hitungLuas() {
        this.setLuas(alas * tinggi);
    }

    @Override
    public void hitungKeliling() {
        this.setKeliling(2 * (alas + tinggi));
    }
}
