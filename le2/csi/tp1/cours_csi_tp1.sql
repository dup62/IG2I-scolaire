-- phpMyAdmin SQL Dump
-- version 5.0.4deb2+deb11u1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : mer. 11 oct. 2023 à 12:08
-- Version du serveur :  10.5.15-MariaDB-0+deb11u1
-- Version de PHP : 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `cours_csi_tp1`
--

-- --------------------------------------------------------

--
-- Structure de la table `horodateurs`
--

CREATE TABLE `horodateurs` (
  `id` int(11) NOT NULL,
  `nomRue` varchar(255) NOT NULL,
  `numeroRue` int(11) NOT NULL,
  `idZone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `horodateurs`
--

INSERT INTO `horodateurs` (`id`, `nomRue`, `numeroRue`, `idZone`) VALUES
(1, 'rue du Château', 10, 1),
(2, 'place des Fleurs', 14, 1),
(3, 'boulevard du Bourg', 32, 2),
(4, 'passage Grisaille', 85, 3),
(5, 'allée du Béton', 173, 3);

-- --------------------------------------------------------

--
-- Structure de la table `tickets`
--

CREATE TABLE `tickets` (
  `id` int(11) NOT NULL,
  `dateDebut` varchar(20) NOT NULL,
  `heureDebut` varchar(10) NOT NULL,
  `dureePrevu` float NOT NULL,
  `plaqueImmatriculation` varchar(12) NOT NULL,
  `prixTotal` float NOT NULL,
  `idHorodateur` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `tickets`
--

INSERT INTO `tickets` (`id`, `dateDebut`, `heureDebut`, `dureePrevu`, `plaqueImmatriculation`, `prixTotal`, `idHorodateur`) VALUES
(1, '2023-09-26', '16:12:00', 1, 'AA-123-BB', 1, 1),
(2, '2023-09-27', '10:26:00', 3, 'CC-456-DD', 3, 2),
(3, '2023-09-27', '10:26:00', 1.5, 'AA-123-BB', 1.5, 3),
(4, '2023-09-28', '14:58:00', 0.5, 'ZZ-789-ZZ', 0.5, 4),
(5, '2023-09-28', '17:22:00', 0.75, '135 XY 79', 0.75, 4),
(6, '2023-09-29', '09:09:09', 1, 'aa123bb', 0.75, 5),
(7, '2023-10-11', '10:22:38', 6, 'ZZ-789-ZZ', 6, 2);

-- --------------------------------------------------------

--
-- Structure de la table `zonesTarifaires`
--

CREATE TABLE `zonesTarifaires` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `tarifHoraire` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `zonesTarifaires`
--

INSERT INTO `zonesTarifaires` (`id`, `nom`, `tarifHoraire`) VALUES
(1, 'A', 1),
(2, 'B', 1),
(3, 'C', 1);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `horodateurs`
--
ALTER TABLE `horodateurs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idZone` (`idZone`);

--
-- Index pour la table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idHorodateur` (`idHorodateur`);

--
-- Index pour la table `zonesTarifaires`
--
ALTER TABLE `zonesTarifaires`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `horodateurs`
--
ALTER TABLE `horodateurs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `tickets`
--
ALTER TABLE `tickets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `zonesTarifaires`
--
ALTER TABLE `zonesTarifaires`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `horodateurs`
--
ALTER TABLE `horodateurs`
  ADD CONSTRAINT `horodateurs_ibfk_1` FOREIGN KEY (`idZone`) REFERENCES `zonesTarifaires` (`id`);

--
-- Contraintes pour la table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`idHorodateur`) REFERENCES `horodateurs` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
