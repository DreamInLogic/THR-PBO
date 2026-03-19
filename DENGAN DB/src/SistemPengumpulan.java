import java.sql.*;
import java.util.Scanner;

public class SistemPengumpulan {

    Scanner input = new Scanner(System.in);

    public void menuDosen() {

        while (true) {

            System.out.println("\n=== MENU DOSEN ===");
            System.out.println("1. Tambah Tugas");
            System.out.println("2. Lihat Tugas");
            System.out.println("3. Edit Tugas");
            System.out.println("4. Hapus Tugas");
            System.out.println("5. Lihat Pengumpulan"); 
            System.out.println("6. Statistik");         
            System.out.println("7. Hapus Pengumpulan");
            System.out.println("8. Reset Data (Hanya Darurat)");
            System.out.println("9. Kembali");
            System.out.print("Pilih: ");

            int pilih = input.nextInt();
            input.nextLine();

            switch (pilih) {

                case 1 -> tambahTugasDB();
                case 2 -> lihatTugasDB();
                case 3 -> editTugasDB();
                case 4 -> hapusTugasDB();
                case 5 -> lihatPengumpulanDB(); 
                case 6 -> statistikDB();        
                case 7 -> hapusPengumpulanDB();
                case 8 -> resetDatabase();
                case 9 -> { return; }

            }
        }
    }

