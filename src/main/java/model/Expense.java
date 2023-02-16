package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
  private String expenseId;
  private String creditorId;
  private String debitorId;
  private int amount;
}
