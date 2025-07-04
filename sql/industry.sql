
-- 行业主营业务
select a.ts_code
     , b.name
     , b.area
     , a.pct_chg
     , b.industry_name_l1
     , b.industry_name_l2
     , b.industry_name_l3
     , c.main_business
     , b.market
     , a.total_mv
     , a.pb
     , a.trade_date
from common_daily.`20241121` as a
         left join stock_list.industry_member as b
                   on a.ts_code = b.ts_code
         left join finance.fina_main as c
                   on b.ts_code = c.ts_code
where index_code_l1 = '801040.SI'
order by industry_name_l1, industry_name_l2, industry_name_l3, total_mv desc;


-- 行业前五股东
select a.ts_code
     , b.name
     , b.area
     , a.pct_chg
     , b.industry_name_l1
     , b.industry_name_l2
     , b.industry_name_l3
     , c.top_holder
     , b.market
     , a.total_mv
     , a.pb
     , a.trade_date
from common_daily.`20241121` as a
         left join stock_list.industry_member as b
                   on a.ts_code = b.ts_code
         left join finance.top_hold as c
                   on b.ts_code = c.ts_code
where index_code_l1 = '801040.SI'
order by industry_name_l1, industry_name_l2, industry_name_l3, total_mv desc;


-- 十天信息

select t.ts_code,
       sl.name,
       sl.industry_name_l1,
       sl.industry_name_l2,
       sl.industry_name_l3,
       t.conti_up,
       t.pct_chg,
       t.y_pct1,
       t.y_pct2,
       t.y_pct3,
       t.y_pct4,
       t.y_pct5,
       t.y_pct6,
       t.y_pct7,
       t.y_pct8,
       t.y_pct9,
       sl.area,
       sl.market,
       t.total_mv,
       t.pb,
       t.trade_date
from common_daily.`20241121` t
         left join stock_list.industry_member sl on t.ts_code = sl.ts_code
where index_code_l1 = '801040.SI'
order by industry_name_l1, industry_name_l2, industry_name_l3, total_mv desc;