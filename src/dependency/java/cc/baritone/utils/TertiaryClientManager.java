package cc.baritone.utils;

public class TertiaryClientManager extends ErrorAtArgumentSSClientManager {
   public TertiaryClientManager(BreadthFirstScheduler breadthFirstScheduler, String text) {
      super(breadthFirstScheduler, String.format("Expected %s", text));
   }

   public TertiaryClientManager(BreadthFirstScheduler breadthFirstScheduler, String text, String text2) {
      super(breadthFirstScheduler, String.format("Expected %s, but got %s instead", text, text2));
   }

   public TertiaryClientManager(BreadthFirstScheduler breadthFirstScheduler, String text, Throwable throwable) {
      super(breadthFirstScheduler, String.format("Expected %s", text), throwable);
   }

   public TertiaryClientManager(BreadthFirstScheduler breadthFirstScheduler, String text, String text2, Throwable throwable) {
      super(breadthFirstScheduler, String.format("Expected %s, but got %s instead", text, text2), throwable);
   }
}
