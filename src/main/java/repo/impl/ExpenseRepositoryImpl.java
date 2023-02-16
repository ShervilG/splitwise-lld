package repo.impl;

import model.Expense;
import repo.ExpenseRepository;

import java.util.*;

public class ExpenseRepositoryImpl implements ExpenseRepository {
  private final Map<String, Expense> expenseMap;

  public ExpenseRepositoryImpl() {
    this.expenseMap = new HashMap<>();
  }

  @Override
  public void addExpense(String creditorId, String debitorId, int amount) {
    String expenseId = UUID.randomUUID().toString();
    Expense expense = new Expense(expenseId, creditorId, debitorId, amount);

    this.expenseMap.put(expenseId, expense);
  }

  @Override
  public List<Expense> getAll() {
    return new ArrayList<>(this.expenseMap.values());
  }
}
