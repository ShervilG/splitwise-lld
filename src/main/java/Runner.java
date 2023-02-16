import repo.ExpenseRepository;
import repo.impl.ExpenseRepositoryImpl;
import service.ExpenseService;
import service.impl.ExpenseServiceImpl;

public class Runner {

  public static void main(String[] args) {
    ExpenseRepository expenseRepository = new ExpenseRepositoryImpl();
    ExpenseService expenseService = new ExpenseServiceImpl(expenseRepository);

    expenseService.addExpense("A", "B", 100);
    expenseService.addExpense("C", "D", 100);
    expenseService.addExpense("C", "A", 100);
    expenseService.addExpense("B", "C", 200);
    expenseService.addExpense("D", "B", 300);
    expenseService.addExpense("K", "C", 100);

    expenseService.simplifyDebts();
  }
}
