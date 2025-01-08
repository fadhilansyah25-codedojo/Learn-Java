package Day03.BluePrint;

abstract public class BangunDatar {
    private double alas;
    private double tinggi;
    private double garismiring;

    public double getAlas() {
        return alas;
    }
    public void setAlas(double alas) {
        this.alas = alas;
    }
    public double getTinggi() {
        return tinggi;
    }
    public void setTinggi(double tinggi) {
        this.tinggi = tinggi;
    }
    public double getGarismiring() {
        return garismiring;
    }
    public void setGarismiring(double garismiring) {
        this.garismiring = garismiring;
    }
    abstract public double hitungLuas();
    abstract public double hitungKeliling();
}
