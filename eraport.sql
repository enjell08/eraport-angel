-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 22, 2025 at 11:19 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eraport`
--

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `id_course` int(11) NOT NULL,
  `id_siswa` varchar(100) DEFAULT NULL,
  `semester` varchar(50) DEFAULT NULL,
  `blok` varchar(25) NOT NULL,
  `course1` varchar(25) NOT NULL,
  `course2` varchar(25) NOT NULL,
  `course3` varchar(25) NOT NULL,
  `course4` varchar(25) NOT NULL,
  `course5` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`id_course`, `id_siswa`, `semester`, `blok`, `course1`, `course2`, `course3`, `course4`, `course5`) VALUES
(5, '4', '1', '2', 'AGAMA ', 'MATEMATIKA', 'MANDARIN', 'SEJARAH', 'PPKN'),
(7, '6', '1', '2', 'AGAMA', 'MATEMATIKA', 'MANDARIN', 'ENGLISH', 'BAHASA INDONESIA'),
(8, '7', '2', '5', 'PPKN', 'SEJARAH', 'PEMOGRAMAN WEB', 'PEMOGRAMAN BERGERAK', 'PBTGM'),
(10, '9', '2', '7', 'IPA', 'IPS', 'AGAMA', 'MATEMATIKA', 'MANDARIN'),
(12, '11', '2', '8', 'PEMOGRAMAN BERGERAK', 'PBTGM', 'BASIS DATA', 'CONVERSATION', 'PKK'),
(13, '12', '1', '4', 'SENI BUDAYA', 'PENJAS', 'IPA', 'IPS', 'AGAMA'),
(14, '13', '2', '6', 'MATEMATIKA', 'MANDARIN', 'ENGLISH', 'BAHASA INDONESIA', 'PPKN'),
(15, '14', '1', '2', 'SEJARAH', 'PEMOGRAMAN WEB', 'PEMOGRAMAN BERGERAK', 'PBTGM', 'BASIS DATA'),
(16, '15', '2', '5', 'CONVERSATION', 'PKK', 'SENI BUDAYA', 'PENJAS', 'IPA'),
(17, '16', '1', '3', 'IPS', 'AGAMA', 'MATEMATIKA', 'MANDARIN', 'ENGLISH'),
(18, '17', '2', '7', 'BAHASA INDONESIA', 'PPKN', 'SEJARAH', 'PEMOGRAMAN WEB', 'PEMOGRAMAN BERGERAK'),
(19, '18', '1', '1', 'PBTGM', 'BASIS DATA', 'CONVERSATION', 'PKK', 'SENI BUDAYA'),
(20, '19', '2', '8', 'PENJAS', 'IPA', 'IPS', 'AGAMA', 'MATEMATIKA'),
(21, '20', '1', '4', 'MANDARIN', 'ENGLISH', 'BAHASA INDONESIA', 'PPKN', 'SEJARAH'),
(23, '22', '1', '2', 'PKK', 'SENI BUDAYA', 'PENJAS', 'IPA', 'IPS'),
(24, '23', '2', '6', 'AGAMA', 'MATEMATIKA', 'MANDARIN', 'ENGLISH', 'BAHASA INDONESIA'),
(25, '24', '1', '3', 'PPKN', 'SEJARAH', 'PEMOGRAMAN WEB', 'PEMOGRAMAN BERGERAK', 'PBTGM'),
(26, '25', '2', '7', 'BASIS DATA', 'CONVERSATION', 'PKK', 'SENI BUDAYA', 'PENJAS'),
(28, '27', '2', '8', 'ENGLISH', 'BAHASA INDONESIA', 'PPKN', 'SEJARAH', 'PEMOGRAMAN WEB'),
(29, '28', '1', '4', 'PEMOGRAMAN BERGERAK', 'PBTGM', 'BASIS DATA', 'CONVERSATION', 'PKK'),
(30, '31', '1', '3', 'MANDARIN', 'MATEMATIKA', 'AGAMA ', 'PPKN', 'BAHASA INDONESIA'),
(31, '32', '2', '7', 'ENGLISH', 'MANDARIN', 'AGAMA ', 'CONVERSATION', 'BAHASA INDONESIA');

-- --------------------------------------------------------

--
-- Table structure for table `marks`
--

CREATE TABLE `marks` (
  `id_mark` int(11) NOT NULL,
  `id_siswa` varchar(11) DEFAULT NULL,
  `semester` varchar(10) DEFAULT NULL,
  `block` varchar(50) DEFAULT NULL,
  `id_course1` varchar(50) DEFAULT NULL,
  `score1` varchar(50) DEFAULT NULL,
  `id_course2` varchar(50) DEFAULT NULL,
  `score2` varchar(50) DEFAULT NULL,
  `id_course3` varchar(50) DEFAULT NULL,
  `score3` varchar(50) DEFAULT NULL,
  `id_course4` varchar(50) DEFAULT NULL,
  `score4` varchar(50) DEFAULT NULL,
  `id_course5` varchar(50) DEFAULT NULL,
  `score5` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `marks`
--

INSERT INTO `marks` (`id_mark`, `id_siswa`, `semester`, `block`, `id_course1`, `score1`, `id_course2`, `score2`, `id_course3`, `score3`, `id_course4`, `score4`, `id_course5`, `score5`) VALUES
(3, 'Hana', '2', '5', 'PEMOGRAMAN WEB', '85', 'PEMOGRAMAN BERGERAK', '90', 'PBTGM', '92', 'BASIS DATA', '96', 'CONVERSATION', '98'),
(4, 'Faisal', '1', '3', 'IPS', '100', 'AGAMA', '100', 'MATEMATIKA', '100', 'MANDARIN', '100', 'ENGLISH', '100'),
(5, 'Cindy', '2', '8', 'PEMOGRAMAN BERGERAK', '75', 'PBTGM', '72', 'BASIS DATA', '77', 'CONVERSATION', '60', 'PKK', '43'),
(6, 'Hendra', '1', '4', 'MANDARIN', '50', 'ENGLISH', '70', 'BAHASA INDONESIA', '75', 'PPKN', '82', 'SEJARAH', '79'),
(7, 'tasya', '1', '3', 'MANDARIN', '75', 'MATEMATIKA', '89', 'AGAMA', '90', 'PPKN', '99', 'BAHASA INDONESIA', '100'),
(8, 'pak ir', '2', '7', 'ENGLISH', '90', 'MANDARIN', '87', 'AGAMA', '77', 'CONVERSATION', '98', 'BAHASA INDONESIA', '90');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id_siswa` int(11) NOT NULL,
  `nama_siswa` varchar(100) DEFAULT NULL,
  `nis` varchar(50) NOT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `jenis_kelamin` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `no_handphone` varchar(15) DEFAULT NULL,
  `no_hp_ayah` varchar(15) DEFAULT NULL,
  `no_hp_ibu` varchar(15) DEFAULT NULL,
  `alamat` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id_siswa`, `nama_siswa`, `nis`, `tanggal_lahir`, `jenis_kelamin`, `email`, `no_handphone`, `no_hp_ayah`, `no_hp_ibu`, `alamat`) VALUES
(7, 'Aulia', '11111111', '2006-01-02', 'PEREMPUAN', 'aulia@example.com', '081234567802', '081234567902', '081234568002', 'Alamat A2'),
(8, 'Budi', '100003', '2006-02-01', '-- PILIH JENIS KELAMIN--', 'budi@example.com', '081234567803', '081234567903', '081234568003', 'Alamat B1'),
(9, 'Bella', '100004', '2006-02-02', 'Perempuan', 'bella@example.com', '081234567804', '081234567904', '081234568004', 'Alamat B2'),
(10, 'Candra', '100005', '2006-03-01', 'Laki-laki', 'candra@example.com', '081234567805', '081234567905', '081234568005', 'Alamat C1'),
(11, 'Cindy', '100006', '2006-03-02', 'Perempuan', 'cindy@example.com', '081234567806', '081234567906', '081234568006', 'Alamat C2'),
(12, 'Doni', '100007', '2006-04-01', 'Laki-laki', 'doni@example.com', '081234567807', '081234567907', '081234568007', 'Alamat D1'),
(13, 'Dewi', '100008', '2006-04-02', 'Perempuan', 'dewi@example.com', '081234567808', '081234567908', '081234568008', 'Alamat D2'),
(14, 'Eko', '100009', '2006-05-01', 'Laki-laki', 'eko@example.com', '081234567809', '081234567909', '081234568009', 'Alamat E1'),
(15, 'Elisa', '100010', '2006-05-02', 'Perempuan', 'elisa@example.com', '081234567810', '081234567910', '081234568010', 'Alamat E2'),
(16, 'Faisal', '100011', '2006-06-01', 'Laki-laki', 'faisal@example.com', '081234567811', '081234567911', '081234568011', 'Alamat F1'),
(17, 'Fiona', '100012', '2006-06-02', 'Perempuan', 'fiona@example.com', '081234567812', '081234567912', '081234568012', 'Alamat F2'),
(18, 'Gilang', '100013', '2006-07-01', 'Laki-laki', 'gilang@example.com', '081234567813', '081234567913', '081234568013', 'Alamat G1'),
(19, 'Gita', '100014', '2006-07-02', 'Perempuan', 'gita@example.com', '081234567814', '081234567914', '081234568014', 'Alamat G2'),
(20, 'Hendra', '100015', '2006-08-01', 'Laki-laki', 'hendra@example.com', '081234567815', '081234567915', '081234568015', 'Alamat H1'),
(21, 'Hana', '100016', '2006-08-02', 'Perempuan', 'hana@example.com', '081234567816', '081234567916', '081234568016', 'Alamat H2'),
(22, 'Ivan', '100017', '2006-09-01', 'Laki-laki', 'ivan@example.com', '081234567817', '081234567917', '081234568017', 'Alamat I1'),
(23, 'Indah', '100018', '2006-09-02', 'Perempuan', 'indah@example.com', '081234567818', '081234567918', '081234568018', 'Alamat I2'),
(24, 'Joko', '100019', '2006-10-01', 'Laki-laki', 'joko@example.com', '081234567819', '081234567919', '081234568019', 'Alamat J1'),
(25, 'Julia', '100020', '2006-10-02', 'Perempuan', 'julia@example.com', '081234567820', '081234567920', '081234568020', 'Alamat J2'),
(26, 'Krisna', '100021', '2006-11-01', 'Laki-laki', 'krisna@example.com', '081234567821', '081234567921', '081234568021', 'Alamat K1'),
(27, 'Karin', '100022', '2006-11-02', 'Perempuan', 'karin@example.com', '081234567822', '081234567922', '081234568022', 'Alamat K2'),
(28, 'Leo', '100023', '2006-12-01', 'Laki-laki', 'leo@example.com', '081234567823', '081234567923', '081234568023', 'Alamat L1'),
(29, 'Lina', '100024', '2006-12-02', 'Perempuan', 'lina@example.com', '081234567824', '081234567924', '081234568024', 'Alamat L2'),
(31, 'tasya', '111222333', '2025-02-02', 'PEREMPUAN', 'tasya@gmail.com', '0987654345678', '0987654567878', '0985432345666', 'jln.kenangan'),
(32, 'pak ir', '098989', '2025-02-01', 'LAKI-LAKI', 'dfghjcvbnm', '23456789', '2345678', '2345678', 'jlnjln');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(225) DEFAULT NULL,
  `password` varchar(225) DEFAULT NULL,
  `level` varchar(225) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `level`) VALUES
(1, 'admin', 'admin', '1'),
(3, 'guru', 'guru', '2'),
(4, 'siswa', 'siswa', '3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id_course`);

--
-- Indexes for table `marks`
--
ALTER TABLE `marks`
  ADD PRIMARY KEY (`id_mark`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id_siswa`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `id_course` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT for table `marks`
--
ALTER TABLE `marks`
  MODIFY `id_mark` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
  MODIFY `id_siswa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
