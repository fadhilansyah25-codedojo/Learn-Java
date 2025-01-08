package Day03;

public class Persegi extends BangunDatar {
    private float sisi;

    // Constructor
    Persegi() {
    };

    Persegi(float sisi) {
        this.sisi = sisi;

        this.hitungLuas();
        this.hitungKeliling();
    };

    // Get & Set
    public float getSisi() {
        return sisi;
    }

    public void setSisi(float sisi) {
        this.sisi = sisi;

        this.hitungLuas();
        this.hitungKeliling();
    }

    @Override
    public void hitungLuas() {
        this.setLuas((float) Math.pow((double) sisi, 2));
    }

    @Override
    public void hitungKeliling() {
        this.setKeliling(4 * sisi);
    }

}
