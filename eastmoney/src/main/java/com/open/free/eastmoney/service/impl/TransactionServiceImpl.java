package com.open.free.eastmoney.service.impl;

import com.open.free.eastmoney.entity.Transaction;
import com.open.free.eastmoney.mapper.TransactionMapper;
import com.open.free.eastmoney.service.ITransactionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Liuchsh
 * @since 2017-09-08
 */
@Service
public class TransactionServiceImpl extends ServiceImpl<TransactionMapper, Transaction> implements ITransactionService {
	
}
