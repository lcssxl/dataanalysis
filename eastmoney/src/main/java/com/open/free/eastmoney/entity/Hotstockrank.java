package com.open.free.eastmoney.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
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
public class Hotstockrank extends Model<Hotstockrank> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String riqi;
    /**
     * 买卖交易类型：0-买入；1-卖出；
     */
	private Integer issell;
    /**
     * 排名名次
     */
	private Integer ranking;
	private String fullcode;
	private String stockname;
	private String stockmarketcode;
	private Double price;
	private Double prcPcnt;
	private Integer zhCnt;
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRiqi() {
		return riqi;
	}

	public void setRiqi(String riqi) {
		this.riqi = riqi;
	}

	public Integer getIssell() {
		return issell;
	}

	public void setIssell(Integer issell) {
		this.issell = issell;
	}

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}

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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPrcPcnt() {
		return prcPcnt;
	}

	public void setPrcPcnt(Double prcPcnt) {
		this.prcPcnt = prcPcnt;
	}

	public Integer getZhCnt() {
		return zhCnt;
	}

	public void setZhCnt(Integer zhCnt) {
		this.zhCnt = zhCnt;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "Hotstockrank{" +
			"id=" + id +
			", riqi=" + riqi +
			", issell=" + issell +
			", ranking=" + ranking +
			", fullcode=" + fullcode +
			", stockname=" + stockname +
			", stockmarketcode=" + stockmarketcode +
			", price=" + price +
			", prcPcnt=" + prcPcnt +
			", zhCnt=" + zhCnt +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
