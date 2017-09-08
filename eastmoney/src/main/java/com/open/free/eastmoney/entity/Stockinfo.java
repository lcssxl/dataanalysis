package com.open.free.eastmoney.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Liuchsh
 * @since 2017-09-08
 */
public class Stockinfo extends Model<Stockinfo> {

    private static final long serialVersionUID = 1L;

	private String fullcode;
	private String stockname;
	private String stockmarketcode;


	public String getFullcode() {
		return fullcode;
	}

	public void setFullcode(String fullcode) {
		this.fullcode = fullcode;
	}

	public String getStockname() {
		return stockname;
	}

	public void setStockname(String stockname) {
		this.stockname = stockname;
	}

	public String getStockmarketcode() {
		return stockmarketcode;
	}

	public void setStockmarketcode(String stockmarketcode) {
		this.stockmarketcode = stockmarketcode;
	}

	@Override
	protected Serializable pkVal() {
		return this.fullcode;
	}

	@Override
	public String toString() {
		return "Stockinfo{" +
			"fullcode=" + fullcode +
			", stockname=" + stockname +
			", stockmarketcode=" + stockmarketcode +
			"}";
	}
}
