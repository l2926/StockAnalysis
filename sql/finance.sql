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