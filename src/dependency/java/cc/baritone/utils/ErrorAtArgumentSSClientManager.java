package cc.baritone.utils;

public abstract class ErrorAtArgumentSSClientManager extends ExtendedClientManager {
   public final BreadthFirstScheduler breadthFirstScheduler;

   private static String resolveText(BreadthFirstScheduler breadthFirstScheduler2, String text) {
      return String.format("Error at argument #%s: %s", breadthFirstScheduler2.getCount() == -1 ? "<unknown>" : Integer.toString(breadthFirstScheduler2.getCount() + 1), text);
   }

   public ErrorAtArgumentSSClientManager(BreadthFirstScheduler breadthFirstScheduler2, String text, Throwable throwable) {
      super(resolveText(breadthFirstScheduler2, text), throwable);
      this.breadthFirstScheduler = breadthFirstScheduler2;
   }

   public ErrorAtArgumentSSClientManager(BreadthFirstScheduler breadthFirstScheduler2, String text) {
      super(resolveText(breadthFirstScheduler2, text));
      this.breadthFirstScheduler = breadthFirstScheduler2;
   }
}
