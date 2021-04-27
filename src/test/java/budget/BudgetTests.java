package budget;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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

  private final Finance finance = new Finance();

  @Test
  public void queryWholeMonth() {
    double queryBudget = finance.queryBudget(LocalDate.of(2021, 4, 1), LocalDate.of(2021, 4, 30));
    Assertions.assertEquals(queryBudget, 310000d);
  }
  @Test
  public void queryWhole2Month() {
    double queryBudget = finance.queryBudget(LocalDate.of(2021, 4, 1), LocalDate.of(2021, 5, 31));
    Assertions.assertEquals(queryBudget, 313000d);
  }
}
