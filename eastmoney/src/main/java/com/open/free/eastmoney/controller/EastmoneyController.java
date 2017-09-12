package com.open.free.eastmoney.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.open.free.eastmoney.common.EastmoneyConstants;
import com.open.free.eastmoney.entity.Hotstockrank;
import com.open.free.eastmoney.entity.Transaction;
import com.open.free.eastmoney.service.IHotstockrankService;
import com.open.free.eastmoney.service.ITransactionService;
import com.open.free.eastmoney.utils.DateUtil;
import com.open.free.eastmoney.utils.HttpClientUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lcssx on 9/8/2017.
 */
@RestController
@RequestMapping(value = "east")
public class EastmoneyController {
    @Resource
    private ITransactionService transactionService;
    @Resource
    private IHotstockrankService hotstockrankService;

    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(@PathVariable String iptvcode) {
        return "ok";
    }

    public Map getIncomeRank(Integer type) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        int totalPage = 0;
        int pageNumber = 0;
        do{
            pageNumber++;
            if(totalPage!=0 && pageNumber>totalPage){
                break;
            }

            long now = System.currentTimeMillis();
            String url = null;
            if(type == EastmoneyConstants.TYPE_PRELIMINARY_MATCH){
                url = EastmoneyConstants.GetIncomeRank_135
                        .replace("${pageNumber}", ""+pageNumber)
                        .replace("${currentTime}", ""+now);
            }else if(type == EastmoneyConstants.TYPE_FINALS_MATCH){
                url = EastmoneyConstants.GetIncomeRank_136
                        .replace("${pageNumber}", ""+pageNumber)
                        .replace("${currentTime}", ""+now);
            }
            String result = HttpClientUtil.doGet(url);
            String dataJson = result.substring(11, result.length()-1);
            JSONObject transactionJSONObj = JSON.parseObject(dataJson);

            if(totalPage == 0){
                Integer totalCnt = transactionJSONObj.getInteger("totalCnt");
                Integer listSize = transactionJSONObj.getInteger("listSize");
                totalPage = totalCnt/listSize + 1;
            }


            JSONArray dataArray = transactionJSONObj.getJSONArray("data");
            if(dataArray==null || dataArray.isEmpty()){
                break;
            }
            String theLastDate = null;
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
                Integer issell = 0;
                if("卖出".equals(mmbz)){
                    issell = 1;
                }else if("买入".equals(mmbz)){
                    issell = 0;
                }else {
                    issell = -1;
                }
                Double price = dataTmp.getDouble("cjjg");
                String tzrq = dataTmp.getString("tzrq");
                if(i == dataArray.size()){
                    theLastDate = tzrq;
                }
                String tzsjTmp = dataTmp.getString("tzsj");
                tzsjTmp = tzsjTmp.length()==8 ? tzsjTmp : ("0"+tzsjTmp);
                String tzsj = tzsjTmp.substring(0,2)+":"+tzsjTmp.substring(2,4)+":"+tzsjTmp.substring(4,6)+"."+tzsjTmp.substring(6,8);

                EntityWrapper<Transaction> wrapper = new EntityWrapper<>();
                wrapper.where("type={0}", type)
                        .and("zjzh={0}", zjzh)
                        .and("userid={0}", userid)
                        .and("fullcode={0}", fullcode)
                        .and("issell={0}", issell)
                        .and("price={0}", price)
                        .and("date_riqi={0}", tzrq)
                        .and("date_sijian={0}", tzsj);

                Transaction transaction = new Transaction();
                transaction.setType(type);
                transaction.setZjzh(zjzh);
                transaction.setUserid(userid);
                transaction.setUsernick(uidnick);
                transaction.setRanking(ranking);
                transaction.setFullcode(fullcode);
                transaction.setStockname(stkName);
                transaction.setStockmarketcode(stkMktCode);
                transaction.setIssell(issell);
                transaction.setPrice(price);
                transaction.setDateRiqi(tzrq);
                transaction.setDateSijian(tzsj);

                Transaction transactionOld = transactionService.selectOne(wrapper);
                if(transactionOld == null){
                    transaction.setCreateTime(new Date());
                    transactionService.insert(transaction);
                }else{
                    transaction.setId(transactionOld.getId());
                    transaction.setUpdateTime(new Date());
                    transactionService.updateById(transaction);
                }
                EastmoneyConstants.TRANSACTION_LOG.info(transaction.toString());
            }
            String twoDayAgo = DateUtil.addDay(new Date(), -2);
            if(theLastDate!=null && theLastDate.compareTo(twoDayAgo)<=0){
                break;
            }

