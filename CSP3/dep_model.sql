
CREATE TABLE `dep_model` (
  `OBJECTID` int NOT NULL,
  `Site_ID` varchar(50) NOT NULL,
  `Ftr_ID` varchar(50) NOT NULL,
  `Ftr_Name` varchar(255) DEFAULT NULL,
  `Last_Updt` date DEFAULT NULL,
  `DpMd_NoNm` varchar(255) DEFAULT NULL,
  `Ref_Detail` text,
  `DpMd_RefID` varchar(255) DEFAULT NULL,
  `GEM_Name` varchar(255) DEFAULT NULL,
  `GEM_RefID` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`OBJECTID`),
  KEY `Site_ID` (`Site_ID`),
  KEY `Ftr_ID` (`Ftr_ID`),
  CONSTRAINT `dep_model_ibfk_1` FOREIGN KEY (`Site_ID`) REFERENCES `site` (`site_id`),
  CONSTRAINT `dep_model_ibfk_2` FOREIGN KEY (`Ftr_ID`) REFERENCES `feature` (`Ftr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;