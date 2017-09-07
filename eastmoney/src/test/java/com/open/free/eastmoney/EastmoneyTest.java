package com.open.free.eastmoney;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.open.free.eastmoney.utils.HttpClientUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = EastmoneyApplication.class)
public class EastmoneyTest {
//    private static final Logger EASTMONEY_LOG = LoggerFactory.getLogger("eastmoneyLog");

    // 预赛收益率前500高手最近成交
    private static final String GetIncomeRank_135 = "https://contest.securities.eastmoney.com/dsapi/Nuggets/GetIncomeRank_135_${pageNumber}_10002.html?jsonprnd=10002&_=${currentTime}&cb=jsonp10002";
    // 决赛收益率前500高手最近成交
    private static final String GetIncomeRank_136 = "https://contest.securities.eastmoney.com/dsapi/Nuggets/GetIncomeRank_136_${pageNumber}_10003.html?jsonprnd=10003&_=${currentTime}&cb=jsonp10003";


    @Test
    public void testGetIncomeRank_135(){
        int totalPage = 0;
        int pageNumber = 0;
        int type = 135;
        do{
            pageNumber++;
            if(pageNumber > totalPage){
                break;
            }

            long now = System.currentTimeMillis();
            String url = GetIncomeRank_135
                    .replace("${pageNumber}", ""+pageNumber)
                    .replace("${currentTime}", ""+now);
            String result = HttpClientUtil.doGet(url);
            String dataJson = result.substring(11, result.length()-1);
            JSONObject transactionJSONObj = JSON.parseObject(dataJson);

            if(totalPage == 0){
                Integer totalCnt = transactionJSONObj.getInteger("totalCnt");
                Integer listSize = transactionJSONObj.getInteger("listSize");
                totalPage = totalCnt/listSize + 1;
            }


            JSONArray dataArray = transactionJSONObj.getJSONArray("data");
            for(int i=0; i<dataArray.size(); i++){
                JSONObject dataTmp = dataArray.getJSONObject(i);

                String zjzh = dataTmp.getString("zjzh");
                String userid = dataTmp.getString("userid");
                String uidnick = dataTmp.getString("uidnick");
                Integer ranking = dataTmp.getInteger("ranking");
                String fullcode = dataTmp.getString("fullcode");
                String stkName = dataTmp.getString("stkName");
                String stkMktCode = dataTmp.getString("stkMktCode");
                String mmbz = dataTmp.getString("mmbz");
                Double price = dataTmp.getDouble("cjjg");
                String tzrq = dataTmp.getString("tzrq");
                String tzsjTmp = dataTmp.getString("tzsj");
                String tzsj = tzsjTmp.substring(0,2)+":"+tzsjTmp.substring(2,4)+":"+tzsjTmp.substring(4,6);

                String printLine = type+"\t"+zjzh+"\t"+userid+"\t"+uidnick+"\t"+ranking+"\t"+fullcode+"\t"+stkName+"\t"+stkMktCode+"\t"+mmbz+"\t"+price+"\t"+tzrq+"\t"+tzsj;
//                EASTMONEY_LOG.info(printLine);
                System.out.println(printLine);
            }
        }while(false);
    }
    @Test
    public void testGetIncomeRank_136(){

    }


}
