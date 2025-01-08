package Day03;

public class Lingkaran extends BangunDatar {
    private float jari;
    private float diameter;

    // Constructor
    Lingkaran(){};
    
    Lingkaran(float jari){
        this.jari = jari;
        this.diameter = 2 * jari;

        this.hitungLuas();
        this.hitungKeliling();
    };

    Lingkaran(int jari){
        this.jari = jari;
        this.diameter = 2 * jari;

        this.hitungLuas();
        this.hitungKeliling();
    };


    // Get & Set
    public float getJari() {
        return jari;
    }

    public void setJari(float jari) {
        this.jari = jari;
        this.diameter = 2 * jari;

        this.hitungLuas();
        this.hitungKeliling();
    }

    public float getDiameter() {
        return diameter;
    }

    @Override
    public void hitungLuas() {
        this.setLuas((float)(Math.PI * Math.pow((double)jari, 2)));
    }

    @Override
    public void hitungKeliling() {
        this.setKeliling((float)(Math.PI * (double)jari));
    }

}
