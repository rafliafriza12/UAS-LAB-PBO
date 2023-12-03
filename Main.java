import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Kelas Main merupakan kelas utama yang berfungsi sebagai program aplikasi Online Shopping.
 * Program ini menyediakan fitur registrasi, login sebagai admin atau customer, dan menyimpan data akun ke dalam file.
 * Setelah login, pengguna akan diarahkan ke driver sesuai peran (AdminDriver atau CustomerDriver).
 * Kelas ini menggunakan beberapa kelas lain seperti Akun, Admin, Customer, AdminDriver, CustomerDriver, ClearConsole.
 * Program juga menggunakan file untuk menyimpan data akun.
 *
 * <p>Setiap akun (Admin atau Customer) disimpan dalam file dengan format "ID PASSWORD".
 * Data akun customer akan disimpan dalam direktori khusus untuk setiap customer dengan file keranjang dan invoice.
 *
 * @author Rafli Afriza Nugraha
 * @version 1.0
 */
public class Main{
    private Akun akun;
    private Driver driverAkun;
    
    /**
     * Metode utama untuk menjalankan aplikasi Online Shopping.
     * Memanggil metode registrasi atau login berdasarkan pilihan pengguna.
     *
     * @param args Argumen baris perintah yang tidak digunakan.
     */
    public static void main(String[] args) {
        Main main = new Main();
        Scanner scan = new Scanner(System.in);
        ClearConsole clear = new ClearConsole();
        int pilih;
        clear.clear();
        System.out.println("Selamat Datang di Online Shopping");
        do{
            System.out.println("=================================");
            System.out.println("[1] Registrasi");
            System.out.println("[2] Login");
            System.out.println("=================================");
            System.out.print("Pilih => ");
            pilih = scan.nextInt();

            switch (pilih) {
                case 1:
                    clear.clear();
                    main.registrasi();
                    break;
                case 2:
                    clear.clear();
                    main.login();
                    break;
                default:
                    break;
            }
        }while(pilih>=1 && pilih <= 2);
        
    }

