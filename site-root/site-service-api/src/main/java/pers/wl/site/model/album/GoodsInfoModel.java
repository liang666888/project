package pers.wl.site.model.album;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the TB_GOODS_INFO database table.
 * 
 */
@Entity
@Table(name="TB_GOODS_INFO")
@NamedQuery(name="GoodsInfoModel.findAll", query="SELECT t FROM GoodsInfoModel t")
public class GoodsInfoModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="goods_id")
	private int goodsId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime;

	@Column(name="goods_desc")
	private String goodsDesc;

	@Lob
	@Column(name="goods_detail")
	private String goodsDetail;

	@Column(name="goods_head_img")
	private String goodsHeadImg;

	@Column(name="goods_name")
	private String goodsName;

	@Column(name="goods_price")
	private BigDecimal goodsPrice;

	@Column(name="goods_sn")
	private String goodsSn;

	@Column(name="goods_sort")
	private int goodsSort;

	@Column(name="goods_status")
	private String goodsStatus;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="update_time")
	private Date updateTime;

	@Column(name="user_id")
	private int userId;

	public GoodsInfoModel() {
	}

	public int getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getGoodsDesc() {
		return this.goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getGoodsDetail() {
		return this.goodsDetail;
	}

	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail;
	}

	public String getGoodsHeadImg() {
		return this.goodsHeadImg;
	}

	public void setGoodsHeadImg(String goodsHeadImg) {
		this.goodsHeadImg = goodsHeadImg;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getGoodsPrice() {
		return this.goodsPrice;
	}

	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public String getGoodsSn() {
		return this.goodsSn;
	}

	public void setGoodsSn(String goodsSn) {
		this.goodsSn = goodsSn;
	}

	public int getGoodsSort() {
		return this.goodsSort;
	}

	public void setGoodsSort(int goodsSort) {
		this.goodsSort = goodsSort;
	}

	public String getGoodsStatus() {
		return this.goodsStatus;
	}

	public void setGoodsStatus(String goodsStatus) {
		this.goodsStatus = goodsStatus;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}