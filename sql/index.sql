-- 统计每日涨跌停个数
select a.ts_code,pct_chg,index_code_l1,index_code_l2,index_code_l3 from common_daily.`20250109` as a inner join stock_list.industry_member as b on a.ts_code = b.ts_code;
select index_code,industry_name,level,b.pct_change
from stock_list.industry_list as a inner join shenwan_daily.`20250109` as b on a.index_code = b.ts_code
where level = "L1";


-- 开盘啦题材成员股
select b.ts_code,a.name as concept_name,c.name,a.desc,a.hot_num,c.area,c.industry_name_l1,c.industry_name_l2,c.industry_name_l3,
       b.pct_chg,b.`close`,b.trade_date,b.total_mv,b.pb
from kpl_concept_cons.`20250109` as a
         left join common_daily.`20250109` as b
                   on a.con_code = b.ts_code
         left join stock_list.industry_member as c
                   on b.ts_code = c.ts_code
where a.name='AI算力概念';


-- 东方财富题材成员股
select a.concept_name,a.con_code,a.name,a.trade_date,b.area,b.industry_name_l1,b.industry_name_l2,b.industry_name_l3,b.market,
       c.pct_chg,c.pb,c.pe,c.ps,c.amount,c.turnover_rate,c.total_mv
from dc_member.`20250109` as a
         left join stock_list.industry_member as b on a.con_code = b.ts_code
         left join common_daily.`20250109` as c on b.ts_code = c.ts_code
where a.concept_name='首发经济';

-- 主营业务3(开盘啦)
select a.name as concept_name,a.con_name as name,a.con_code as ts_code,b.pb,b.total_mv ,c.market,c.industry_name_l1,c.industry_name_l2,c.industry_name_l3,
       d.province,d.city,d.main_business,d.business_scope,b.trade_date
from kpl_concept_cons.`20250109` as a
         left join common_daily.`20250109` as b on a.con_code = b.ts_code
         left join stock_list.industry_member as c on b.ts_code = c.ts_code
         left join stock_list.stock_company as d on c.ts_code = d.ts_code
where a.ts_code='000025.KP';

-- 主营业务3(东方财富)
select a.concept_name,a.name,a.con_code as ts_code,b.pb,b.total_mv,c.market,c.industry_name_l1,c.industry_name_l2,c.industry_name_l3,
       d.province,d.city,d.main_business,d.business_scope,b.trade_date
from dc_member.`20250109` as a
         left join common_daily.`20250109` as b on a.con_code = b.ts_code
         left join stock_list.industry_member as c on b.ts_code = c.ts_code
         left join stock_list.stock_company as d on c.ts_code = d.ts_code
where a.ts_code='BK1186.DC';


-- 五日内涨停次数
select c.name,c.area,c.industry_name_l1,c.industry_name_l2,c.industry_name_l3,b.pct_chg,b.close,
       b.up10_count5,b.up10_count10,b.up10_count15,b.up10_count20,b.down10_count5,b.down10_count10,b.down10_count15,b.down10_count20,
       b.conti_up,c.market,b.total_mv,b.pb,b.trade_date
from kpl_concept_cons.`20250109` as a
         left join common_daily.`20250109` as b on a.con_code = b.ts_code
         left join stock_list.industry_member as c on b.ts_code = c.ts_code ;


-- 开盘啦 资金流向
select a.name as concept_name,b.ts_code,c.name,c.area,c.industry_name_l1,c.industry_name_l2,c.industry_name_l3,b.pct_chg,
       d.buy_elg_amount,d.buy_lg_amount,d.buy_md_amount,d.buy_sm_amount,d.sell_elg_amount,d.sell_lg_amount,d.sell_md_amount,d.sell_sm_amount,d.net_mf_amount,
       b.amount,b.turnover_rate,c.market,b.total_mv,b.pb,b.trade_date
from kpl_concept_cons.`20250109` as a
         left join common_daily.`20250109` as b on a.con_code = b.ts_code
         left join stock_list.industry_member as c on b.ts_code = c.ts_code
         left join money_flow.`20250612` as d on c.ts_code = d.ts_code
where a.name = 'AI算力概念';