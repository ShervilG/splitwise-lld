package service;

import model.Expense;

import java.util.List;

public interface ExpenseService {
  void addExpense(String creditorId, String debitorId, int amount);
  List<Expense> getAll();
  void simplifyDebts();
}
