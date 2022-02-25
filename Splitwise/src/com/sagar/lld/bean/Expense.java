package com.sagar.lld.bean;

import java.util.List;

public class Expense {
//	private ExpenseType expenseType;
	private String userId;
	private User paidBy;
	private double amount;
	List<Split> splits;
	public Expense( double amount, User paidBy, List<Split> splits) {
		this.paidBy = paidBy;
		this.amount = amount;
		this.splits = splits;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public User getPaidBy() {
		return paidBy;
	}
	public void setPaidBy(User paidBy) {
		this.paidBy = paidBy;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public List<Split> getSplits() {
		return splits;
	}
	public void setSplits(List<Split> splits) {
		this.splits = splits;
	}
	
	
	
	
}
