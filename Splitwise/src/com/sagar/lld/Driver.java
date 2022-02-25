package com.sagar.lld;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sagar.lld.bean.EqualSplit;
import com.sagar.lld.bean.ExactSplit;
import com.sagar.lld.bean.ExpenseType;
import com.sagar.lld.bean.PercentageSplit;
import com.sagar.lld.bean.Split;
import com.sagar.lld.bean.User;
import com.sagar.lld.service.ExpenseManager;

public class Driver {
	public static void main(String[] args) {
		ExpenseManager expenseManager = new ExpenseManager();
		expenseManager.addUser(new User("u1", "USER1", "asfasjf", "14124"));
		expenseManager.addUser(new User("u2", "USER2", "asfasjf", "14124"));
		expenseManager.addUser(new User("u3", "USER3", "asfasjf", "14124"));
		expenseManager.addUser(new User("u4", "USER4", "asfasjf", "14124"));

		Scanner sc = new Scanner(System.in);
		while (true) {
			String command = sc.nextLine();
			String[] commands = command.split(" ");
			String commandType = commands[0];

			switch (commandType) {
			case "SHOW":
				if (commands.length == 1)
					expenseManager.showBalances();
				else {
					expenseManager.showBalance(commands[1]);
				}
				break;
			case "EXPENSE":
				String paidBy = commands[1];
				double amount = Double.parseDouble(commands[2]);
				int totalUsers = Integer.parseInt(commands[3]);
				String expenseType = commands[4 + totalUsers];
				List<Split> splits = new ArrayList<Split>();
				switch (expenseType) {
				case "EQUAL":
					for (int i = 0; i < totalUsers; i++) {
						splits.add(new EqualSplit(expenseManager.users.get(commands[4 + i])));
					}
					expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits);
					break;
				case "EXACT":
					for (int i = 0; i < totalUsers; i++) {
						splits.add(new ExactSplit(expenseManager.users.get(commands[4 + i]),
								Double.parseDouble(commands[5 + totalUsers + i])));
					}
					expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits);
					break;
				case "PERCENT":
					for (int i = 0; i < totalUsers; i++) {
						splits.add(new PercentageSplit(expenseManager.users.get(commands[4 + i]),
								Double.parseDouble(commands[5 + totalUsers + i])));
					}
					expenseManager.addExpense(ExpenseType.PERCENTAGE, amount, paidBy, splits);
					break;
				default:
					break;
				}
			default:
				break;
			}
		}
	}

}
