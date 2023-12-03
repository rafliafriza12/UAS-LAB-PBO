import java.util.Scanner;

/**
 * Kelas CustomerDriver bertanggung jawab untuk mengelola tampilan dan
 * interaksi pengguna untuk pelanggan. Kelas ini menyediakan berbagai menu
 * seperti melihat barang, mengelola keranjang belanja, melakukan checkout,
 * dan melihat invoice.
 *
 * @author Rafli Afriza Nugraha
 * @version 1.0
 */
public class CustomerDriver extends Driver {

    /**
     * Objek Customer yang terkait dengan CustomerDriver.
     */
    private Customer akun;

    /**
     * Objek Transaksi yang digunakan untuk mengelola transaksi pelanggan.
     */
    private Transaksi transaksi;

    /**
     * Objek ListBarang yang menyimpan daftar barang yang dapat dilihat dan
     * ditambahkan ke keranjang oleh pelanggan.
     */
    private ListBarang barang;

    /**
     * Objek Scanner untuk menerima input dari pengguna.
     */
    private Scanner scan;

    /**
     * Konstruktor untuk kelas CustomerDriver.
     *
     * @param customer Objek Customer yang akan dihubungkan dengan CustomerDriver.
     */
    public CustomerDriver(Customer customer) {
        this.akun = new Customer(customer.getId(), customer.getPass());
        this.transaksi = new Transaksi(customer.getId(), customer.getPass());
        this.barang = new ListBarang();
        this.scan = new Scanner(System.in);
    }

    /**
     * Menampilkan menu utama untuk pelanggan dan meng-handle input pengguna.
     */
    public void menu() {
        int pilihMenu;
        ClearConsole clear = new ClearConsole();
        System.out.printf("\nSelamat datang %s\n\n", this.akun.getId());

        do {
            System.out.println("   Menu Customer");
            System.out.println("+++++++++++++++++++++");
            System.out.println("[1] Lihat Barang");
            System.out.println("[2] Lihat Keranjang");
            System.out.println("[3] Tambah barang ke keranjang");
            System.out.println("[4] Edit Barang Di Keranjang");
            System.out.println("[5] Hapus Barang Di Keranjang");
            System.out.println("[6] Check Out");
            System.out.println("[7] Lihat Invoice");
            System.out.println("[8] Logout");
            System.out.println("+++++++++++++++++++++");
            System.out.print("Pilih => ");
            pilihMenu = scan.nextInt();

            switch (pilihMenu) {
                case 1:
                    clear.clear();
                    barang.tampilkanBarang();
                    break;
                case 2:
                    clear.clear();
                    this.akun.getKeranjang().tampilkanIsiKeranjang(this.akun);
                    break;
                case 3:
                    clear.clear();
                    this.akun.getKeranjang().masukkanBarangKeKeranjang(this.akun);
                    break;
                case 4:
                    clear.clear();
                    this.akun.getKeranjang().editKeranjang(this.akun);
                    break;
                case 5:
                    clear.clear();
                    this.akun.getKeranjang().hapusBarang(this.akun);
                    break;
                case 6:
                    clear.clear();
                    if (this.transaksi.transaksiCustomer() == false) {
                        this.menu();
                    }
                    break;
                case 7:
                    clear.clear();
                    this.akun.tampilInvoice(this.akun.getId());
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } while (pilihMenu > 0 && pilihMenu < 9);
    }
}
