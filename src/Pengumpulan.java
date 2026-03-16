public class Pengumpulan {

private Mahasiswa mahasiswa;
private Tugas tugas;
private String tanggalKumpul;
private String status;

public Pengumpulan(Mahasiswa mahasiswa, Tugas tugas, String tanggalKumpul) {

    this.mahasiswa = mahasiswa;
    this.tugas = tugas;
    this.tanggalKumpul = tanggalKumpul;

    if (tanggalKumpul.compareTo(tugas.getDeadline()) <= 0) {
        status = "Tepat Waktu";
    } else {
        status = "Terlambat";
    }

}

public void tampilkan() {

    System.out.println("Mahasiswa : " + mahasiswa.getNama());
    System.out.println("Tugas     : " + tugas.getJudul());
    System.out.println("Deadline  : " + tugas.getDeadline());
    System.out.println("Tanggal   : " + tanggalKumpul);
    System.out.println("Status    : " + status);
    System.out.println("==========================");
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

    public String getTanggalKumpul() {
        return tanggalKumpul;
    }

    public void setTanggalKumpul(String tanggalKumpul) {
        this.tanggalKumpul = tanggalKumpul;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
