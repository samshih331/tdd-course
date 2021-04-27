import java.util.HashMap;

public class Tennis {

  private String leftSidePlayer;
  private String rightSidePlayer;
  private int leftSidePlayerScore = 0;
  private int rightSidePlayerScore = 0;
  private final HashMap<Integer, String> scoreLookup = new HashMap<>() {
    {
      put(1, "fifteen");
      put(2, "thirty");
      put(3, "forty");
    }
  };

  public Tennis(String leftSidePlayer, String rightSidePlayer) {
    this.leftSidePlayer = leftSidePlayer;
    this.rightSidePlayer = rightSidePlayer;
  }

  public String score() {

    if (isSameScore()) {
      return "love all";
    }

    if (leftSidePlayerScore > 0) {
      return scoreLookup.get(leftSidePlayerScore) + " love";
    }

    return "";
  }

  private boolean isSameScore() {
    return leftSidePlayerScore == 0 && rightSidePlayerScore == 0;
  }

  public void rightSidePlayerScore() {
    this.rightSidePlayerScore += 1;
  }

  public void leftSidePlayerScore() {
    this.leftSidePlayerScore += 1;
  }
}
