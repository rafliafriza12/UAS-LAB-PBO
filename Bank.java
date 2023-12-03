import java.util.Scanner;

/**
 * Kelas Bank merupakan turunan dari kelas Pembayaran.
 * Representasi objek pembayaran melalui sistem perbankan.
 */
public class Bank extends Pembayaran {

    /**
     * Variabel instance untuk menyimpan harga total pembayaran.
     */
    private long hargaTotal;

    /**
     * Konstanta untuk menyimpan nomor rekening bank.
     */
    private final String noRekening = "083197471234";

    /**
     * Konstruktor kelas Bank.
     *
     * @param hargaTotal Harga total pembayaran yang harus dilakukan.
     */
    public Bank(long hargaTotal) {
        this.hargaTotal = hargaTotal;
    }

    /**
     * Metode untuk mendapatkan harga total pembayaran.
     *
     * @return Harga total pembayaran.
     */
    public long getHargaTotal() {
        return hargaTotal;
    }

    /**
     * Metode untuk melakukan proses pembayaran melalui sistem perbankan.
     * Pengguna diminta untuk membayar sesuai dengan harga total yang ditetapkan.
     */
    public void bayar() {
        ClearConsole clear = new ClearConsole();
        Scanner scan = new Scanner(System.in);
        long bayar = 0;

        System.out.println("Silahkan bayar ke nomor Bank berikut : " + this.noRekening);
        System.out.println("Total yang harus anda bayar adalah : " + this.getHargaTotal());

        while (bayar != this.getHargaTotal()) {
            System.out.print("Bayar => ");
            bayar = scan.nextLong();
            if (bayar == this.getHargaTotal()) {
                clear.clear();
                System.out.println("Transaksi sedang di proses\nTunggu Admin menyetujui pembayaran anda\n");
            } else {
                System.out.println("Nominal yang anda masukkan tidak sesuai");
            }
        }
    }
}
