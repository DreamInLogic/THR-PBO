import java.util.Scanner;

public class Main {

public static void main(String[] args) {

    try (Scanner input = new Scanner(System.in)) {
        SistemPengumpulan sistem = new SistemPengumpulan();

        while (true) {

            System.out.println("\n=== SISTEM PENGUMPULAN TUGAS ===");
            System.out.println("1. Login Dosen");
            System.out.println("2. Login Mahasiswa");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");

            int pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {

                case 1 -> sistem.menuDosen();

                case 2 -> sistem.menuMahasiswa();

                case 3 -> {
                    System.out.println("Program selesai.");
                    return;
                }

                default -> System.out.println("Menu tidak tersedia");

            }

        }
    }

}

}
