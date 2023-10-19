CREATE TABLE `pracownicy` (
  `id` int NOT NULL AUTO_INCREMENT,
  `imie` varchar(45) DEFAULT NULL,
  `nazwisko` varchar(45) DEFAULT NULL,
  `wiek` int DEFAULT NULL,
  `skasowany` int DEFAULT NULL,
  `stanowisko` int DEFAULT NULL,
  `sep` int DEFAULT '0',
  `zdjecie` mediumblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sl_stanowisk` (
  `id` int NOT NULL,
  `nazwa` varchar(45) DEFAULT NULL,
  `opis` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `sl_stan` (
  `id` int NOT NULL,
  `nazwa` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nazwa_UNIQUE` (`nazwa`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