            Thread.sleep(1000);
        }while(true);


        return resultMap;
    }

    public Map hotStockRank(Integer type) throws Exception {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        int totalPage = 0;
        int pageNumber = 0;
        String riqi = DateUtil.date2Str(new Date(), DateUtil.FORMAT_yyyyMMdd);
        do{
            pageNumber++;
            if(totalPage!=0 && pageNumber>totalPage){
                break;
            }

            long now = System.currentTimeMillis();
            String url = null;
            if(type == EastmoneyConstants.MMJY_BUY){
                url = EastmoneyConstants.HotRank_136_0
                        .replace("${pageNumber}", ""+pageNumber)
                        .replace("${currentTime}", ""+now);
            }else if(type == EastmoneyConstants.MMJY_SELL){
                url = EastmoneyConstants.HotRank_136_1
                        .replace("${pageNumber}", ""+pageNumber)
                        .replace("${currentTime}", ""+now);
            }
            String result = HttpClientUtil.doGet(url);
            String dataJson = result.substring(11, result.length()-1);
            JSONObject transactionJSONObj = JSON.parseObject(dataJson);

            if(totalPage == 0){
                Integer totalCnt = transactionJSONObj.getInteger("totalCnt");
                Integer listSize = transactionJSONObj.getInteger("listSize");
                totalPage = totalCnt/listSize + 1;
            }


            JSONArray dataArray = transactionJSONObj.getJSONArray("data");
            if(dataArray==null || dataArray.isEmpty()){
                break;
            }
            for(int i=0; i<dataArray.size(); i++){
                JSONObject dataTmp = dataArray.getJSONObject(i);

                Integer ranking = dataTmp.getInteger("ranking");
                String fullcode = dataTmp.getString("fullcode");
                String stkName = dataTmp.getString("stkName");
                String stkMktCode = dataTmp.getString("stkMktCode");
                Double price = dataTmp.getDouble("price");
                Double prcPcnt = dataTmp.getDouble("prcPcnt");
                Integer zhCnt = dataTmp.getInteger("zhCnt");

                EntityWrapper<Hotstockrank> wrapper = new EntityWrapper<>();
                wrapper.where("riqi={0}", riqi)
                        .and("issell={0}", type)
                        .and("ranking={0}", ranking);

                Hotstockrank hotstockrank = new Hotstockrank();
                hotstockrank.setRiqi(riqi);
                hotstockrank.setRanking(ranking);
                hotstockrank.setFullcode(fullcode);
                hotstockrank.setStockname(stkName);
                hotstockrank.setStockmarketcode(stkMktCode);
                hotstockrank.setIssell(type);
                hotstockrank.setPrice(price);
                hotstockrank.setPrcPcnt(prcPcnt);
                hotstockrank.setZhCnt(zhCnt);

                Hotstockrank hotstockrankOld = hotstockrankService.selectOne(wrapper);
                if(hotstockrankOld != null){
                    hotstockrank.setId(hotstockrankOld.getId());
                    hotstockrank.setUpdateTime(new Date());
                    hotstockrankService.updateById(hotstockrank);
                }else{
                    hotstockrank.setCreateTime(new Date());
                    hotstockrankService.insert(hotstockrank);
                }

                EastmoneyConstants.HOTSTOCKRANK_LOG.info(hotstockrank.toString());
            }
            Thread.sleep(1000);
        }while(true);


        return resultMap;
    }

}
