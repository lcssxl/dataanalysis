package com.open.free.eastmoney.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EastmoneyConstants {
    // 收益率前500高手最近成交 类型
    public static final int TYPE_PRELIMINARY_MATCH = 135; //预赛
    public static final int TYPE_FINALS_MATCH = 136; //决赛
    // 预赛收益率前500高手最近成交
    public static final String GetIncomeRank_135 = "https://contest.securities.eastmoney.com/dsapi/Nuggets/GetIncomeRank_135_${pageNumber}_10002.html?jsonprnd=10002&_=${currentTime}&cb=jsonp10002";
    // 决赛收益率前500高手最近成交
    public static final String GetIncomeRank_136 = "https://contest.securities.eastmoney.com/dsapi/Nuggets/GetIncomeRank_136_${pageNumber}_10003.html?jsonprnd=10003&_=${currentTime}&cb=jsonp10003";

    // 买卖类型
    public static final int MMJY_BUY = 0; //买入
    public static final int MMJY_SELL = 1; //卖出
    // 今日热门买入股排行榜
    public static final String HotRank_136_0 = "https://contest.securities.eastmoney.com/dsapi/Nuggets/HotRank_136_0_${pageNumber}_10001.html?jsonprnd=10001&_=${currentTime}&cb=jsonp10001";
    // 今日热门卖出股排行榜
    public static final String HotRank_136_1 = "https://contest.securities.eastmoney.com/dsapi/Nuggets/HotRank_136_1_${pageNumber}_10000.html?jsonprnd=10000&_=${currentTime}&cb=jsonp10000";

    public static final Logger TRANSACTION_LOG = LoggerFactory.getLogger("transactionLog");
    public static final Logger HOTSTOCKRANK_LOG = LoggerFactory.getLogger("hotStockRankLog");
}
