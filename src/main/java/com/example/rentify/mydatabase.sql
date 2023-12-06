-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : ven. 16 déc. 2022 à 21:13
-- Version du serveur : 10.4.25-MariaDB
-- Version de PHP : 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `mydatabase`
--

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
  `username` varchar(30) NOT NULL,
  `CIN` varchar(30) NOT NULL,
  `prenom` varchar(30) NOT NULL,
  `nom` varchar(30) NOT NULL,
  `age` int(11) NOT NULL,
  `password` varchar(30) NOT NULL,
  `N_permis` varchar(30) NOT NULL,
  `date_finVali` date NOT NULL,
  `isAdmin` bit(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`username`, `CIN`, `prenom`, `nom`, `age`, `password`, `N_permis`, `date_finVali`, `isAdmin`) VALUES
('meryemEl', 'D583016', 'meryem', 'Elhajoui', 21, '123', 'N001', '2024-11-20', b'0'),
('mouad', 'D4092', 'MOUAD', 'qabouch', 24, '222', '4378', '2022-09-12', b'0'),
('rimel', 'D34355', 'rima', 'Elhajoui', 22, '123', 'N1234', '2022-08-16', b'0'),
('root', '', '', '', 0, 'root', '', '0000-00-00', b'1');

-- --------------------------------------------------------

--
-- Structure de la table `voiture`
--

CREATE TABLE `voiture` (
  `MatriculeV` varchar(20) NOT NULL,
  `DateVisite` date DEFAULT NULL,
  `DateAssu` date DEFAULT NULL,
  `Marque` varchar(30) DEFAULT NULL,
  `Modele` varchar(20) DEFAULT NULL,
  `PrixParJour` double DEFAULT NULL,
  `image` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `voiture`
--

INSERT INTO `voiture` (`MatriculeV`, `DateVisite`, `DateAssu`, `Marque`, `Modele`, `PrixParJour`, `image`) VALUES
('A12438-32', '2019-11-01', '2019-02-21', 'Mercedes amg A', 'Mercedes', 1000.78, 'C:\\Users\\HP\\OneDrive\\Bureau\\Projet-Java\\pics');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- Index pour la table `voiture`
--
ALTER TABLE `voiture`
  ADD PRIMARY KEY (`MatriculeV`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
