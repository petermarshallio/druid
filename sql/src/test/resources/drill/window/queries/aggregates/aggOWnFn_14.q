SELECT MIN(lastVal_c1) FROM (SELECT LAST_VALUE(c1) OVER(PARTITION BY c2 ORDER BY c1) lastVal_c1 , c2 FROM "tblWnulls.parquet") sub_query