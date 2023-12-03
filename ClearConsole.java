/**
 * Kelas ClearConsole digunakan untuk membersihkan tampilan konsol sesuai dengan sistem operasi yang digunakan.
 * Metode clear() memeriksa sistem operasi yang digunakan dan membersihkan tampilan konsol.
 */
public class ClearConsole {
    /**
     * Metode clear() membersihkan tampilan konsol sesuai dengan sistem operasi yang digunakan.
     * Jika sistem operasi adalah Unix/Linux/MacOS, tampilan konsol akan dibersihkan menggunakan perintah "clear".
     * Jika sistem operasi adalah Windows, tampilan konsol akan dibersihkan menggunakan perintah "cls".
     * Jika sistem operasi tidak didukung, pesan "Unsupported operating system" akan ditampilkan.
     */
    public void clear() {
        String os = System.getProperty("os.name").toLowerCase();

        try {
            if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
                // Unix/Linux/MacOS
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } else if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.println("Unsupported operating system");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
