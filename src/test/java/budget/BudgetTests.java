package budget;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

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
@ExtendWith(MockitoExtension.class)
public class BudgetTests {

  @InjectMocks
  private Finance finance;
  @Mock
  BudgetRepo budgetRepo;

  @Test
  public void queryWholeMonth() {

    int[] amounts = { 310000, 30000, 1000 };
    String[] yearMonths = { "202104", "202105", "202110" };
    List<Budget> budgets = new ArrayList<>();

    for (int i = 0; i < amounts.length; i++) {
      budgets.add(new Budget(yearMonths[i], amounts[i]));
    }

    when(budgetRepo.getAll()).thenReturn(budgets);

    finance = new Finance(budgetRepo);

    double queryBudget = finance.queryBudget(LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 30));
    Assertions.assertEquals(queryBudget, 310000d);
  }

  @Test
  public void queryWhole2Month() {
    int[] amounts = { 310000, 30000, 1000 };
    String[] yearMonths = { "202104", "202105", "202110" };
    List<Budget> budgets = new ArrayList<>();

    for (int i = 0; i < amounts.length; i++) {
      budgets.add(new Budget(yearMonths[i], amounts[i]));
    }

    when(budgetRepo.getAll()).thenReturn(budgets);

    double queryBudget = this.finance.queryBudget(LocalDate.of(2021, 4, 1), LocalDate.of(2021, 5, 31));
    Assertions.assertEquals(queryBudget, 340000d);
  }

  @Test
  public void queryPartialMonth() {
    int[] amounts = { 3100 };
    String[] yearMonths = { "202110" };
    List<Budget> budgets = new ArrayList<>();

    for (int i = 0; i < amounts.length; i++) {
      budgets.add(new Budget(yearMonths[i], amounts[i]));
    }

    when(budgetRepo.getAll()).thenReturn(budgets);

    double queryBudget = finance.queryBudget(LocalDate.of(2021, 10, 1), LocalDate.of(2021, 10, 10));
    Assertions.assertEquals(queryBudget, 1000d);
  }

  @Test
  public void queryNoData() {
    List<Budget> budgets = new ArrayList<>();
    when(budgetRepo.getAll()).thenReturn(budgets);
    double queryBudget = this.finance.queryBudget(LocalDate.of(2021, 4, 1), LocalDate.of(2021, 5, 31));
    Assertions.assertEquals(queryBudget, 0);
  }

  @Test
  public void queryPartial2Month() {
    int[] amounts = { 310000, 30000 };
    String[] yearMonths = { "202110", "202111" };
    List<Budget> budgets = new ArrayList<>();

    for (int i = 0; i < amounts.length; i++) {
      budgets.add(new Budget(yearMonths[i], amounts[i]));
    }

    when(budgetRepo.getAll()).thenReturn(budgets);

    double queryBudget = finance.queryBudget(LocalDate.of(2021, 10, 30), LocalDate.of(2021, 11, 10));
    Assertions.assertEquals(queryBudget, 30000);
  }

  @Test
  public void queryInvalidPeriod() {
    List<Budget> budgets = new ArrayList<>();
    when(budgetRepo.getAll()).thenReturn(budgets);
    double queryBudget = this.finance.queryBudget(LocalDate.of(2021, 5, 1), LocalDate.of(2021, 4, 30));
    Assertions.assertEquals(queryBudget, 0);
  }

  @Test
  public void queryPartial3Month() {
    int[] amounts = { 300000, 31000, 30 };
    String[] yearMonths = { "202104", "202105", "202106" };
    List<Budget> budgets = new ArrayList<>();

    for (int i = 0; i < amounts.length; i++) {
      budgets.add(new Budget(yearMonths[i], amounts[i]));
    }

    when(budgetRepo.getAll()).thenReturn(budgets);
    double queryBudget = finance.queryBudget(LocalDate.of(2021, 4, 30), LocalDate.of(2021, 6, 3));
    Assertions.assertEquals(queryBudget, 41003);
  }

}
