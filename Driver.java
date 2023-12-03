/**
 * Kelas abstrak Driver merupakan kerangka dasar untuk driver aplikasi.
 * Kelas ini memiliki metode abstrak 'menu()' yang perlu diimplementasikan oleh kelas turunannya.
 */
public abstract class Driver {
    /**
     * Metode abstrak menu() merupakan kerangka untuk menu utama pada aplikasi.
     * Setiap kelas turunan wajib mengimplementasikan metode ini untuk menentukan fungsionalitas menu aplikasi.
     */
    public abstract void menu();
}
