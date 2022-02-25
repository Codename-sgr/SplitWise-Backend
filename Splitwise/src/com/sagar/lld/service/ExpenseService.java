package com.sagar.lld.service;

import java.util.List;

import com.sagar.lld.bean.EqualSplit;
import com.sagar.lld.bean.ExactSplit;
import com.sagar.lld.bean.Expense;
import com.sagar.lld.bean.ExpenseType;
import com.sagar.lld.bean.PercentageSplit;
import com.sagar.lld.bean.Split;
import com.sagar.lld.bean.User;

public class ExpenseService {
	public static Expense createExpense(ExpenseType expenseType,double amount,User paidBy,List<Split> splits) {
		switch (expenseType) {
		case EXACT:
			return new Expense(amount,paidBy,splits);
			
		case PERCENTAGE:
			for(Split split:splits) {
				PercentageSplit percentageSplit=(PercentageSplit)split;
//				System.out.println(percentageSplit.getPercent());
				split.setAmount(amount*percentageSplit.getPercent()/100.0);
			}
			return new Expense(amount, paidBy, splits);
		case EQUAL:
			int totalOwedUsers=splits.size();
			double splitAmount=((double)Math.round(amount*100/totalOwedUsers))/100.0;
			for(Split split:splits) {
				EqualSplit equalSplit=(EqualSplit)split;
//				System.out.println(splitAmount);
				equalSplit.setAmount(splitAmount);
			}
			
			splits.get(0).setAmount(splitAmount+amount-splitAmount*totalOwedUsers);
			return new Expense(amount, paidBy, splits);
		default:
			return null;
			}
	}

}
