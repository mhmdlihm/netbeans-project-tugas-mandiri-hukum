-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 10, 2025 at 04:47 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pbo2_2310010233`
--

-- --------------------------------------------------------

--
-- Table structure for table `jenis_perkara`
--

CREATE TABLE `jenis_perkara` (
  `id` int(10) NOT NULL,
  `parent` varchar(100) NOT NULL,
  `jenis_perkara_id` varchar(100) NOT NULL,
  `jenis_perkara_nama` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `jenis_perkara`
--

INSERT INTO `jenis_perkara` (`id`, `parent`, `jenis_perkara_id`, `jenis_perkara_nama`) VALUES
(637845, 'fbdfbd', 'bdbtr', 'bttrrt'),
(8687, 'dgus', 'efjhewi', 'etweqg'),
(67, 'asar', '45', 'dimas'),
(67, 'asar', '45', 'dimas'),
(60, 'dimas', 'rty', 'rtyrew');

-- --------------------------------------------------------

--
-- Table structure for table `perkara_proses`
--

CREATE TABLE `perkara_proses` (
  `id_perkara` varchar(10) NOT NULL,
  `tahapan` varchar(100) NOT NULL,
  `proses` varchar(100) NOT NULL,
  `tanggal` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `perkara_proses`
--

INSERT INTO `perkara_proses` (`id_perkara`, `tahapan`, `proses`, `tanggal`) VALUES
('34', '1', 'ilhm', '2'),
('39', '5', 'asar', '2');

-- --------------------------------------------------------

--
-- Table structure for table `tabel_pengadilan`
--

CREATE TABLE `tabel_pengadilan` (
  `id_pengadilan` int(10) NOT NULL,
  `nama_pengadilan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tabel_pengadilan`
--

INSERT INTO `tabel_pengadilan` (`id_pengadilan`, `nama_pengadilan`) VALUES
(43, 'adit'),
(89, 'asar'),
(4, 'dimas'),
(34, 'ilhm');

-- --------------------------------------------------------

--
-- Table structure for table `tahapan_perkara`
--

CREATE TABLE `tahapan_perkara` (
  `id_tahapan` int(10) NOT NULL,
  `nama_tahapan` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tahapan_perkara`
--

INSERT INTO `tahapan_perkara` (`id_tahapan`, `nama_tahapan`) VALUES
(45, 'ilham'),
(47, 'dimas');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
