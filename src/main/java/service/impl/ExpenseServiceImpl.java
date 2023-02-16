package service.impl;

import javafx.util.Pair;
import model.Expense;
import repo.ExpenseRepository;
import service.ExpenseService;

import java.util.*;

public class ExpenseServiceImpl implements ExpenseService {
  private final ExpenseRepository expenseRepository;

  public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
    this.expenseRepository = expenseRepository;
  }

  @Override
  public void addExpense(String creditorId, String debitorId, int amount) {
    this.expenseRepository.addExpense(creditorId, debitorId, amount);
  }

  @Override
  public List<Expense> getAll() {
    return this.expenseRepository.getAll();
  }

  @Override
  public void simplifyDebts() {
    List<Expense> allExpenses = getAll();
    if (allExpenses == null || allExpenses.size() == 0) {
      return;
    }

    Map<String, Integer> userIdChangeAmountMap = new HashMap<>();
    allExpenses.forEach(expense -> {
      userIdChangeAmountMap.merge(expense.getCreditorId(), expense.getAmount(), Integer::sum);
      userIdChangeAmountMap.merge(expense.getDebitorId(), -expense.getAmount(), Integer::sum);
    });

    Queue<Pair<String, Integer>> debitorHeap = new PriorityQueue<>(
        Comparator.comparingInt(e -> Math.abs(e.getValue())));
    Queue<Pair<String, Integer>> creditorHeap = new PriorityQueue<>(
        Comparator.comparingInt(e -> Math.abs(e.getValue())));

    userIdChangeAmountMap.forEach((k, v) -> {
      System.out.println(k + " ows a total of " + v);

      if (v < 0) {
        debitorHeap.add(new Pair<>(k, v));
      } else if (v > 0) {
        creditorHeap.add(new Pair<>(k, v));
      }
    });

    System.out.println("");

    while (!creditorHeap.isEmpty() && !debitorHeap.isEmpty()) {
      Pair<String, Integer> creditorData = creditorHeap.remove();
      Pair<String, Integer> debitorData = debitorHeap.remove();

      int newAmount = debitorData.getValue() + creditorData.getValue();

      if (Math.abs(debitorData.getValue()) > Math.abs(creditorData.getValue())) {
        if (newAmount != 0) {
          debitorHeap.add(new Pair<>(debitorData.getKey(), newAmount));
        }

        System.out.println(debitorData.getKey() + " ows " + creditorData.getKey() + " " + creditorData.getValue());
      } else {
        if (newAmount != 0) {
          creditorHeap.add(new Pair<>(creditorData.getKey(), newAmount));
        }

        System.out.println(debitorData.getKey() + " ows " + creditorData.getKey() + " " + (-debitorData.getValue()));
      }
    }
  }
}
