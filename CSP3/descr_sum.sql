CREATE TABLE `descr_sum` (
  `OBJECTID` int NOT NULL,
  `Site_ID` varchar(10) DEFAULT NULL,
  `Ftr_ID` varchar(10) DEFAULT NULL,
  `Last_Updt` date DEFAULT NULL,
  `Descr_Type` varchar(50) DEFAULT NULL,
  `Descr` text,
  `Remarks` text,
  PRIMARY KEY (`OBJECTID`),
  KEY `Site_ID` (`Site_ID`),
  KEY `Ftr_ID` (`Ftr_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;