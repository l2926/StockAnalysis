--财务模块横览
select a.ts_code,b.name,b.area,b.industry_name_l1,b.industry_name_l2,b.industry_name_l3,total_assets,total_liab,total_revenue,total_cogs,oper_cost,n_income_attr_p,
       b.market,c.total_mv,c.pb,c.trade_date
from finance.`2024` as a
         inner join stock_list.industry_member as b
                    on a.ts_code = b.ts_code
         inner join common_daily.`20250108` as c
                    on a.ts_code = c.ts_code
where index_code_l2 = '801737.SI'
order by total_mv desc;



--总资产
select a.`20101231` as num2010,a.`20111231` as num2011,a.`20121231` as num2012,a.`20131231` as num2013,a.`20141231` as num2014,a.`20151231` as num2015,
       a.`20161231` as num2016,a.`20171231` as num2017,a.`20181231` as num2018,a.`20191231` as num2019,a.`20201231` as num2020,
       a.`20211231` as num2021,a.`20221231` as num2022,a.`20231231` as num2023,a.`20241231` as num2024,a.`20251231` as num2025,
       b.name,b.area,b.industry_name_l1,b.industry_name_l2,b.industry_name_l3,b.market,c.total_mv,c.pb,c.trade_date
from finance.total_assets as a
         inner join stock_list.industry_member as b on a.ts_code = b.ts_code
         inner join common_daily.`20251216` as c on b.ts_code = c.ts_code ;