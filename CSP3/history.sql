CREATE TABLE `history` (
  `OID` int NOT NULL,
  `Site_ID` varchar(255) DEFAULT NULL,
  `Ftr_ID` varchar(255) DEFAULT NULL,
  `Ftr_Name` varchar(255) DEFAULT NULL,
  `Last_Updt` date DEFAULT NULL,
  `Status` varchar(255) DEFAULT NULL,
  `StatDetail` text,
  `Year_From` int DEFAULT NULL,
  `Year_To` int DEFAULT NULL,
  `Ref_Detail` text,
  `Ref_ID` varchar(255) DEFAULT NULL,
  `Remarks` text,
  PRIMARY KEY (`OID`),
  KEY `Site_ID` (`Site_ID`),
  KEY `Ftr_ID` (`Ftr_ID`),
  CONSTRAINT `history_ibfk_1` FOREIGN KEY (`Site_ID`) REFERENCES `site` (`site_id`),
  CONSTRAINT `history_ibfk_2` FOREIGN KEY (`Ftr_ID`) REFERENCES `feature` (`Ftr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;