    /**
     * Metode untuk melakukan proses registrasi akun sebagai Admin atau Customer.
     * Meminta pengguna memilih peran (Admin atau Customer) dan mengumpulkan informasi akun.
     * Data akun disimpan ke dalam file sesuai peran.
     */
    public void registrasi(){
        Scanner scan = new Scanner(System.in);
        int regis;
        ClearConsole clear = new ClearConsole();
        ArrayList<Akun> listAkun = new ArrayList<Akun>();

        System.out.println("Anda ingin registrasi sebagai...");
        System.out.println("================================");
        System.out.println("[1] Admin");
        System.out.println("[2] Customer");
        System.out.println("================================");
        System.out.print("Pilih => ");
        regis = scan.nextInt();

        scan.nextLine();

        switch (regis) {
            case 1:
                clear.clear();
                String pathDatabaseAdmin = "assets/akun/Admin/admin.txt";
                BufferedWriter databaseAdmin = null;
                this.bacaDatabaseAkun(pathDatabaseAdmin, listAkun, regis);
                try {
                    databaseAdmin = new BufferedWriter(new FileWriter(pathDatabaseAdmin));

                    System.out.println("Registrasi Admin");
                    System.out.println("================");
                    System.out.print("ID : ");
                    String id = scan.nextLine();
                    System.out.print("Password : ");
                    String password = scan.nextLine();

                    Akun akun = new Admin(id,password);

                    listAkun.add(akun);

                    for(Akun i : listAkun){
                        databaseAdmin.write(i.getId()+" ");
                        databaseAdmin.write(i.getPass());
                        databaseAdmin.newLine();
                    }

                    listAkun.clear();
                    clear.clear();
                    System.out.println("Akun Anda Berhasil di Buat");
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    try{
                        if(databaseAdmin!=null){
                            databaseAdmin.close();
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                clear.clear();
                String pathDatabaseCustomer = "assets/akun/Customer/customer.txt";
                BufferedWriter databaseCustomer = null;
                this.bacaDatabaseAkun(pathDatabaseCustomer, listAkun, regis);
                try {
                    databaseCustomer = new BufferedWriter(new FileWriter(pathDatabaseCustomer));

                    System.out.println("Registrasi Customer");
                    System.out.println("===================");
                    System.out.print("ID : ");
                    String id = scan.nextLine();
                    System.out.print("Password : ");
                    String password = scan.nextLine();

                    Akun akun = new Customer(id,password);
                    
                    listAkun.add(akun);
                    
                    clear.clear();
                    
                    this.createFileForCustomer(akun.getId(),databaseCustomer,listAkun);

                    listAkun.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally{
                    try{
                        if(databaseCustomer!=null){
                            databaseCustomer.close();
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    /**
     * Metode untuk melakukan proses login sebagai Admin atau Customer.
     * Meminta pengguna memilih peran (Admin atau Customer), kemudian memvalidasi ID dan password.
     * Jika login berhasil, pengguna diarahkan ke driver sesuai peran (AdminDriver atau CustomerDriver).
     */
    public void login(){
        Scanner scan = new Scanner(System.in);
        ClearConsole clear = new ClearConsole();
        
        String id;
        String password;
        String pathDatabaseAdmin = "assets/akun/Admin/admin.txt";
        String pathDatabaseCustomer = "assets/akun/Customer/customer.txt";
        BufferedReader databaseAkun = null;

        System.out.println("Login Sebagai");
        System.out.println("=============");
        System.out.println("[1] Admin");
        System.out.println("[2] Cutomer");
        System.out.println("=============");
        System.out.print("Pilih => ");
        int pilih = scan.nextInt();

        scan.nextLine();

        switch (pilih) {
            case 1:
                clear.clear();
                System.out.println("Login Admin");
                System.out.print("ID\t\t: ");
                id = scan.nextLine();
                System.out.print("Password\t: ");
                password = scan.nextLine();

                System.out.println("\n");
                System.out.print("login.............0%");

                for(int i = 1 ; i <= 5 ; i++ ){
                    try {
                        Thread.sleep(250);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.print("\rlogin............."+i*20+"%");
                }

                try {
                    databaseAkun = new BufferedReader(new FileReader(pathDatabaseAdmin));
                    String bacaDatabase;
                    boolean loginStatus = false;
                    while((bacaDatabase = databaseAkun.readLine()) != null){
                        String[] dataAkun = bacaDatabase.split(" ");
                        Admin admin = new Admin(dataAkun[0],dataAkun[1]);

                        if(id.equals(admin.getId()) && password.equals(admin.getPass())){
                            loginStatus = true;
                            clear.clear();
                            AdminDriver adminDriver = new AdminDriver(admin);
                            
                            adminDriver.menu();
                            
                            break;
                        }
                    }

                    if(loginStatus==false){
                        clear.clear();
                        System.out.println("\nID atau PASSWORD anda salah");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally{
                    try{
                        if(databaseAkun!=null){
                            databaseAkun.close();
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }

                break;
            case 2:
                clear.clear();
                System.out.println("Login Customer");
                System.out.print("ID\t\t: ");
                id = scan.nextLine();
                System.out.print("Password\t: ");
                password = scan.nextLine();

                System.out.println("\n");
                System.out.print("login.............0%");

                for(int i = 1 ; i <= 5 ; i++ ){
                    try {
                        Thread.sleep(250);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.print("\rlogin............."+i*20+"%");
                }

                try {
                    databaseAkun = new BufferedReader(new FileReader(pathDatabaseCustomer));
                    String bacaDatabase;
                    boolean loginStatus = false;

                    while((bacaDatabase = databaseAkun.readLine()) != null){
                        String[] dataAkun = bacaDatabase.split(" ");
                        Customer customer = new Customer(dataAkun[0],dataAkun[1]);
                        if(id.equals(customer.getId()) && password.equals(customer.getPass())){
                            loginStatus = true;
                            clear.clear();
                            CustomerDriver customerDriver = new CustomerDriver(customer);
                            customerDriver.menu();
                            break;
                        }
                    }
                    if(loginStatus==false){
                        clear.clear();
                        System.out.println("\nID atau PASSWORD anda salah");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally{
                    try{
                        if(databaseAkun!=null){
                            databaseAkun.close();
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }

    }

    /**
     * Metode untuk membaca data akun dari file sesuai peran (Admin atau Customer).
     * Data akun dibaca dan disimpan ke dalam ArrayList<Akun> untuk digunakan dalam proses registrasi.
     *
     * @param pathDatabase Lokasi file database akun.
     * @param dataAkun     ArrayList untuk menyimpan objek Akun.
     * @param pilih        Pilihan peran (1 untuk Admin, 2 untuk Customer).
     */
    private void bacaDatabaseAkun(String pathDatabase, ArrayList<Akun> dataAkun, int pilih){
        BufferedReader databaseAkun = null;
        switch (pilih) {
            case 1:
                try {

                    databaseAkun = new BufferedReader(new FileReader(pathDatabase));
                    String bacaDataBaseAkun;
                    while ((bacaDataBaseAkun = databaseAkun.readLine())!=null) {
                        String[] data = bacaDataBaseAkun.split(" ");
                        Akun akun = new Admin(data[0],data[1]);
                        dataAkun.add(akun);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }finally{
                    try {
                        if(databaseAkun!=null){
                            databaseAkun.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case 2:
                try {

                    databaseAkun = new BufferedReader(new FileReader(pathDatabase));
                    String bacaDataBaseAkun;
                    while ((bacaDataBaseAkun = databaseAkun.readLine())!=null) {
                        String[] data = bacaDataBaseAkun.split(" ");
                        Akun akun = new Customer(data[0],data[1]);
                        dataAkun.add(akun);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }finally{
                    try {
                        if(databaseAkun!=null){
                            databaseAkun.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }    
    }

    /**
     * Metode untuk membuat direktori dan file-file khusus untuk setiap customer setelah registrasi.
     * Direktori customer dibuat, dan file keranjang dan invoice dibuat di dalamnya.
     * Jika pembuatan berhasil, data akun disimpan ke dalam file database customer.
     *
     * @param idCustomer        ID customer yang baru diregistrasi.
     * @param databaseCustomer  BufferedWriter untuk menyimpan data akun customer ke file.
     * @param listAkun          ArrayList yang berisi objek Akun customer yang baru diregistrasi.
     */
    private void createFileForCustomer(String idCustomer, BufferedWriter databaseCustomer, ArrayList<Akun> listAkun){
        String pathDirectoryCustomer = "assets/database-customer/"+idCustomer;
        String pathKerangjangCustomer = "assets/database-customer/"+idCustomer+"/"+idCustomer+"-keranjang.txt";
        String pathInvoiceCustomer = "assets/database-customer/"+idCustomer+"/"+idCustomer+"-invoice.txt";

        try {

            File direcory = new File(pathDirectoryCustomer);

            if(direcory.mkdir()){
                System.out.println("AKUN BERHASIL DI BUAT");
            }
            else{
                System.out.println("ID INI SUDAH DIGUNAKAN CUSTOMER LAIN");
                listAkun.remove(listAkun.size()-1);
            }
            
            
            File keranjangCustomer = new File(pathKerangjangCustomer);
            File invoiceCustomer = new File(pathInvoiceCustomer);

            if(keranjangCustomer.createNewFile() && invoiceCustomer.createNewFile()){

                for(Akun i : listAkun){
                        databaseCustomer.write(i.getId()+" ");
                        databaseCustomer.write(i.getPass());
                        databaseCustomer.newLine();
                    }
            }
            else{
                for(Akun i : listAkun){
                        databaseCustomer.write(i.getId()+" ");
                        databaseCustomer.write(i.getPass());
                        databaseCustomer.newLine();
                    }
            }
        } catch (IOException e) {
            System.out.println("Terjadi masalah : " + e.getMessage());
            e.printStackTrace();
        }
    }
}