package Day03;

public class JajarGenjang extends BangunDatar{
    private float alas;
    private float tinggi;

    public float sisiA;
    public float sisiB;

    // Constructor
    JajarGenjang() {
    };

    JajarGenjang(float alas, float tinggi) {
        this.alas = alas;
        this.tinggi = tinggi;

        this.hitungLuas();
    };

    JajarGenjang(float alas, float tinggi, float a, float b) {
        this.alas = alas;
        this.tinggi = tinggi;

        this.sisiA = a;
        this.sisiB = b;
    };

    // Get & Set
    public float getAlas() {
        return alas;
    }

    public void setAlas(float alas) {
        this.alas = alas;
        this.hitungLuas();
    }

    public float getTinggi() {
        return tinggi;
    }

    public void setTinggi(float tinggi) {
        this.tinggi = tinggi;
        this.hitungLuas();
    }

    // Method
    @Override
    public void hitungLuas() {
        this.setLuas((this.alas * this.tinggi) * 0.5f);
    }

    @Override
    public void hitungKeliling() {
        if (sisiA != 0 && sisiB != 0) {
            this.setKeliling(2 * (sisiA + sisiB));
        } else {
            System.out.println("Semua nilai dari sisi jajargenjang tidak lengkap!");
        }
    }
}
