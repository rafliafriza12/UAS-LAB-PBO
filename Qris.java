import java.util.Scanner;

/**
 * Kelas Qris merupakan implementasi dari pembayaran menggunakan QRIS (Quick Response Code).
 * Kelas ini merupakan turunan dari kelas Pembayaran dan mengimplementasikan metode abstrak 'bayar()'.
 * QRIS digunakan untuk pembayaran dengan melakukan scan QR code dan memasukkan nominal sesuai dengan total yang harus dibayar.
 */

public class Qris extends Pembayaran{
    private long hargaTotal;

    /**
     * Konstruktor untuk kelas Qris yang menerima total harga yang harus dibayar.
     * @param hargaTotal Total harga yang harus dibayar dalam transaksi.
     */

    public Qris(long hargaTotal){
        this.hargaTotal = hargaTotal;
    }

    /**
     * Getter untuk mendapatkan total harga yang harus dibayar.
     * @return Total harga yang harus dibayar.
     */

    public long getHargaTotal() {
        return hargaTotal;
    }

     /**
     * Metode implementasi dari pembayaran menggunakan QRIS.
     * Meminta pengguna untuk melakukan scan QR code dan memasukkan nominal sesuai dengan total yang harus dibayar.
     * Jika nominal yang dimasukkan sesuai, pesan proses pembayaran ditampilkan. Jika tidak, pesan kesalahan ditampilkan.
     */
    
    public void bayar(){
        Scanner scan = new Scanner(System.in);
        long bayar = 0;
        ClearConsole clear = new ClearConsole();

        System.out.println("# # # # # # #            # # # # # # #");
        System.out.println("#           #  # #  # #  #           #");
        System.out.println("#   # # #   #    #  # #  #   # # #   #");
        System.out.println("#   # # #   #  # #       #   # # #   #");
        System.out.println("#   # # #   #     #      #   # # #   #");
        System.out.println("#           #  # # # #   #           #");
        System.out.println("# # # # # # #  #  #  #   # # # # # # #");
        System.out.println("\n");
        System.out.println("              $ $ $ $ $");
        System.out.println("  # #  # #    $        $     #     #");
        System.out.println("    #  # #    $        $     # # # #");
        System.out.println("  # #         $ $ $ $ $         #");
        System.out.println("     #        $   $              # #");
        System.out.println("  # # # #     $     $       # #  # ");
        System.out.println("  #     #     $       $     # #  # #");
        System.out.println("              $        $");
        System.out.println("\n");
        System.out.println("# # # # # # #            # # # # # # #");
        System.out.println("#           #  # #  # #  #           #");
        System.out.println("#   # # #   #    #  # #  #   # # #   #");
        System.out.println("#   # # #   #  # #       #   # # #   #");
        System.out.println("#   # # #   #     #      #   # # #   #");
        System.out.println("#           #  # # # #   #           #");
        System.out.println("# # # # # # #  #  #  #   # # # # # # #");

        System.out.println("\n\nSilahkan Scan QR Qode terlebih dahulu lalu masukkan nominal sebesar " + this.hargaTotal );
        while (bayar!=this.getHargaTotal()) {
            System.out.print("Bayar => ");
            bayar = scan.nextLong();
            if(bayar==this.getHargaTotal()){
                clear.clear();
                System.out.println("Transaksi sedang di proses\nTunggu Admin menyetujui pembayaran anda\n");
            }
            else{
                System.out.println("Nominal yang anda masukkan tidak sesuai");
            }
        }
    }
}
