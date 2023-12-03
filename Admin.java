/**
 * Kelas Admin merupakan turunan dari kelas Akun.
 * Representasi objek akun dengan hak akses admin.
 */
public class Admin extends Akun {
    // Variabel instance untuk menyimpan password admin
    private String passAdmin;

    /**
     * Konstruktor tanpa parameter yang menginisialisasi ID dan password admin dengan nilai default.
     */
    public Admin() {
        // Memanggil konstruktor superclass (Akun) dengan ID "Admin"
        super("Admin");
        // Menginisialisasi password admin dengan nilai default "Admin"
        this.passAdmin = "Admin";
    }

    /**
     * Konstruktor dengan satu parameter untuk menginisialisasi ID dan password admin.
     * @param id ID admin yang diinginkan.
     */
    public Admin(String id) {
        // Memanggil konstruktor superclass (Akun) dengan ID yang diberikan
        super(id);
        // Menginisialisasi password admin dengan nilai default "Admin"
        this.passAdmin = "Admin";
    }

    /**
     * Konstruktor dengan dua parameter untuk menginisialisasi ID dan password admin.
     * @param id ID admin yang diinginkan.
     * @param passAdmin Password admin yang diinginkan.
     */
    public Admin(String id, String passAdmin) {
        // Memanggil konstruktor superclass (Akun) dengan ID yang diberikan
        super(id);
        // Menginisialisasi password admin dengan nilai yang diberikan
        this.passAdmin = passAdmin;
    }

    /**
     * Metode untuk mengatur password admin.
     * @param pass Password admin yang baru.
     */
    public void setPass(String pass) {
        this.passAdmin = pass;
    }

    /**
     * Metode untuk mendapatkan password admin.
     * @return Password admin saat ini.
     */
    public String getPass() {
        return this.passAdmin;
    }

    /**
     * Override dari metode setId dalam kelas Akun.
     * Metode ini mengatur ID admin.
     * @param id ID admin yang baru.
     */
    @Override
    public void setId(String id) {
        super.id = id;
    }

    /**
     * Override dari metode getId dalam kelas Akun.
     * Metode ini mengembalikan ID admin saat ini.
     * @return ID admin saat ini.
     */
    @Override
    public String getId() {
        return super.id;
    }
}
