CREATE DEFINER=`root`@`localhost` PROCEDURE `CleanAndDisplayContained`()
BEGIN
    SELECT 
        Site_ID, 
        Ftr_ID, 
        Rsrc_Date, 
        REPLACE(Contained, '.111', '') AS Contained, 
        Cont_Units
    FROM 
        Resources; 
END