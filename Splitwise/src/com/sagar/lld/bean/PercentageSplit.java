package com.sagar.lld.bean;

public class PercentageSplit extends Split{
	private double percent;

	public PercentageSplit(User user, double percent) {
		super(user);
		this.percent=percent;
		// TODO Auto-generated constructor stub
	}

	public double getPercent() {
		return percent;
	}

	public void setPercent(double percent) {
		this.percent = percent;
	}
	
	

}
