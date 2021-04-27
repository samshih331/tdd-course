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

      DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMM");
      YearMonth currentYearmonth = YearMonth.parse(yearMonth, dateTimeFormatter);

      YearMonth startYearMonth = YearMonth.from(start);
      YearMonth endYearMonth = YearMonth.from(end);

      if (currentYearmonth.equals(startYearMonth)
          || currentYearmonth.equals(endYearMonth)
          || (currentYearmonth.isAfter(startYearMonth)
              && currentYearmonth.isBefore(endYearMonth))) {
        int monthTotalDays = currentYearmonth.atEndOfMonth().getDayOfMonth();

        LocalDate startCondition;
        LocalDate endCondition;

        if (currentYearmonth.equals(startYearMonth)) {
          startCondition = start;
        } else {
          startCondition = currentYearmonth.atDay(1);
        }

        if (currentYearmonth.equals(endYearMonth)) {
          endCondition = end;
        } else {
          endCondition = currentYearmonth.atEndOfMonth();
        }

        long days = DAYS.between(startCondition, endCondition) + 1;
        double amountPerDay = (double) amount / monthTotalDays;
        result = result + (days * amountPerDay);
      }
    }

    return result;
  }
}
