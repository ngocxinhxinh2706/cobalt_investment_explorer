CREATE DEFINER=`root`@`localhost` PROCEDURE `FilterGeolMinOcc`(
    IN p_commodity VARCHAR(100), 
    IN p_ftr_type VARCHAR(100)
)
BEGIN
    SELECT 
        Site_ID, 
        Ftr_Name, 
        Commodity, 
        Ftr_type, 
        Last_Updt AS Last_Updated, 
        Value_mat
    FROM 
        GeolMinOcc
    WHERE 
        Commodity = p_commodity 
        AND Ftr_type = p_ftr_type;
END