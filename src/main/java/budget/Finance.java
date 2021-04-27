package budget;

import java.time.LocalDate;
import java.util.List;

public class Finance {

  BudgetRepo budgetRepo = new BudgetRepoImpl();

  public double queryBudget(LocalDate start, LocalDate end) {
    List<Budget> all = budgetRepo.getAll();
    for (Budget budget : all) {
      String yearMonth = budget.getYearMonth();
      if (yearMonth.equals("202104")) {
        return budget.getAmount();
      }

    }

    return 0d;
  }
}