    public void tambahTugasDB() {

        System.out.print("ID: ");
        String id = input.nextLine();

        System.out.print("Judul: ");
        String judul = input.nextLine();

        System.out.print("Deadline: ");
        String deadline = input.nextLine();

        try {
            Connection conn = DatabaseConnection.connect();
            String sql = "INSERT INTO tugas VALUES (?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, judul);
            ps.setString(3, deadline);

            ps.executeUpdate();

            System.out.println("Tugas berhasil ditambah!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void lihatTugasDB() {

        try {
            Connection conn = DatabaseConnection.connect();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM tugas");

            System.out.println("==========================================");
            System.out.println("ID    | JUDUL            | DEADLINE");
            System.out.println("==========================================");

            while (rs.next()) {
                System.out.printf("%-5s | %-15s | %s\n",
                    rs.getString("id_tugas"),
                    rs.getString("judul"),
                    rs.getString("deadline")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void menuMahasiswa() {

        System.out.print("Masukkan NIM: ");
        String nim = input.nextLine();

        System.out.print("Masukkan Nama: ");
        String nama = input.nextLine();

        simpanMahasiswa(nim, nama);

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
                case 2 -> kumpulkanTugasDB(nim);
                case 3 -> { return; }

            }
        }
    }

    public void simpanMahasiswa(String nim, String nama) {

        try {
            Connection conn = DatabaseConnection.connect();

            String check = "SELECT * FROM mahasiswa WHERE nim=?";
            PreparedStatement psCheck = conn.prepareStatement(check);
            psCheck.setString(1, nim);

            ResultSet rs = psCheck.executeQuery();

            if (!rs.next()) {

                String sql = "INSERT INTO mahasiswa VALUES (?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);

                ps.setString(1, nim);
                ps.setString(2, nama);

                ps.executeUpdate();

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void lihatTugasMahasiswa(String nim) {

        try {
            Connection conn = DatabaseConnection.connect();

            String sql = """
                SELECT * FROM tugas t
                WHERE NOT EXISTS (
                    SELECT 1 FROM pengumpulan p 
                    WHERE p.id_tugas = t.id_tugas AND p.nim = ?
                )
            """;

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nim);

            ResultSet rs = ps.executeQuery();

            boolean ada = false;

            System.out.println("==========================================");
            System.out.println("ID    | JUDUL            | DEADLINE");
            System.out.println("==========================================");

            while (rs.next()) {
                ada = true;
                System.out.printf("%-5s | %-15s | %s\n",
                    rs.getString("id_tugas"),
                    rs.getString("judul"),
                    rs.getString("deadline")
                );
            }

            if (!ada) {
                System.out.println("Semua tugas sudah dikumpulkan 🎉");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void kumpulkanTugasDB(String nim) {

        lihatTugasMahasiswa(nim);

        System.out.print("Pilih ID Tugas: ");
        String id = input.nextLine();

        System.out.print("Tanggal Kumpul: ");
        String tanggal = input.nextLine();

        try {
            Connection conn = DatabaseConnection.connect();

            // CEK DOUBLE SUBMIT
            String check = "SELECT * FROM pengumpulan WHERE nim=? AND id_tugas=?";
            PreparedStatement psCheck = conn.prepareStatement(check);
            psCheck.setString(1, nim);
            psCheck.setString(2, id);

            ResultSet rs = psCheck.executeQuery();

            if (rs.next()) {
                System.out.println("Tugas sudah dikumpulkan!");
                return;
            }

            // Ambil deadline
            String getDeadline = "SELECT deadline FROM tugas WHERE id_tugas=?";
            PreparedStatement psDeadline = conn.prepareStatement(getDeadline);
            psDeadline.setString(1, id);

            ResultSet rd = psDeadline.executeQuery();

            if (!rd.next()) {
                System.out.println("Tugas tidak ditemukan!");
                return;
            }

            String deadline = rd.getString("deadline");

            // ✅ STATUS
            String status = (tanggal.compareTo(deadline) <= 0)
                    ? "Tepat Waktu"
                    : "Terlambat";

            // 💾 INSERT
            String sql = "INSERT INTO pengumpulan(nim,id_tugas,tanggal_kumpul,status) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, nim);
            ps.setString(2, id);
            ps.setString(3, tanggal);
            ps.setString(4, status);

            ps.executeUpdate();

            System.out.println("Tugas berhasil dikumpulkan!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void lihatPengumpulanDB() {

        try {
            Connection conn = DatabaseConnection.connect();

            String sql = """
                SELECT p.id, m.nama, t.judul, p.tanggal_kumpul, p.status
                FROM pengumpulan p
                JOIN mahasiswa m ON p.nim = m.nim
                JOIN tugas t ON p.id_tugas = t.id_tugas
            """;

            ResultSet rs = conn.createStatement().executeQuery(sql);

            System.out.println("======================================================================");
            System.out.println("ID | NAMA        | TUGAS           | TANGGAL   | STATUS");
            System.out.println("======================================================================");

            while (rs.next()) {
                System.out.printf("%-3d | %-10s | %-15s | %-10s | %s\n",
                    rs.getInt("id"),              // ✅ DI SINI
                    rs.getString("nama"),
                    rs.getString("judul"),
                    rs.getString("tanggal_kumpul"),
                    rs.getString("status")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void statistikDB() {

        try {
            Connection conn = DatabaseConnection.connect();

            String sql = """
                SELECT t.judul, COUNT(p.id_tugas) as jumlah
                FROM tugas t
                LEFT JOIN pengumpulan p ON t.id_tugas = p.id_tugas
                GROUP BY t.id_tugas
            """;

            ResultSet rs = conn.createStatement().executeQuery(sql);

            System.out.println("\n=== STATISTIK ===");

            while (rs.next()) {
                System.out.println(
                    rs.getString("judul") + " -> " +
                    rs.getInt("jumlah") + " mahasiswa"
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void editTugasDB() {
        try {
            Connection conn = DatabaseConnection.connect();

            System.out.print("Masukkan ID Tugas: ");
            String id = input.nextLine();

            System.out.print("Judul Baru: ");
            String judul = input.nextLine();

            System.out.print("Deadline Baru: ");
            String deadline = input.nextLine();

            String sql = "UPDATE tugas SET judul=?, deadline=? WHERE id_tugas=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, judul);
            ps.setString(2, deadline);
            ps.setString(3, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Tugas berhasil diupdate!");
            } else {
                System.out.println("ID tidak ditemukan!");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void hapusTugasDB() {
        try {
            Connection conn = DatabaseConnection.connect();

            System.out.print("Masukkan ID Tugas: ");
            String id = input.nextLine();

            String hapusPengumpulan = "DELETE FROM pengumpulan WHERE id_tugas=?";
            PreparedStatement ps1 = conn.prepareStatement(hapusPengumpulan);
            ps1.setString(1, id);
            ps1.executeUpdate();

            String sql = "DELETE FROM tugas WHERE id_tugas=?";
            PreparedStatement ps2 = conn.prepareStatement(sql);
            ps2.setString(1, id);

            int result = ps2.executeUpdate();

            if (result > 0) {
                System.out.println("Tugas berhasil dihapus!");
            } else {
                System.out.println("ID tidak ditemukan!");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void hapusPengumpulanDB() {
        try {
            Connection conn = DatabaseConnection.connect();

            System.out.print("Masukkan ID Pengumpulan: ");
            int id = input.nextInt();
            input.nextLine();

            String sql = "DELETE FROM pengumpulan WHERE id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Pengumpulan berhasil dihapus!");
            } else {
                System.out.println("ID tidak ditemukan!");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void resetDatabase() {
        
        try {
            Connection conn = DatabaseConnection.connect();

            // ❌ HAPUS SEMUA DATA
            conn.createStatement().executeUpdate("DELETE FROM pengumpulan");
            conn.createStatement().executeUpdate("DELETE FROM mahasiswa");
            conn.createStatement().executeUpdate("DELETE FROM tugas");

            // ✅ RESET AUTO INCREMENT (BIAR ID MULAI DARI 1 LAGI)
            conn.createStatement().executeUpdate("ALTER TABLE pengumpulan AUTO_INCREMENT = 1");

            // ✅ INSERT DATA TUGAS
            conn.createStatement().executeUpdate("""
                INSERT INTO tugas VALUES
                ('T01','Laporan PBO','2026-04-01'),
                ('T02','Class Diagram','2026-04-05'),
                ('T03','Use Case Diagram','2026-04-07'),
                ('T04','Implementasi Java','2026-04-10')
            """);

            // ✅ INSERT MAHASISWA
            conn.createStatement().executeUpdate("""
                INSERT INTO mahasiswa VALUES
                ('250215030','Sunu Setyo Jati'),
                ('250215031','Andi Saputra'),
                ('250215032','Budi Santoso'),
                ('250215033','Citra Lestari')
            """);

            // ✅ INSERT PENGUMPULAN
            conn.createStatement().executeUpdate("""
                INSERT INTO pengumpulan(nim,id_tugas,tanggal_kumpul,status) VALUES
                ('250215030','T01','2026-03-30','Tepat Waktu'),
                ('250215031','T01','2026-04-02','Terlambat'),
                ('250215032','T02','2026-04-04','Tepat Waktu'),
                ('250215033','T03','2026-04-08','Terlambat')
            """);

            System.out.println("Database berhasil di-reset!");

        } catch (SQLException e) {
            System.out.println("Error reset: " + e.getMessage());
        }
    }
}