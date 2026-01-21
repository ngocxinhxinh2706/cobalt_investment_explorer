CREATE TABLE `loc_poly` (
  `OBJECTID` int NOT NULL,
  `Shape` varchar(255) DEFAULT NULL,
  `Site_ID` varchar(255) DEFAULT NULL,
  `Ftr_ID` varchar(255) DEFAULT NULL,
  `Ftr_Name` varchar(255) DEFAULT NULL,
  `Last_Updt` date DEFAULT NULL,
  `Remarks` text,
  `Area_SqKm` varchar(255) DEFAULT NULL,
  `Area_Acres` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`OBJECTID`),
  KEY `Site_ID` (`Site_ID`),
  KEY `Ftr_ID` (`Ftr_ID`),
  CONSTRAINT `loc_poly_ibfk_1` FOREIGN KEY (`Site_ID`) REFERENCES `site` (`site_id`),
  CONSTRAINT `loc_poly_ibfk_2` FOREIGN KEY (`Ftr_ID`) REFERENCES `feature` (`Ftr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

