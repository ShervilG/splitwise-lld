package repo;

import model.Expense;

import java.util.List;

public interface ExpenseRepository {
  void addExpense(String creditorId, String debitorId, int amount);
  List<Expense> getAll();
}
