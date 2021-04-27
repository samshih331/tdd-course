package budget;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
算預算
某一個月預算多少
查詢一段區間能用的預算有多少 => 查詢起訖到日
每一筆預算，有哪一個月份，多少錢
回傳Double
LocalDate
表裡面是西元年 六位數字字串 => 201806
錢的單位是元 有可能有小數 小數點兩位 四捨五入
要計算預算的比例 依照天數比例計算
有可能沒資料
 */
public class BudgetTests {

  private Finance finance;
  private final BudgetRepoImpl budgetRepo = new BudgetRepoImpl();

  @Test
  public void queryWholeMonth() {
    List<Budget> budgets = new ArrayList<>();
    Budget april2021 = new Budget();
    april2021.setAmount(310000);
    april2021.setYearMonth("202104");
    budgets.add(april2021);

    Budget may2021 = new Budget();
    may2021.setAmount(30000);
    may2021.setYearMonth("202105");
    budgets.add(may2021);

    Budget oct2021 = new Budget();
    oct2021.setAmount(1000);
    oct2021.setYearMonth("202110");
    budgets.add(oct2021);
    budgetRepo.setBudgets(budgets);

    finance = new Finance(budgetRepo);

    double queryBudget = finance.queryBudget(LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 30));
    Assertions.assertEquals(queryBudget, 310000d);
  }

  @Test
  public void queryWhole2Month() {
    List<Budget> budgets = new ArrayList<>();
    Budget april2021 = new Budget();
    april2021.setAmount(310000);
    april2021.setYearMonth("202104");
    budgets.add(april2021);

    Budget may2021 = new Budget();
    may2021.setAmount(30000);
    may2021.setYearMonth("202105");
    budgets.add(may2021);

    Budget oct2021 = new Budget();
    oct2021.setAmount(1000);
    oct2021.setYearMonth("202110");
    budgets.add(oct2021);
    budgetRepo.setBudgets(budgets);

    finance = new Finance(budgetRepo);
    double queryBudget = this.finance.queryBudget(LocalDate.of(2021, 4, 1), LocalDate.of(2021, 5, 31));
    Assertions.assertEquals(queryBudget, 340000d);
  }

  @Test
  public void queryPartialMonth() {
    double queryBudget = finance.queryBudget(LocalDate.of(2021, 10, 1), LocalDate.of(2021, 10, 10));
    Assertions.assertEquals(queryBudget, 1000d);
  }

  @Test
  public void queryNoData() {
    List<Budget> budgets = new ArrayList<>();
    budgetRepo.setBudgets(budgets);
    finance = new Finance(budgetRepo);
    double queryBudget = this.finance.queryBudget(LocalDate.of(2021, 4, 1), LocalDate.of(2021, 5, 31));
    Assertions.assertEquals(queryBudget, 0);
  }

  @Test
  public void queryInvalidPerid() {
    List<Budget> budgets = new ArrayList<>();
    budgetRepo.setBudgets(budgets);
    finance = new Finance(budgetRepo);
    double queryBudget = this.finance.queryBudget(LocalDate.of(2021, 5, 1), LocalDate.of(2021, 4, 30));
    Assertions.assertEquals(queryBudget, 0);
  }

  class BudgetRepoImpl implements BudgetRepo {
    private List<Budget> budgets;

    @Override
    public List<Budget> getAll() {
      return budgets;
    }

    public void setBudgets(List<Budget> budgets) {
      this.budgets = budgets;
    }
  }
}
