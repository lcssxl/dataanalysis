SELECT fullcode,stockname,stockmarketcode,cnt,buycnt,sellcnt,(buycnt-sellcnt) AS dif
FROM(
	SELECT fullcode,stockname,stockmarketcode
	,COUNT(1) AS cnt
	,SUM(CASE issell WHEN 0 THEN 1 WHEN 1 THEN 0 ELSE 0 END ) AS buycnt
	,SUM(CASE issell WHEN 0 THEN 0 WHEN 1 THEN 1 ELSE 0 END ) AS sellcnt
	FROM `transaction`
#	WHERE type IN(135,136)
	WHERE type IN(136) AND date_riqi IN('20170908')
	GROUP BY fullcode
	ORDER BY cnt DESC) t
;


SELECT rank1.riqi,rank1.ranking
,rank1.issell,rank1.stockmarketcode,rank1.stockname,rank1.zhCnt
,rank2.issell,rank2.stockmarketcode,rank2.stockname,rank2.zhCnt
FROM hotstockrank rank1
LEFT JOIN hotstockrank rank2 ON rank1.riqi=rank2.riqi AND rank1.ranking=rank2.ranking AND rank1.issell+1=rank2.issell
WHERE rank1.issell!=1
ORDER BY ranking;


##查看详细
SELECT *
FROM `transaction`
WHERE stockmarketcode IN(
'SZ002182'
#,
#'SH600516'
)
ORDER BY date_riqi DESC,date_sijian;