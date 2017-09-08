package com.open.free.eastmoney;

import com.open.free.eastmoney.common.EastmoneyConstants;
import com.open.free.eastmoney.controller.EastmoneyController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EastmoneyApplication.class)
public class EastmoneyTest {
    @Resource
    private EastmoneyController eastmoneyController;

    @Test
    public void testAll(){
        testGetIncomeRank_135();
        testGetIncomeRank_136();
        testHotStockRank_0();
        testHotStockRank_1();
    }



    @Test
    public void testGetIncomeRank_135(){
        try{
            eastmoneyController.getIncomeRank(EastmoneyConstants.TYPE_PRELIMINARY_MATCH);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testGetIncomeRank_136(){
        try{
            eastmoneyController.getIncomeRank(EastmoneyConstants.TYPE_FINALS_MATCH);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    public void testHotStockRank_0(){
        try{
            eastmoneyController.hotStockRank(EastmoneyConstants.MMJY_BUY);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void testHotStockRank_1(){
        try{
            eastmoneyController.hotStockRank(EastmoneyConstants.MMJY_SELL);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
