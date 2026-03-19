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

                    case 1 -> {
                        System.out.println("\n=== LOGIN DOSEN ===");

                        System.out.print("Username: ");
                        String user = input.nextLine();

                        System.out.print("Password: ");
                        String pass = input.nextLine();

                        if (user.equals("dosen") && pass.equals("123")) {
                            System.out.println("Login berhasil!");
                            sistem.menuDosen();
                        } else {
                            System.out.println("Login gagal!");
                        }
                    }

                    case 2 -> {
                        System.out.println("\nLogin Mahasiswa");
                        sistem.menuMahasiswa();
                    }

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