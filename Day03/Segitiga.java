package Day03;

public class Segitiga extends BangunDatar {
    private float alas;
    private float tinggi;

    private float sisiA;
    private float sisiB;
    private float sisiC;

    // Constructor
    Segitiga() {
    };

    Segitiga(float alas, float tinggi) {
        this.alas = alas;
        this.tinggi = tinggi;

        this.hitungLuas();
    };

    Segitiga(float alas, float tinggi, float a, float b, float c) {
        this.alas = alas;
        this.tinggi = tinggi;

        this.sisiA = a;
        this.sisiB = b;
        this.sisiC = c;
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

    public float getSisiA() {
        return sisiA;
    }

    public void setSisiA(float sisiA) {
        this.sisiA = sisiA;
    }

    public float getSisiB() {
        return sisiB;
    }

    public void setSisiB(float sisiB) {
        this.sisiB = sisiB;
    }

    public float getSisiC() {
        return sisiC;
    }

    public void setSisiC(float sisiC) {
        this.sisiC = sisiC;
    }

    // Method
    @Override
    public void hitungLuas() {
        this.setLuas((this.alas * this.tinggi) * 0.5f);
    }

    @Override
    public void hitungKeliling() {
        if (sisiA != 0 && sisiB != 0 && sisiC != 0) {
            this.setKeliling(sisiA + sisiB + sisiC);
        } else {
            System.out.println("Semua nilai dari sisi segitiga tidak lengkap!");
        }
    }
}