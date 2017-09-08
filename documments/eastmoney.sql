

SELECT fullcode,issell,stockname,stockmarketcode,COUNT(1) AS ct
FROM `transaction`
GROUP BY fullcode,issell
ORDER BY ct DESC;