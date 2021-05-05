package com.revature.models;

public class Count {
	private String week;
	private Double sum;
	
	public Count(String week, Double sum) {
		super();
		this.week = week;
		this.sum = sum;
	}
	
	public String getWeek() {
		return week;
	}
	public void setWeek(String week) {
		this.week = week;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}

	@Override
	public String toString() {
		return "week= " + week + "  sum= $" + sum + "\n";
	}
}
