public class Tugas {

private String idTugas;
private String judul;
private String deadline;

public Tugas(String idTugas, String judul, String deadline) {
    this.idTugas = idTugas;
    this.judul = judul;
    this.deadline = deadline;
}

public String getIdTugas() {
    return idTugas;
}

public String getJudul() {
    return judul;
}

public String getDeadline() {
    return deadline;
}

    public void setIdTugas(String idTugas) {
        this.idTugas = idTugas;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

}
