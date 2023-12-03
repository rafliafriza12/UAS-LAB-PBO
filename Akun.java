/**
 * Kelas abstrak Akun merepresentasikan entitas akun dengan ID dan password.
 * Kelas ini menyediakan metode abstrak untuk mengelola ID dan password akun.
 */
public abstract class Akun {

    /**
     * Variabel instance untuk menyimpan ID akun.
     */
    protected String id;

    /**
     * Konstruktor kelas Akun.
     *
     * @param id ID yang akan diatur untuk akun.
     */
    public Akun(String id) {
        this.id = id;
    }

    /**
     * Metode abstrak untuk mengatur ID akun.
     *
     * @param id ID baru untuk diatur.
     */
    public abstract void setId(String id);

    /**
     * Metode abstrak untuk mendapatkan ID akun.
     *
     * @return ID akun saat ini.
     */
    public abstract String getId();

    /**
     * Metode abstrak untuk mengatur password akun.
     *
     * @param pass Password baru untuk diatur.
     */
    public abstract void setPass(String pass);

    /**
     * Metode abstrak untuk mendapatkan password akun.
     *
     * @return Password akun saat ini.
     */
    public abstract String getPass();
}
