package cc.baritone.utils;

public final class AStarStrategy {
   public static CompletableFutureBreadthFirstStrategy resolveCompletableFutureBreadthFirs(String text) {
      return new CompletableFutureBreadthFirstStrategy(text);
   }

   public static BreadthFirstStrategy resolveBreadthFirstStrategy(String text) {
      return new BreadthFirstStrategy(text);
   }
}
