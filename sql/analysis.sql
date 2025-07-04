-- 涨停行情
select a.ts_code,
       b.name,
       b.industry_name_l1,
       b.industry_name_l2,
       b.industry_name_l3,
       b.area,
       a.pct_chg,
       a.close,
       a.pre_close,
       a.high,
       a.low,
       a.pe_ttm,
       a.pb,
       a.ps_ttm,
       a.volume_ratio,
       a.turnover_rate,
       a.amount,
       a.conti_up,
       b.market,
       a.total_mv,
       a.trade_date
from common_daily.`${tradeDate}` as a
         inner join stock_list.industry_member as b on a.ts_code = b.ts_code
where pct_chg > 9
order by industry_name_l1, industry_name_l2, industry_name_l3, total_mv desc

-- 主营业务
select a.ts_code,
       b.name,
       b.area,
       a.pct_chg,
       b.industry_name_l1,
       b.industry_name_l2,
       b.industry_name_l3,
       c.main_business,
       b.market,
       a.total_mv,
       a.pb,
       a.trade_date
from common_daily.`20241211` as a
         left join stock_list.industry_member as b on a.ts_code = b.ts_code
         left join finance.fina_main as c on b.ts_code = c.ts_code
where pct_chg > 9
order by industry_name_l1, industry_name_l2, industry_name_l3, total_mv desc;


-- 前五股东
select a.ts_code,
       b.name,
       b.area,
       a.pct_chg,
       b.industry_name_l1,
       b.industry_name_l2,
       b.industry_name_l3,
       c.top_holder,
       b.market,
       a.total_mv,
       a.pb,
       a.trade_date
from common_daily.`20241121` as a
         left join stock_list.industry_member as b on a.ts_code = b.ts_code
         left join finance.top_hold as c on b.ts_code = c.ts_code
where pct_chg > 9
order by industry_name_l1, industry_name_l2, industry_name_l3, total_mv desc;



-- 10天信息

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
where y_pct3 > 9
order by industry_name_l1, industry_name_l2, industry_name_l3, total_mv desc;

-- A股百强名单
select a.ts_code,b.name,b.area,b.industry_name_l1,b.industry_name_l2,b.industry_name_l3,a.pct_chg,a.close,a.pe,a.pb,a.ps,a.turnover_rate,a.amount,b.market,a.total_mv,a.trade_date
from common_daily.`20240105` as a
         left join stock_list.industry_member as b on a.ts_code = b.ts_code
order by total_mv desc limit 100;


-- 资金流向
select a.ts_code,b.name,b.area,b.industry_name_l1,b.industry_name_l2,b.industry_name_l3,
       a.pct_chg,c.buy_elg_amount,c.buy_lg_amount,c.buy_md_amount,c.buy_sm_amount,
       c.sell_elg_amount,c.sell_lg_amount,c.sell_md_amount,c.sell_sm_amount,a.amount,a.turnover_rate,b.market,a.total_mv
from common_daily.`20250612` as a
         left join stock_list.industry_member as b
                   on a.ts_code = b.ts_code
         left join money_flow.`20250612` as c
                   on b.ts_code = c.ts_code;


-- 五日内两板以上
select a.ts_code,a.pct_chg,a.close,a.up10_count5,a.up10_count10,a.up10_count15,a.up10_count20,
       a.down10_count5,a.down10_count10,a.down10_count15,a.down10_count20,a.conti_up,
       b.market,a.total_mv,a.trade_date
from common_daily.`20250612` as a
         left join stock_list.industry_member as b
                   on a.ts_code = b.ts_code
where a.up10_count5 > 1;



-- 任意两天涨跌幅差
select  a.ts_code,a.`close` as pre_close,b.`close` as post_close,c.total_mv ,d.market
from hfq_daily.`20200106` as a inner join hfq_daily.`20250106` as b
                                          on a.ts_code = b.ts_code
                               right join common_daily.`20250106` as c
                                          on b.ts_code = c.ts_code
                               right join stock_list.industry_member as d
                                          on c.ts_code = d.ts_code ;

select a.ts_code,a.`close`,b.total_mv,b.trade_date close from hfq_daily.`20250108` as a
    left join common_daily.`20250108` as b
on a.ts_code = b.ts_code
order by b.total_mv desc;


-- 涨停板
select a.ts_code,a.name,a.trade_date,a.lu_time,a.last_time, a.lu_desc,a.theme,a.status,b.total_mv,b.trade_date,
       c.area,c.industry_name_l1,c.industry_name_l2,c.industry_name_l3,c.market
from kpl_list.`20250109` as a
         left join common_daily.`20250109` as b on a.ts_code = b.ts_code
         left join stock_list.industry_member as c on b.ts_code = c.ts_code ;
