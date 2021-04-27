package budget;

import java.util.ArrayList;
import java.util.List;

public class BudgetRepoImpl implements BudgetRepo {
  @Override
  public List<Budget> getAll() {
    ArrayList<Budget> budgets = new ArrayList<>();
    Budget april2021 = new Budget();
    april2021.setAmount(310000);
    april2021.setYearMonth("202104");
    budgets.add(april2021);
    return budgets;
  }
}
