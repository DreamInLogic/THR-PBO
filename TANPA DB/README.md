# Sistem Pengumpulan Tugas - PBO

Program Java sederhana untuk memenuhi **Tugas 1 Pemrograman Berorientasi Objek (PBO)**.

Program ini mensimulasikan sistem pengumpulan tugas praktikum antara **dosen dan mahasiswa**.

---

## Deskripsi Sistem

Sistem ini memungkinkan:

- Dosen menambahkan tugas
- Mahasiswa mengumpulkan tugas
- Sistem menentukan apakah tugas **tepat waktu atau terlambat**
- Melihat daftar pengumpulan tugas

Program dijalankan melalui **menu interaktif di terminal**.

---

## Konsep PBO yang Digunakan

Program ini mengimplementasikan beberapa konsep dasar PBO:

- Class dan Object
- Inheritance
- Enkapsulasi
- Method
- Percabangan (if / switch)
- Perulangan (looping)
- Input dari pengguna
- Array untuk penyimpanan data

---

## Struktur Class


User
├── Mahasiswa
└── Dosen

Tugas
Pengumpulan
SistemPengumpulan
Main


Penjelasan:

- **User** → class dasar pengguna
- **Mahasiswa** → turunan dari User
- **Dosen** → turunan dari User
- **Tugas** → menyimpan data tugas
- **Pengumpulan** → menyimpan data pengumpulan tugas
- **SistemPengumpulan** → mengelola seluruh sistem
- **Main** → menjalankan program

---

## Fitur Program

- Login sebagai Dosen
- Login sebagai Mahasiswa
- Menambahkan tugas
- Mengumpulkan tugas
- Melihat daftar pengumpulan
- Menampilkan status pengumpulan (Tepat waktu / Terlambat)

---

## Cara Menjalankan Program

Clone repository:


git clone https://github.com/DreamInLogic/THR-PBO.git


Masuk ke folder project:


cd THR-PBO/src


Compile program:


javac *.java


Jalankan program:


java Main


---

## Author

Nama : **Sunu Setyo Jati**  
NIM : **250215030**  
Mata Kuliah : **Pemrograman Berorientasi Objek**  
Program Studi : **TRPL**  
Kelas : **1A**
