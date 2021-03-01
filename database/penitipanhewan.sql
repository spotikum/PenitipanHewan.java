-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 13, 2020 at 12:20 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `penitipanhewan`
--

-- --------------------------------------------------------

--
-- Table structure for table `pending`
--

CREATE TABLE `pending` (
  `kode` int(11) NOT NULL,
  `pemilik` varchar(30) DEFAULT NULL,
  `jenis` varchar(10) DEFAULT NULL,
  `ras` varchar(10) DEFAULT NULL,
  `dititip` varchar(20) DEFAULT NULL,
  `diambil` varchar(20) DEFAULT NULL,
  `paket` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pending`
--

INSERT INTO `pending` (`kode`, `pemilik`, `jenis`, `ras`, `dititip`, `diambil`, `paket`) VALUES
(1001, 'indra', 'kucing', 'medium', '01-01-2020', '07-0102020', 'istimewa'),
(1002, 'patric', 'anjing', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_jaminan`
--

CREATE TABLE `tb_jaminan` (
  `id_jaminan` int(11) NOT NULL,
  `namajaminan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_jaminan`
--

INSERT INTO `tb_jaminan` (`id_jaminan`, `namajaminan`) VALUES
(1, 'KTP'),
(2, 'Paspor');

-- --------------------------------------------------------

--
-- Table structure for table `tb_jenishewan`
--

CREATE TABLE `tb_jenishewan` (
  `id_jenishewan` int(11) NOT NULL,
  `id_namahewan` int(11) NOT NULL,
  `namajenishewan` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_jenishewan`
--

INSERT INTO `tb_jenishewan` (`id_jenishewan`, `id_namahewan`, `namajenishewan`) VALUES
(1, 1, 'Scottish Fold'),
(2, 1, 'Norwegian Forrest Cat'),
(3, 1, 'Sphynx'),
(4, 1, 'Egyptian Mau'),
(5, 1, 'Korat'),
(6, 2, 'Chihuahua'),
(7, 2, 'Siberian Husky'),
(8, 2, 'Pug'),
(9, 2, 'Shiba Inu'),
(10, 2, 'Dachshund');

-- --------------------------------------------------------

--
-- Table structure for table `tb_jenismakanan`
--

CREATE TABLE `tb_jenismakanan` (
  `id_jenismakanan` int(11) NOT NULL,
  `id_namahewan` int(11) NOT NULL,
  `namajenismakanan` varchar(30) NOT NULL,
  `harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_jenismakanan`
--

INSERT INTO `tb_jenismakanan` (`id_jenismakanan`, `id_namahewan`, `namajenismakanan`, `harga`) VALUES
(1, 1, 'Whiskas', 250000),
(2, 1, 'Proplan', 180000),
(3, 1, 'Royal Canin Kitten', 375000),
(4, 1, 'Felibite', 87000),
(5, 1, 'Frikies', 98000),
(6, 1, 'Me-O', 105000),
(7, 1, 'Maxi', 150000),
(8, 1, 'Equilibrio', 125000),
(9, 2, 'PEDIGREE Dry', 87000),
(10, 2, 'Propac lamb & Brown rice', 90000),
(11, 2, 'Happy Dog Supreme', 90000),
(12, 2, 'Blackwood All Life Stages Salm', 220000),
(13, 2, 'MAKANAN ANJING PREMIUM DOG', 150000),
(14, 2, 'Adult Africa', 90000);

-- --------------------------------------------------------

--
-- Table structure for table `tb_log`
--

CREATE TABLE `tb_log` (
  `id_log` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `id_jenishewan` int(11) DEFAULT NULL,
  `id_jenismakanan` int(11) DEFAULT NULL,
  `id_pakethewan` int(11) DEFAULT NULL,
  `kode` int(11) DEFAULT NULL,
  `namapemilik` varchar(50) DEFAULT NULL,
  `hewanlainnya` int(11) DEFAULT NULL,
  `namahewanlainnya` varchar(30) DEFAULT NULL,
  `tanggalterima` varchar(30) DEFAULT NULL,
  `tanggalambil` varchar(30) DEFAULT NULL,
  `telepon` varchar(14) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `keterangan` varchar(100) DEFAULT NULL,
  `stats` int(11) DEFAULT NULL,
  `tanggalint` int(11) DEFAULT NULL,
  `harga` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_log`
--

INSERT INTO `tb_log` (`id_log`, `id_user`, `id_jenishewan`, `id_jenismakanan`, `id_pakethewan`, `kode`, `namapemilik`, `hewanlainnya`, `namahewanlainnya`, `tanggalterima`, `tanggalambil`, `telepon`, `email`, `alamat`, `keterangan`, `stats`, `tanggalint`, `harga`, `total`) VALUES
(1006, 1, 1, 1, 1, 1001, 'indra', 0, '', '10-jun-2020', '11-jun-2020', '812345678', ' ', ' ', ' ', 0, NULL, NULL, NULL),
(1015, 1, 2, 2, 1, 1009, 'f', 0, ' ', '2020-06-10', '2020-06-13', '-', '-', '-', '-', 0, NULL, NULL, NULL),
(1016, 1, 2, 2, 2, 1010, 'dadu', 0, ' ', '2020-06-10', '2020-06-13', '-', '-', '-', '-', 0, NULL, NULL, NULL),
(1017, 1, 1, 1, 1, 1011, 'sda', 0, ' ', '2020-06-10', '2020-06-12', '-', '-', '-', '-', 0, NULL, NULL, NULL),
(1018, 1, 1, 1, 3, 1012, 'sada', 0, ' ', '2020-06-10', '2020-06-12', '-', '-', '-', '-', 0, NULL, NULL, NULL),
(1019, 1, 1, 1, 2, 1013, 'sasa', 0, ' ', '2020-06-10', '2020-06-12', '-', '-', '-', '-', 0, NULL, NULL, NULL),
(1020, 1, 1, 1, 2, 1014, 'hoaa', 0, ' ', '2020-06-10', '2020-06-12', '-', '-', '-', '-', 0, NULL, NULL, NULL),
(1021, 1, 1, 1, 1, 1015, 'kaka', 0, ' ', '2020-06-10', '2020-06-12', '-', '-', '-', '-', 0, NULL, NULL, NULL),
(1022, 1, 1, 1, 1, 1016, 'sasa', 0, ' ', '2020-06-10', '2020-06-12', '-', '-', '-', '-', 0, NULL, NULL, NULL),
(1023, 1, 1, 1, 1, 1017, '', 0, ' ', '2020-06-10', '2020-06-13', '', '', '', '', 0, NULL, NULL, NULL),
(1024, 1, 1, 1, 1, 1018, 'koko', 0, ' ', '2020-06-10', '2020-06-12', '', '', '', '', 0, NULL, NULL, NULL),
(1025, 1, 1, 1, 2, 1019, 'bisa', 0, 'apalah', '2020-06-10', '2020-06-13', '-', '-', '-', '-', 0, 1591768440, NULL, NULL),
(1026, 1, 1, 1, 1, 1020, 'oase', 1, 'bodo', '2020-06-10', '2020-06-13', '', '', '', '', 0, 1591768633, NULL, NULL),
(1027, 1, 1, 1, 1, 1021, 'apalah', 1, 'rogi', '2020-06-10', '2020-06-12', '', '', '', '', 0, 1591768795, NULL, NULL),
(1028, 1, 1, 1, 1, 1022, '', 0, 'Scottish Fold', '2020-06-10', '2020-06-12', '', '', '', '', 0, 1591769159, 45000, NULL),
(1029, 1, 2, 2, 1, 1023, 'adasa', 0, 'Chihuahua', '2020-06-10', '2020-06-11', '', '', '', '', 0, 1591770737, 28000, NULL),
(1030, 1, 2, 2, 1, 1024, 'ada', 0, 'Siberian Husky', '2020-06-10', '2020-06-11', '', '', '', '', 0, 1591772254, 28000, NULL),
(1031, 1, 10, 2, 1, 1025, 'asaa', 0, 'Shiba Inu', '2020-06-10', '2020-06-12', '', '', '', '', 0, 1591772323, 38000, NULL),
(1032, 1, 10, 2, 2, 1026, 'dodo', 0, 'Shiba Inu', '2020-06-10', '2020-06-12', '0812345678', '', '', '', 1, 1591776308, 38000, NULL),
(1033, 1, 10, 2, 3, 1027, 'jojo', 1, 'tanpajenis', '2020-06-10', '2020-06-12', '0812345678', '', '', '', 0, 1591777570, 38000, NULL),
(1034, 1, 10, 2, 2, 1028, 'gogo', 1, 'Anjing Jelek', '2020-06-10', '2020-06-12', '0812345678', '', '', '', 0, 1591779338, 38000, NULL),
(1035, 1, 10, 2, 3, 1029, 'sopo', 1, 'cokopy', '2020-06-10', '2020-06-13', '08234567898', '', '', '', 1, 1591779640, 48000, NULL),
(1036, 1, 10, 2, 3, 1029, 'sopo', 0, 'Siberian Husky', '2020-06-10', '2020-06-13', '08234567898', '', '', '', 1, 1591783528, 48000, NULL),
(1037, 1, 10, 2, 2, 1030, 'sada', 0, 'Siberian Husky', '2020-06-10', '2020-06-13', '081337053157', '', '', '', 1, 1591784608, 48000, NULL),
(1038, 1, 10, 2, 3, 1031, 'soho', 0, 'Shiba Inu', '2020-06-10', '2020-06-12', '0812345678', '', '', '', 1, 18523, 38000, NULL),
(1039, 1, 5, 1, 2, 1032, 'pucek', 0, 'Scottish Fold', '2020-06-10', '2020-06-13', '08213458752', '', '', '', 1, 18578, 55000, NULL),
(1040, 1, 10, 2, 3, 1033, 'komo', 0, 'Pug', '2020-06-10', '2020-06-13', '0821564784', '', '', '', 1, 1591787351, 48000, NULL),
(1041, 1, 10, 2, 2, 1034, 'saga', 0, 'Shiba Inu', '2020-06-11', '2020-06-14', '081236547895', '', '', '', 0, 1591805912, 48000, NULL),
(1042, 1, 5, 1, 1, 1035, 'caca', 0, 'Sphynx', '2020-06-11', '2020-06-15', '083124567845', '', '', '', 1, 1591806102, 65000, NULL),
(1043, 1, 10, 2, 3, 1036, 'souca', 0, 'Pug', '2020-06-11', '2020-06-13', '081234568545', '', '', '', 1, 1591806309, 38000, NULL),
(1044, 1, 10, 2, 2, 1037, 'tralala', 0, 'Shiba Inu', '2020-06-11', '2020-06-14', '0812345678987', '', '', '', 1, 1591806525, 48000, NULL),
(1045, 1, 10, 2, 2, 1038, 'molacoca', 0, 'Siberian Husky', '2020-06-11', '2020-06-13', '08213456785', '', '', '', 1, 1591806612, 38000, NULL),
(1046, 1, 10, 2, 3, 1039, 'coco', 0, 'Dachshund', '2020-06-11', '2020-06-13', '08215463214578', '', '', '', 1, 1591806720, 38000, NULL),
(1047, 1, 10, 2, 3, 1040, 'sahana', 0, 'Shiba Inu', '2020-06-11', '2020-06-15', '08523154678', '', '', '', 1, 1591807723, 58000, NULL),
(1048, 1, 10, 2, 1, 1041, 'Wisnu Aditya', 0, 'Siberian Husky', '2020-06-11', '2020-06-18', '085739284040', 'mangjr168@gmail.com', 'dalung', '', 0, 1591809675, 88000, NULL),
(1049, 1, 10, 2, 2, 1042, 'Betenk', 1, 'kintamani', '2020-06-11', '2020-06-25', '08123456789', 'sasap@gmail.com', 'desa bobor', 'patah tulang', 0, 1591812939, 158000, NULL),
(1050, 1, 10, 2, 2, 158002, 'sasa', 0, 'Chihuahua', '2020-06-11', '2020-08-14', '0821546787', '', '', '', 1, 1591824247, 0, 116000),
(1051, 1, 5, 1, 2, 2, 'dasa', 0, 'Scottish Fold', '2020-06-11', '2020-08-16', '0821354754', '', '', '', 1, 1591824343, 0, 170000),
(1052, 1, 10, 2, 2, 3, 'adasaa', 0, 'Siberian Husky', '2020-06-11', '2020-08-16', '0821456789', '', '', '', 0, 1591825460, 48000, NULL),
(1053, 1, 10, 2, 2, 4, 'donal', 0, 'Siberian Husky', '2020-06-11', '2020-06-14', '08123456789', '', '', '', 0, 1591825791, 48000, NULL),
(1054, 1, 5, 1, 2, 5, 'koso', 0, 'Scottish Fold', '2020-06-11', '2020-06-14', '08124578654', '', '', '', 1, 1591826365, 55000, NULL),
(1055, 1, 5, 1, 1, 6, 'dolo', 0, 'Scottish Fold', '2020-06-11', '2020-08-18', '0821546789', '', '', '', 0, 1591826937, 65000, NULL),
(1056, 1, 10, 2, 1, 7, 'balala', 1, 'kintamani', '2020-06-11', '2020-08-18', '081234751', '', '', '', 1, 1591827363, 58000, NULL),
(1057, 1, 10, 2, 1, 8, 'soko', 1, 'kintamani', '2020-06-11', '2020-08-14', '08123456987', '', '', '', 0, 1591827613, 38000, NULL),
(1058, 1, 5, 1, 2, 9, 'pandemi', 0, 'Sphynx', '2020-06-11', '2020-08-18', '082123456789', '', '', '', 0, 1591827783, 65000, NULL),
(1059, 1, 10, 2, 1, 10, 'Otong', 1, 'Kintamani', '2020-06-11', '2020-08-24', '08123456789', 'otong@gmail.com', 'Desa Bahagiaselalu', '-', 0, 1591829938, 88000, NULL),
(1060, 1, 10, 2, 1, 10, 'Otong', 1, 'Kintamani', '2020-06-11', '2020-08-24', '08123456789', 'otong@gmail.com', 'Desa Bahagiaselalu', '-', 0, 1591829943, 88000, NULL),
(1061, 1, 5, 1, 2, 11, 'Guromo', 0, 'Sphynx', '2020-06-11', '2020-08-20', '08123456789', 'Gad@mail.com', 'Desa Bajakan', '', 0, 1591840041, 75000, NULL),
(1062, 1, 10, 2, 1, 11, 'Asada', 1, 'Kintamani', '2020-06-11', '2020-08-14', '08123456789', 'Gad@mail.com', 'Desa Bajakan', '', 0, 1591840123, 38000, NULL),
(1063, 1, 10, 2, 1, 12, 'Deva', 1, 'Kintamani', '2020-06-11', '2020-08-24', '08123456789', 'deva@gmail.com', 'Desa Tianyar', 'Patah Tulang', 0, 1591849516, 88000, NULL),
(1064, 1, 10, 2, 3, 13, 'Indra', 1, 'Kintamani', '2020-06-11', '2020-08-24', '08123456789', 'indra@gmail.com', 'Dsea Tianyar', '', 0, 1591873445, 88000, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_looging`
--

CREATE TABLE `tb_looging` (
  `id_looging` int(11) NOT NULL,
  `namapemilik` varchar(50) DEFAULT NULL,
  `jenishewan` varchar(30) DEFAULT NULL,
  `jenismakanan` varchar(30) DEFAULT NULL,
  `lamapenitipan` int(11) DEFAULT NULL,
  `paketpenitipan` varchar(20) DEFAULT NULL,
  `telepon` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `keterangan` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user` varchar(30) DEFAULT NULL,
  `namahewan` varchar(30) DEFAULT NULL,
  `kode` int(11) DEFAULT NULL,
  `tanggalterima` varchar(20) DEFAULT NULL,
  `tanggalambil` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `tb_namahewan`
--

CREATE TABLE `tb_namahewan` (
  `id_namahewan` int(11) NOT NULL,
  `namahewan` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_namahewan`
--

INSERT INTO `tb_namahewan` (`id_namahewan`, `namahewan`) VALUES
(1, 'Kucing'),
(2, 'Anjing');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pakethewan`
--

CREATE TABLE `tb_pakethewan` (
  `id_pakethewan` int(11) NOT NULL,
  `namapaket` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_pakethewan`
--

INSERT INTO `tb_pakethewan` (`id_pakethewan`, `namapaket`) VALUES
(1, 'Paket Gold'),
(2, 'Paket Silver'),
(3, 'Paket Bronze');

-- --------------------------------------------------------

--
-- Table structure for table `tb_status`
--

CREATE TABLE `tb_status` (
  `id_status` int(11) NOT NULL,
  `namastatus` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_status`
--

INSERT INTO `tb_status` (`id_status`, `namastatus`) VALUES
(0, 'Dititiip'),
(1, 'Sudah Diam');

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id_user`, `username`, `password`, `type`) VALUES
(1, 'admin', 'admin', 1),
(3, 'user', 'user', 2),
(5, 'dodo', 'hello', 2),
(8, 'gogo', 'babi', 1),
(10, 'pao', 'uhui', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tb_usertype`
--

CREATE TABLE `tb_usertype` (
  `id_usertype` int(11) NOT NULL,
  `namausertype` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_usertype`
--

INSERT INTO `tb_usertype` (`id_usertype`, `namausertype`) VALUES
(1, 'Admin'),
(2, 'Karyawan');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pending`
--
ALTER TABLE `pending`
  ADD PRIMARY KEY (`kode`);

--
-- Indexes for table `tb_jaminan`
--
ALTER TABLE `tb_jaminan`
  ADD PRIMARY KEY (`id_jaminan`);

--
-- Indexes for table `tb_jenishewan`
--
ALTER TABLE `tb_jenishewan`
  ADD PRIMARY KEY (`id_jenishewan`);

--
-- Indexes for table `tb_jenismakanan`
--
ALTER TABLE `tb_jenismakanan`
  ADD PRIMARY KEY (`id_jenismakanan`),
  ADD KEY `id_namahewan` (`id_namahewan`);

--
-- Indexes for table `tb_log`
--
ALTER TABLE `tb_log`
  ADD PRIMARY KEY (`id_log`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_jenishewan` (`id_jenishewan`),
  ADD KEY `id_jenismakanan` (`id_jenismakanan`),
  ADD KEY `id_pakethewan` (`id_pakethewan`);

--
-- Indexes for table `tb_looging`
--
ALTER TABLE `tb_looging`
  ADD PRIMARY KEY (`id_looging`);

--
-- Indexes for table `tb_namahewan`
--
ALTER TABLE `tb_namahewan`
  ADD PRIMARY KEY (`id_namahewan`);

--
-- Indexes for table `tb_pakethewan`
--
ALTER TABLE `tb_pakethewan`
  ADD PRIMARY KEY (`id_pakethewan`);

--
-- Indexes for table `tb_status`
--
ALTER TABLE `tb_status`
  ADD PRIMARY KEY (`id_status`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id_user`);

--
-- Indexes for table `tb_usertype`
--
ALTER TABLE `tb_usertype`
  ADD PRIMARY KEY (`id_usertype`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pending`
--
ALTER TABLE `pending`
  MODIFY `kode` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1003;

--
-- AUTO_INCREMENT for table `tb_jaminan`
--
ALTER TABLE `tb_jaminan`
  MODIFY `id_jaminan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_jenismakanan`
--
ALTER TABLE `tb_jenismakanan`
  MODIFY `id_jenismakanan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `tb_log`
--
ALTER TABLE `tb_log`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1065;

--
-- AUTO_INCREMENT for table `tb_looging`
--
ALTER TABLE `tb_looging`
  MODIFY `id_looging` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_namahewan`
--
ALTER TABLE `tb_namahewan`
  MODIFY `id_namahewan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_pakethewan`
--
ALTER TABLE `tb_pakethewan`
  MODIFY `id_pakethewan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tb_jenismakanan`
--
ALTER TABLE `tb_jenismakanan`
  ADD CONSTRAINT `id_namahewan` FOREIGN KEY (`id_namahewan`) REFERENCES `tb_namahewan` (`id_namahewan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
