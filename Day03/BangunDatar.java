package Day03;

abstract public class BangunDatar {
    private float luas;
    private float keliling;
    
    public float getLuas() {
        return luas;
    }

    protected void setLuas(float luas) {
        this.luas = luas;
    }

    public float getKeliling() {
        return keliling;
    }

    protected void setKeliling(float keliling) {
        this.keliling = keliling;
    }
    
    abstract public void hitungLuas();
    abstract public void hitungKeliling();
}
