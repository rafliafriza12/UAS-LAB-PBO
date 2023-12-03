import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
/**
 * Kelas ListBarang merepresentasikan daftar barang yang dapat dikelola, termasuk
 * metode untuk memuat barang dari file, menampilkan, menambah, menghapus, dan mengedit barang.
 * Kelas ini juga menyimpan perubahan ke dalam file setelah operasi-edit dilakukan.
 *
 * <p>Daftar barang disimpan dalam bentuk ArrayList dari objek Barang.
 * Barang adalah kelas yang memiliki atribut IdBarang, NamaBarang, HargaBarang, dan Stok.
 *
 * @author Rafli Afriza Nugraha
 * @version 1.0
 */
public class ListBarang {
    private ArrayList<Barang> barang;
    
    /**
     * Konstruktor untuk membuat objek ListBarang dengan inisialisasi ArrayList barang.
     */
    public ListBarang(){
        barang = new ArrayList<Barang>();
    }

    /**
     * Mendapatkan daftar barang.
     *
     * @return ArrayList dari objek Barang.
     */
    public ArrayList<Barang> getBarang() {
        return barang;
    }

    /**
     * Memuat daftar barang dari file ke dalam ArrayList barang.
     * File yang dibaca berlokasi di "assets/barang/barang.txt".
     */
    public void loadBarangFromFile() {
        String pathFile = "assets/barang/barang.txt";
        BufferedReader fileBarang = null;
        try {
            fileBarang = new BufferedReader(new FileReader(pathFile));
            String bacaBaris;
            // boolean loop = true;
                while ((bacaBaris = fileBarang.readLine()) != null) {
                    String[] detailBarang = bacaBaris.split(" ");
                    Barang objBarang = new Barang();

                    objBarang.setIdBarang(Integer.parseInt(detailBarang[0]));
                    objBarang.setNamaBarang(detailBarang[1]);
                    objBarang.setHargaBarang(Integer.parseInt(detailBarang[2]));
                    objBarang.setStok(Integer.parseInt(detailBarang[3]));

                    barang.add(objBarang);
                }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileBarang != null) {
                    fileBarang.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Menampilkan daftar barang ke konsol.
     * Daftar barang diurutkan berdasarkan IdBarang.
     */
    public void tampilkanBarang(){
        this.loadBarangFromFile();

        this.sorting();
        System.out.println("--------------List Barang--------------\n");
        this.barang.forEach((i) -> {
            System.out.println("Id Barang\t: "+i.getIdBarang());
            System.out.println("Nama Barang\t: "+i.getNamaBarang());
            System.out.println("Harga\t\t: "+i.getHargaBarang());
            System.out.println("Stok\t\t: "+i.getStok());
            System.out.println("\n");
        });

        this.barang.clear();
    }

    /**
     * Menambahkan barang baru ke dalam daftar barang.
     * Pengguna diminta untuk memasukkan Id, Nama, Harga, dan Stok barang.
     * Setelah penambahan, daftar barang diurutkan berdasarkan IdBarang.
     */
    public void tambahBarang(){
        ClearConsole clear = new ClearConsole();
        this.loadBarangFromFile();

        Barang tambahBarang = new Barang();
        Scanner scan = new Scanner(System.in);

        System.out.println("Tambah Barang");
        System.out.print("Id barang\t: ");
        String idBarang = scan.nextLine();
        System.out.print("Nama Barang\t: ");
        String namaBarang = scan.nextLine();
        System.out.print("Harga Barang\t: ");
        String hargaBarang = scan.nextLine();
        System.out.print("Stok Barang\t: ");
        String stokBarang = scan.nextLine();
        System.out.println("\n");

        tambahBarang.setIdBarang(Integer.parseInt(idBarang));
        tambahBarang.setNamaBarang(namaBarang);
        tambahBarang.setHargaBarang(Integer.parseInt(hargaBarang));
        tambahBarang.setStok(Integer.parseInt(stokBarang));


        this.barang.add(tambahBarang);

        this.sorting();

        writeToFile();

        this.barang.clear();
        clear.clear();
        System.out.println(tambahBarang.getNamaBarang()+" Berhasil ditambahkan");
    }

    /**
     * Menghapus barang dari daftar berdasarkan IdBarang yang dimasukkan pengguna.
     * Setelah penghapusan, daftar barang diurutkan berdasarkan IdBarang.
     */
    public void hapusBarang(){
        ClearConsole clear = new ClearConsole();
        this.loadBarangFromFile();

        Scanner scan = new Scanner(System.in);
        int index = 0;

        System.out.print("Masukkan id barang yang ingin dihapus : ");
        int hapus = scan.nextInt();

        for(int i = 0; i < this.barang.size();i++){
            if(hapus == this.barang.get(i).getIdBarang()){
                index = i;
                this.barang.remove(index);
                clear.clear();
                System.out.println("Barang Berhasil Dihapus");
                break;
            }
            else if((i==this.barang.size()-1)&& index==0){
                clear.clear();
                System.out.println("Barang Tidak Ditemukan");
                break;
            }
        }

        this.sorting();

        this.writeToFile();

        this.barang.clear();
    }

    /**
     * Mengedit atribut-atribut barang dalam daftar berdasarkan IdBarang yang dimasukkan pengguna.
     * Setelah pengeditan, daftar barang diurutkan berdasarkan IdBarang.
     */
    public void editBarang(){
        this.loadBarangFromFile();
        ClearConsole clear = new ClearConsole();
        Scanner scan = new Scanner(System.in);
        int index = 0;
        Barang editBarang = new Barang();

        System.out.print("Masukkan id barang yang ingin di edit : ");
        int edit = scan.nextInt();

        scan.nextLine();

        for(int i = 0 ; i < this.barang.size();i++){
            if(edit == this.barang.get(i).getIdBarang()){
                index = i;
                System.out.print("Masukkan nama barang baru : ");
                String namaBarangBaru = scan.nextLine();
                
                System.out.print("Masukkan harga baru : ");
                String hargaBaru = scan.nextLine();
        
                System.out.print("Masukkan stok baru : ");
                String stokBaru = scan.nextLine();
        
                editBarang.setIdBarang(edit);
                editBarang.setNamaBarang(namaBarangBaru);
                editBarang.setHargaBarang(Integer.parseInt(hargaBaru));
                editBarang.setStok(Integer.parseInt(stokBaru));
                this.barang.set(index, editBarang);
                clear.clear();
                System.out.println("Barang berhasil di Edit");
                break;
            }
            else if((i==this.barang.size()-1)&& index==0){
                clear.clear();
                System.out.println("Barang Tidak Ditemukan");
                break;
            }
        }   

        this.sorting();

        this.writeToFile();

        this.barang.clear();
    }

    /**
     * Menyimpan perubahan daftar barang ke dalam file "assets/barang/barang.txt".
     * Setelah penulisan, daftar barang diurutkan berdasarkan IdBarang.
     */
    public void writeToFile(){
        String pathFileBarang = "assets/barang/barang.txt";
        BufferedWriter fileBarang = null;

        try{
            fileBarang = new BufferedWriter(new FileWriter(pathFileBarang));
    
            for(Barang i : this.barang){
                fileBarang.write(i.getIdBarang()+" ");
                fileBarang.write(i.getNamaBarang()+" ");
                fileBarang.write(i.getHargaBarang()+" ");
                fileBarang.write(String.valueOf(i.getStok()));
                fileBarang.newLine();
            }
    
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(fileBarang!=null){
                    fileBarang.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }   
    }

    /**
     * Melakukan pengurutan daftar barang berdasarkan IdBarang menggunakan algoritma Bubble Sort.
     */
    private void sorting(){
        for(int i = 0; i < this.barang.size(); i++){
            for(int j = 0; j < this.barang.size()-i-1;j++){
                if(this.barang.get(j).getIdBarang() > this.barang.get(j+1).getIdBarang()){
                    Barang temp = this.barang.get(j);
                    Barang temp1 = this.barang.get(j+1);
                    this.barang.set(j, temp1);
                    this.barang.set(j+1, temp);
                }
            }
        }
    }
}