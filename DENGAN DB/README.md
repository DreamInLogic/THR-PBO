Sistem Pengumpulan Tugas - PBO (Dengan Database)

Program Java berbasis database untuk memenuhi Tugas Pemrograman Berorientasi Objek (PBO).

Program ini merupakan pengembangan dari versi tanpa database, dengan menambahkan penyimpanan data menggunakan database.

Deskripsi Sistem

Sistem ini mensimulasikan proses pengumpulan tugas antara dosen dan mahasiswa dengan penyimpanan data secara permanen menggunakan database.

Sistem memungkinkan:

Dosen mengelola tugas (CRUD)

Mahasiswa mengumpulkan tugas

Sistem menentukan status pengumpulan (tepat waktu / terlambat)

Menampilkan data pengumpulan dan statistik

Program dijalankan melalui menu interaktif di terminal.

Teknologi yang Digunakan

Java

JDBC

MySQL

Konsep PBO yang Digunakan

Program ini mengimplementasikan konsep:

Class dan Object

Inheritance

Enkapsulasi

Method

Relasi antar class

Input user (Scanner)

Exception Handling

Database Connection (JDBC)

Struktur Class
User
├── Mahasiswa
└── Dosen

Tugas
Pengumpulan
SistemPengumpulan
DatabaseConnection
Main
Struktur Database

Database: pbo_tugas

Tabel:

tugas

mahasiswa

pengumpulan

Relasi:

pengumpulan → tugas (Foreign Key)

pengumpulan → mahasiswa (Foreign Key)

Fitur Program
👨‍🏫 Dosen

Tambah tugas

Lihat daftar tugas

Edit tugas

Hapus tugas

Lihat pengumpulan

Statistik pengumpulan

Reset database

👨‍🎓 Mahasiswa

Input NIM dan nama

Lihat tugas yang belum dikumpulkan

Kumpulkan tugas

Validasi tidak bisa submit dua kali

Status otomatis (Tepat Waktu / Terlambat)

Setup Database
1. Buat Database
CREATE DATABASE pbo_tugas;
2. Import Database

Gunakan phpMyAdmin:

Buka phpMyAdmin

Pilih database pbo_tugas

Klik menu Import

Upload file:

database/pbo_tugas.sql
Konfigurasi Koneksi

Pastikan file DatabaseConnection.java sesuai:

String url = "jdbc:mysql://localhost:3306/pbo_tugas";
String user = "root";
String password = "";
Cara Menjalankan Program
Compile:
javac -cp "lib/*" -d bin src/*.java
Run:
java -cp "bin;lib/*" Main
Login
Dosen
Username : dosen
Password : 123
Mahasiswa

Input bebas (NIM & Nama)

Keunggulan Versi Database

Data tersimpan permanen

Tidak hilang saat program ditutup

Mendukung relasi antar data

Lebih mendekati sistem nyata

Struktur Folder
dengan-db/
├── src/
├── bin/
├── lib/
├── database/
│   └── pbo_tugas.sql
└── README.md
Author

Nama : Sunu Setyo Jati
NIM : 250215030
Mata Kuliah : Pemrograman Berorientasi Objek
Program Studi : TRPL
Kelas : 1A