package com.sagar.lld.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sagar.lld.bean.Expense;
import com.sagar.lld.bean.ExpenseType;
import com.sagar.lld.bean.Split;
import com.sagar.lld.bean.User;

public class ExpenseManager {
	private List<Expense> expenses;
	public Map<String, User> users;
	private Map<String, Map<String, Double>> balanceSheet;
	
	public ExpenseManager() {
		this.expenses = new ArrayList<Expense>();
		this.users = new HashMap<String, User>();
		this.balanceSheet = new HashMap<String, Map<String,Double>>();
	}
	
	public void addUser(User user) {
		users.put(user.getId(),user);
		balanceSheet.put(user.getId(), new HashMap<String, Double>());
	}
	
	public void addExpense(ExpenseType expenseType,double amount,String paidBy,List<Split> splits) {
		Expense expense=ExpenseService.createExpense(expenseType,amount,users.get(paidBy),splits);
		expenses.add(expense);
		for(Split split:expense.getSplits()) {
			String paidTo=split.getUser().getId();
			Map<String, Double> balances=balanceSheet.get(paidBy);
			
			if(!balances.containsKey(paidTo)) {
				balances.put(paidTo, 0.0);
			}
			
			balances.put(paidTo, balances.get(paidTo)+split.getAmount());
			
			balances=balanceSheet.get(paidTo);
			if(!balances.containsKey(paidBy)) {
				balances.put(paidBy, 0.0);
			}
			
			balances.put(paidBy, balances.get(paidBy)-split.getAmount());
		}
		
	}
	
	public void showBalance(String userId) {
		boolean isEmpty=true;
		
		for(Map.Entry<String,Double> owedByUsers:balanceSheet.get(userId).entrySet()) {
			if(owedByUsers.getValue()!=0) {
				isEmpty=false;
				printBalance(userId,owedByUsers.getKey(),owedByUsers.getValue());
			}
			
		}
		if(isEmpty) {
			System.out.println("No Balances");
		}
		
	}
	
	public void showBalances() {
		boolean isEmpty=true;
		for(Map.Entry<String, Map<String, Double>> allBalances: balanceSheet.entrySet()) {
			for(Map.Entry<String, Double> owedByUser:allBalances.getValue().entrySet() ) {
				if(owedByUser.getValue()>0) {
					isEmpty=false;
					printBalance(allBalances.getKey(),owedByUser.getKey(),owedByUser.getValue());
				}
			}
		}
		if(isEmpty) {
			System.out.println("No Balances");
		}
		
	}

	private void printBalance(String user1, String user2, Double value) {
		// TODO Auto-generated method stub
		String user1Name=users.get(user1).getName();
		String user2Name=users.get(user2).getName();
		
		if(value<0) {
			System.out.println(user1Name+" owes "+user2Name+" : "+Math.abs(value));
		}else if(value>0){
			System.out.println(user2Name+" owes "+user1Name+" : "+Math.abs(value));			
		}
		
		
	}
	
	
	

}
