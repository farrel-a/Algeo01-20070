# TUGAS BESAR 1 IF2123 ALJABAR LINIER DAN GEOMETRI SISTEM PERSAMAAN LINIER DETERMINAN, DAN APLIKASINYA 

## SEMESTER 1 TAHUN 2020/2021 

<br>

## 13520110 - Farrel Ahmad 
## 13520070 - Raden Haryosatyo Wisjnunandono
## 13520145 - Steven Gianmarg H. Siahaan

<br>

## Table of Contents
- [Cara Menjalankan Program](#CM)
- [Cara Compile/Kompilasi](#CC)
- [Main Menu](#MM)
- [Contoh Penggunaan](#CP)

<br>

## Cara Menjalankan Program <a name = "CM"></a>
```sh
$ git clone https://github.com/nandono206/Algeo01-20070.git

$ cd Algeo01-20070

$ cd bin

$ java com.matrixxx.Main
```

<br>

## Cara Compile/Kompilasi <a name = "CC"></a>
- Setelah clone
```sh
$ cd Algeo01-20070

$ javac -d bin src/com/matrixx/*.java
```

<br>

## Main Menu <a name = "MM"></a>
```sh
MENU
1. Sistem Persamaan Linear
2. Determinan
3. Matriks Balikan
4. Interpolasi Polinom
5. Regresi Linear Berganda
6. Keluar
>>>
```

- Pilih dengan cara memasukkan angka

<br>

## Contoh Penggunaan <a name = "CP"></a>
- Menggunakan SPL kaidah cramer untuk mencari solusi dari persamaan berikut

```
2x + 3y - 5z = 1
x  +  y -  z = 2
     2y +  z = 8
```

```sh
MENU
1. Sistem Persamaan Linear
2. Determinan
3. Matriks Balikan
4. Interpolasi Polinom
5. Regresi Linear Berganda
6. Keluar
>>> 1
1. Metode Eliminasi Gauss
2. Metode Eliminasi Gauss-Jordan
3. Metode Matriks Balikan
4. Kaidah Cramer
>>> 4
Input Types:
1. Keyboard Input
2. File Input
>>> 1
Masukkan jumlah baris: 3
Masukkan jumlah kolom: 4
Masukkan Matriks: 
2 3 -5 1
1 1 -1 2
0 2 1 8
File Created: CramerResult.txt
Matriks:
2.000 3.000 -5.000 1.000 
1.000 1.000 -1.000 2.000
0.000 2.000 1.000 8.000


X1 = 1.0
X2 = 3.0
X3 = 2.0
```