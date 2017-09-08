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
public class Transaction extends Model<Transaction> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
    /**
     * 类型：135-预赛；136-决赛；
     */
	private Integer type;
    /**
     * 组合ID
     */
	private String zjzh;
	private String userid;
	private String usernick;
    /**
     * 排名名次
     */
	private Integer ranking;
	private String fullcode;
	private String stockname;
	private String stockmarketcode;
    /**
     * 买卖交易类型：0-买入；1-卖出；
     */
	private Integer issell;
	private Double price;
    /**
     * 日期点 格式：yyyyMMdd
     */
	@TableField("date_riqi")
	private String dateRiqi;
	@TableField("date_sijian")
	private String dateSijian;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getZjzh() {
		return zjzh;
	}

	public void setZjzh(String zjzh) {
		this.zjzh = zjzh;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsernick() {
		return usernick;
	}

	public void setUsernick(String usernick) {
		this.usernick = usernick;
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

	public Integer getIssell() {
		return issell;
	}

	public void setIssell(Integer issell) {
		this.issell = issell;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDateRiqi() {
		return dateRiqi;
	}

	public void setDateRiqi(String dateRiqi) {
		this.dateRiqi = dateRiqi;
	}

	public String getDateSijian() {
		return dateSijian;
	}

	public void setDateSijian(String dateSijian) {
		this.dateSijian = dateSijian;
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
		return "Transaction{" +
			"id=" + id +
			", type=" + type +
			", zjzh=" + zjzh +
			", userid=" + userid +
			", usernick=" + usernick +
			", ranking=" + ranking +
			", fullcode=" + fullcode +
			", stockname=" + stockname +
			", stockmarketcode=" + stockmarketcode +
			", issell=" + issell +
			", price=" + price +
			", dateRiqi=" + dateRiqi +
			", dateSijian=" + dateSijian +
			", createTime=" + createTime +
			", updateTime=" + updateTime +
			"}";
	}
}
