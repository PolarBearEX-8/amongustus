package cc.baritone.utils;

public class TooManyException extends ExtendedClientManager {
   public TooManyException(int count) {
      super(String.format("Too many arguments (expected at most %d)", count));
   }
}
