public class Pengumpulan {

    private Mahasiswa mahasiswa;
    private Tugas tugas;
    private String tanggal;
    private String status;

    public Pengumpulan(Mahasiswa mahasiswa, Tugas tugas, String tanggal, String status) {
        this.mahasiswa = mahasiswa;
        this.tugas = tugas;
        this.tanggal = tanggal;
        this.status = status;
    }

    public void tampilkan() {
        System.out.println("Mahasiswa : " + mahasiswa.getNama());
        System.out.println("Tugas     : " + tugas.getJudul());
        System.out.println("Tanggal   : " + tanggal);
        System.out.println("Status    : " + status);
        System.out.println("---------------------------");
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
    }

    public Tugas getTugas() {
        return tugas;
    }

    public void setTugas(Tugas tugas) {
        this.tugas = tugas;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}