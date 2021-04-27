import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TennisTests {

  Tennis tennis = new Tennis("Joey", "Gary");

  @Test
  public void love_all() {
    scoreShouldBe("love all");
  }

  private void scoreShouldBe(String score) {
    assertEquals(tennis.score(), score);
  }

  @Test
  public void fifteen_love() {
    givenLeftSidePlayerScoreTimes(1);
    scoreShouldBe("fifteen love");
  }

  @Test
  public void thirty_love() {
    givenLeftSidePlayerScoreTimes(2);
    scoreShouldBe("thirty love");
  }

  private void givenLeftSidePlayerScoreTimes(int times) {
    for (int i = 0; i < times; i++) {
      tennis.leftSidePlayerScore();
    }
  }

  @Test
  public void forty_love() {
    givenLeftSidePlayerScoreTimes(3);
    scoreShouldBe("forty love");
  }

}
