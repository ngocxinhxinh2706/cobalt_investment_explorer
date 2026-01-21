CREATE TABLE `datareferences` (
  `OBJECTID` int DEFAULT NULL,
  `Ref_ID` varchar(100) NOT NULL,
  `dataReference` text,
  `Last_Updt` date DEFAULT NULL,
  PRIMARY KEY (`Ref_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
