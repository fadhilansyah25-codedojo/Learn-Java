package Day03;

public class App {
    public static void main(String[] args) {
        Lingkaran myLingkaran = new Lingkaran(10);
        myLingkaran.hitungLuas();
        myLingkaran.hitungKeliling();

        // keliling
        System.out.println("Luas Lingkaran: "+myLingkaran.getLuas());
        System.out.println("keliling Lingkaran: "+myLingkaran.getKeliling());

        myLingkaran.setJari(20);

        System.out.println("Luas Lingkaran: "+myLingkaran.getLuas());
        System.out.println("keliling Lingkaran: "+myLingkaran.getKeliling());

        // jajargenjang
        JajarGenjang myJajarGenjang = new JajarGenjang(10,2);
        System.out.println("Luas Jajargenjang: "+myJajarGenjang.getLuas());

        myJajarGenjang.sisiA = 10;
        myJajarGenjang.sisiB = 10;
        System.out.println("keliling Lingkaran: "+myLingkaran.getKeliling());

        // Segitiga
        Segitiga mySegitiga = new Segitiga(10,5);
        System.out.println("Luas Segitiga: "+mySegitiga.getLuas());

        mySegitiga.setSisiA(10);
        mySegitiga.setSisiB(20);
        mySegitiga.setSisiC(10);
        mySegitiga.hitungKeliling();
        
        System.out.println("keliling Segitiga: "+mySegitiga.getKeliling());
    }
}
