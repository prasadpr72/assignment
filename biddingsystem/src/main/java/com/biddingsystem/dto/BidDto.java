package com.biddingsystem.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "BID")
public class BidDto {

	@Id
	// @JsonIgnore
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bidId;

	@Column(nullable = false,unique=true)
	private String itemCode;
	@Column(nullable = false)
	private Double bidPrice;
	@Column(nullable = false)
	private Double stepRate;
	@Column(nullable = false)
	private String bidStatus;
	@Column(nullable = false)
	private String lastUpdatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	@Version
	private Integer versionId;

	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public Double getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(Double bidPrice) {
		this.bidPrice = bidPrice;
	}

	public Double getStepRate() {
		return stepRate;
	}

	public void setStepRate(Double stepRate) {
		this.stepRate = stepRate;
	}

	public String getBidStatus() {
		return bidStatus;
	}

	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = new Date();
	}

	public Integer getVersionId() {
		return versionId;
	}

	public void setVersionId(Integer versionId) {
		this.versionId = versionId;
	}

	@Override
	public String toString() {
		return "BidDto [bidId=" + bidId + ", itemCode=" + itemCode
				+ ", bidPrice=" + bidPrice + ", stepRate=" + stepRate
				+ ", bidStatus=" + bidStatus + ", lastUpdatedBy="
				+ lastUpdatedBy + ", updatedDate=" + updatedDate + "]";
	}

}
