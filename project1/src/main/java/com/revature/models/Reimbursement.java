package com.revature.models;

import java.util.Date;

public class Reimbursement {
	public static Integer PENDING = 1;
	
	private int reimbId;
	private double reimbAmount;
	private Date reimbSubmit;
	private Date reimbResolved;
	private String reimbDescription;
	private String reimbReceipt;
	private Integer reimbAuthor; // foreign key
	private Integer reimbResolver; // foreign key
	private Integer reimbStatusId; // foreign key
	private Integer reimbTypeId; // foreign key
	
	private String reimbStatus; // foreign key
	private String reimbType; // foreign key
	private String reimbAuthorName; // foreign key
	private String reimbResolverName; // foreign key
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Reimbursement(int reimbId, double reimbAmount, Date reimbSubmit, Date reimbResolved, String reimbDescription,
			String reimbReceipt, Integer reimbAuthor, Integer reimbResolver, Integer reimbStatusId,
			Integer reimbTypeId) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmit = reimbSubmit;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbReceipt = reimbReceipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
	}
	public Reimbursement(int reimbId, double reimbAmount, Date reimbSubmit, Date reimbResolved, String reimbDescription,
			String reimbReceipt, Integer reimbAuthor, Integer reimbResolver, Integer reimbStatusId,
			Integer reimbTypeId,String reimbStatus,String reimbType,String reimbAuthorName,String reimbResolverName) {
		super();
		this.reimbId = reimbId;
		this.reimbAmount = reimbAmount;
		this.reimbSubmit = reimbSubmit;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbReceipt = reimbReceipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusId = reimbStatusId;
		this.reimbTypeId = reimbTypeId;
		this.reimbStatus = reimbStatus;
		this.reimbType = reimbType;
		this.reimbAuthorName = reimbAuthorName;
		this.reimbResolverName = reimbResolverName;
	}
	public int getReimbId() {
		return reimbId;
	}
	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}
	public double getReimbAmount() {
		return reimbAmount;
	}
	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}
	public Date getReimbSubmit() {
		return reimbSubmit;
	}
	public void setReimbSubmit(Date reimbSubmit) {
		this.reimbSubmit = reimbSubmit;
	}
	public Date getReimbResolved() {
		return reimbResolved;
	}
	public void setReimbResolved(Date reimbResolved) {
		this.reimbResolved = reimbResolved;
	}
	public String getReimbDescription() {
		return reimbDescription;
	}
	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}
	public String getReimbReceipt() {
		return reimbReceipt;
	}
	public void setReimbReceipt(String reimbReceipt) {
		this.reimbReceipt = reimbReceipt;
	}
	public Integer getReimbAuthor() {
		return reimbAuthor;
	}
	public void setReimbAuthor(Integer reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}
	public Integer getReimbResolver() {
		return reimbResolver;
	}
	public void setReimbResolver(Integer reimbResolver) {
		this.reimbResolver = reimbResolver;
	}
	public Integer getReimbStatusId() {
		return reimbStatusId;
	}
	public void setReimbStatusId(Integer reimbStatusId) {
		this.reimbStatusId = reimbStatusId;
	}
	public Integer getReimbTypeId() {
		return reimbTypeId;
	}
	public void setReimbTypeId(Integer reimbTypeId) {
		this.reimbTypeId = reimbTypeId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(reimbAmount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((reimbAuthor == null) ? 0 : reimbAuthor.hashCode());
		result = prime * result + ((reimbDescription == null) ? 0 : reimbDescription.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((reimbReceipt == null) ? 0 : reimbReceipt.hashCode());
		result = prime * result + ((reimbResolved == null) ? 0 : reimbResolved.hashCode());
		result = prime * result + ((reimbResolver == null) ? 0 : reimbResolver.hashCode());
		result = prime * result + ((reimbStatusId == null) ? 0 : reimbStatusId.hashCode());
		result = prime * result + ((reimbSubmit == null) ? 0 : reimbSubmit.hashCode());
		result = prime * result + ((reimbTypeId == null) ? 0 : reimbTypeId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (Double.doubleToLongBits(reimbAmount) != Double.doubleToLongBits(other.reimbAmount))
			return false;
		if (reimbAuthor == null) {
			if (other.reimbAuthor != null)
				return false;
		} else if (!reimbAuthor.equals(other.reimbAuthor))
			return false;
		if (reimbDescription == null) {
			if (other.reimbDescription != null)
				return false;
		} else if (!reimbDescription.equals(other.reimbDescription))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (reimbReceipt == null) {
			if (other.reimbReceipt != null)
				return false;
		} else if (!reimbReceipt.equals(other.reimbReceipt))
			return false;
		if (reimbResolved == null) {
			if (other.reimbResolved != null)
				return false;
		} else if (!reimbResolved.equals(other.reimbResolved))
			return false;
		if (reimbResolver == null) {
			if (other.reimbResolver != null)
				return false;
		} else if (!reimbResolver.equals(other.reimbResolver))
			return false;
		if (reimbStatusId == null) {
			if (other.reimbStatusId != null)
				return false;
		} else if (!reimbStatusId.equals(other.reimbStatusId))
			return false;
		if (reimbSubmit == null) {
			if (other.reimbSubmit != null)
				return false;
		} else if (!reimbSubmit.equals(other.reimbSubmit))
			return false;
		if (reimbTypeId == null) {
			if (other.reimbTypeId != null)
				return false;
		} else if (!reimbTypeId.equals(other.reimbTypeId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", reimbAmount=" + reimbAmount + ", reimbSubmit=" + reimbSubmit
				+ ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription + ", reimbReceipt="
				+ reimbReceipt + ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver
				+ ", reimbStatusId=" + reimbStatusId + ", reimbTypeId=" + reimbTypeId + "]";
	}
	public String getReimbStatus() {
		return reimbStatus;
	}
	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}
	public String getReimbType() {
		return reimbType;
	}
	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}
	public String getReimbAuthorName() {
		return reimbAuthorName;
	}
	public void setReimbAuthorName(String reimbAuthorName) {
		this.reimbAuthorName = reimbAuthorName;
	}
	public String getReimbResolverName() {
		return reimbResolverName;
	}
	public void setReimbResolverName(String reimbResolverName) {
		this.reimbResolverName = reimbResolverName;
	}

	
}
