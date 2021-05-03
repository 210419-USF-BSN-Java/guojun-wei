package com.revature.models;

import java.util.Date;

public class Item {
	@Override
	public String toString() {
		return "Item [itemId=" + itemId + ", itemName=" + itemName + ", availability=" + availability + ", timeStamp="
				+ timeStamp + "]";
	}

	private Integer itemId;
	private String itemName;
	private boolean availability;
	private Date timeStamp;
	
	public Item(Integer itemId, String itemName, boolean availability, Date timeStamp) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.availability = availability;
		this.timeStamp = timeStamp;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
	
}
