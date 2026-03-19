import java.util.Scanner;

public class SistemPengumpulan {

private Tugas[] daftarTugas = new Tugas[50];
private Pengumpulan[] daftarPengumpulan = new Pengumpulan[100];

private int jumlahTugas = 0;
private int jumlahPengumpulan = 0;

Scanner input = new Scanner(System.in);

public SistemPengumpulan() {

    daftarTugas[0] = new Tugas("T01", "OOP Java", "2026-06-01");
    daftarTugas[1] = new Tugas("T02", "Class Diagram", "2026-06-05");

    jumlahTugas = 2;

}

    public void menuDosen() {

        while (true) {

            System.out.println("\n=== MENU DOSEN ===");
            System.out.println("1. Tambah Tugas");
            System.out.println("2. Lihat Pengumpulan");
            System.out.println("3. Daftar Tugas");
            System.out.println("4. Kembali");
            System.out.print("Pilih: ");

            int pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {

                case 1 -> tambahTugas();
                case 2 -> lihatPengumpulan();
                case 3 -> daftarTugas();
                case 4 -> { return; }

                default -> System.out.println("Menu tidak tersedia");
            }

        }

    }

    public void tambahTugas() {

        System.out.print("ID Tugas: ");
        String id = input.nextLine();

        System.out.print("Judul: ");
        String judul = input.nextLine();

        System.out.print("Deadline: ");
        String deadline = input.nextLine();

        daftarTugas[jumlahTugas] = new Tugas(id, judul, deadline);
        jumlahTugas++;

        System.out.println("Tugas berhasil dibuat!");

    }

    public void lihatPengumpulan() {

        if ( jumlahPengumpulan == 0) {
                System.out.println("Belum ada pengumpulan tugas.");
                return;
            }

        for (int i = 0; i < jumlahPengumpulan; i++) {
            daftarPengumpulan[i].tampilkan();
        }

    }

    public Tugas[] getDaftarTugas() {
        return daftarTugas;
    }

    public void setDaftarTugas(Tugas[] daftarTugas) {
        this.daftarTugas = daftarTugas;
    }

    public Pengumpulan[] getDaftarPengumpulan() {
        return daftarPengumpulan;
    }

    public void setDaftarPengumpulan(Pengumpulan[] daftarPengumpulan) {
        this.daftarPengumpulan = daftarPengumpulan;
    }

    public int getJumlahPengumpulan() {
        return jumlahPengumpulan;
    }

    public void setJumlahPengumpulan(int jumlahPengumpulan) {
        this.jumlahPengumpulan = jumlahPengumpulan;
    }

    public void menuMahasiswa() {

            System.out.print("Masukkan NIM: ");
            String nim = input.nextLine();

            while (true) {

                System.out.println("\n=== MENU MAHASISWA ===");
                System.out.println("1. Lihat Tugas");
                System.out.println("2. Kumpulkan Tugas");
                System.out.println("3. Kembali");
                System.out.print("Pilih: ");

                int pilih = input.nextInt();
                input.nextLine();

                switch (pilih) {

                    case 1 -> lihatTugasMahasiswa(nim);

                    case 2 -> kumpulkanTugas(nim);

                    case 3 -> { return; }

                    default -> System.out.println("Menu tidak tersedia");

                }

            }

    }

    public void lihatTugasMahasiswa(String nim) {

        if (jumlahTugas == 0) {
            System.out.println("Belum ada tugas dari dosen.");
            return;
        }

        for (int i = 0; i < jumlahTugas; i++) {

            boolean sudahKumpul = false;

            for (int j = 0; j < jumlahPengumpulan; j++) {

                if (daftarPengumpulan[j].getMahasiswa().getId().equals(nim)
                        && daftarPengumpulan[j].getTugas().getIdTugas()
                        .equals(daftarTugas[i].getIdTugas())) {

                    sudahKumpul = true;

                }

            }

            if (!sudahKumpul) {

                System.out.println("--------------------------------");
                System.out.println("ID       : " + daftarTugas[i].getIdTugas());
                System.out.println("Judul    : " + daftarTugas[i].getJudul());
                System.out.println("Deadline : " + daftarTugas[i].getDeadline());
                System.out.println("--------------------------------");

            }

        }

    }

    public void kumpulkanTugas(String nim) {

        System.out.print("Nama: ");
        String nama = input.nextLine();

        lihatTugasMahasiswa(nim);

        System.out.print("Pilih ID Tugas: ");
        String id = input.nextLine();

        System.out.print("Tanggal Kumpul: ");
        String tanggal = input.nextLine();

        for (int i = 0; i < jumlahTugas; i++) {

            if (daftarTugas[i].getIdTugas().equals(id)) {

                Mahasiswa m = new Mahasiswa(nim, nama);

                daftarPengumpulan[jumlahPengumpulan] =
                        new Pengumpulan(m, daftarTugas[i], tanggal);

                jumlahPengumpulan++;

                System.out.println("Tugas berhasil dikumpulkan!");
                return;

            }

        }

        System.out.println("ID Tugas tidak ditemukan.");

    }

    public void daftarTugas() {

        if (jumlahTugas == 0) {
            System.out.println("Belum ada tugas.");
            return;
        }

        System.out.println("\n=== DAFTAR TUGAS ===");
        System.out.println("ID   Judul              Deadline       Terkumpul");
        System.out.println("------------------------------------------------");

        for (int i = 0; i < jumlahTugas; i++) {

            int jumlahKumpul = 0;

            for (int j = 0; j < jumlahPengumpulan; j++) {

                if (daftarPengumpulan[j].getTugas().getIdTugas()
                        .equals(daftarTugas[i].getIdTugas())) {

                    jumlahKumpul++;

                }

            }

            System.out.printf("%-4s %-18s %-14s %-5d\n",
                    daftarTugas[i].getIdTugas(),
                    daftarTugas[i].getJudul(),
                    daftarTugas[i].getDeadline(),
                    jumlahKumpul);

        }

    }
}


