package budget;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

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
      Integer amount = budget.getAmount();

      DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMM");
      YearMonth ym = YearMonth.parse(yearMonth, fmt);
      LocalDate dt = ym.atDay(1);

      if (dt.getMonth().equals(start.getMonth()) || dt.getMonth().equals(end.getMonth()) || dt.isAfter(start) && dt
          .isBefore(end)) {
        int monthTotalDays = ym.atEndOfMonth().getDayOfMonth();

        LocalDate startCondition;
        LocalDate endCondition;

        if (start.getMonth().equals(dt.getMonth())) {
          startCondition = start;
        } else {
          startCondition = ym.atDay(1);
        }

        if (end.getMonth().equals(dt.getMonth())) {
          endCondition = end;
        } else {
          endCondition = ym.atEndOfMonth();
        }

        long days = DAYS.between(startCondition, endCondition) + 1;
        double amountPerDay = (double) amount / monthTotalDays;
        result = result + (days * amountPerDay);
      }
    }

    return result;
  }
}
