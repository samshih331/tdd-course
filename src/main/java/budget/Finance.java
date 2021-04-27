package budget;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Finance {

  BudgetRepo budgetRepo;

  public Finance(BudgetRepo budgetRepo) {
    this.budgetRepo = budgetRepo;
  }

  public double queryBudget(LocalDate start, LocalDate end) {
    List<Budget> all = budgetRepo.getAll();

    double result = 0;

    for (Budget budget : all) {
      String yearMonth = budget.getYearMonth();

      DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMM");
      YearMonth ym = YearMonth.parse(yearMonth, fmt);
      LocalDate dt = ym.atDay(1);

      if (dt.isEqual(start) || dt.isEqual(end) || dt.isAfter(start) && dt.isBefore(end)) {

        result = result + budget.getAmount();
      }
    }

    return result;
  }
}
