package com.revature.models;

import java.util.Date;

public class OfferInfo {
	private Integer offerId;
	private Integer userId;
	private Integer itemId;
	private Double price;
	private Integer paymentStatus; // 1 as pending 2 as accept 3 as reject 4 as payed
	private Date timeStamp;
	
	public OfferInfo(Integer offerId, Integer userId, Integer itemId, Double price, Integer paymentStatus,
			Date timeStamp) {
		super();
		this.offerId = offerId;
		this.userId = userId;
		this.itemId = itemId;
		this.price = price;
		this.paymentStatus = paymentStatus;
		this.timeStamp = timeStamp;
	}
	
	public Integer getOfferId() {
		return offerId;
	}
	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "OfferInfo [offerId=" + offerId + ", userId=" + userId + ", itemId=" + itemId + ", price=" + price
				+ ", paymentStatus=" + paymentStatus + ", timeStamp=" + timeStamp + "]";
	}
	
	
	
	
	
	
}
