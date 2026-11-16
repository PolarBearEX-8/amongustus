package cc.baritone.utils;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class CompletableFutureBreadthFirstStrategy extends BreadthFirstStrategy {
   public CompletableFuture resolveCompletableFutureByText(String text) {
      return this.resolveCompletableFuture((Function)(strategy -> CompletableFutureBreadthFirstStrategy.resolveBooleanByText(text, (BreadthFirstStrategy)strategy)));
   }

   private static Boolean resolveBoolean(String text, BreadthFirstStrategy breadthFirstStrategy) {
      return breadthFirstStrategy.matchesTextByText(text);
   }

   private static Boolean resolveBooleanByText(String text, BreadthFirstStrategy breadthFirstStrategy) {
      return breadthFirstStrategy.matchesText(text);
   }

   public CompletableFuture getCompletableFutureAsCompletableFutur() {
      return this.resolveCompletableFuture((Function)(strategy -> ((BreadthFirstStrategy)strategy).getHeuristicOptimizer()));
   }

   public CompletableFuture getCompletableFuture() {
      return this.resolveCompletableFuture((Function)(strategy -> ((BreadthFirstStrategy)strategy).getList()));
   }

   public CompletableFuture getCompletableFuture3() {
      return this.resolveCompletableFuture((Function)(strategy -> ((BreadthFirstStrategy)strategy).getControllerContext()));
   }

   public CompletableFuture resolveCompletableFuture(String text) {
      return this.resolveCompletableFuture((Function)(strategy -> CompletableFutureBreadthFirstStrategy.resolveBoolean(text, (BreadthFirstStrategy)strategy)));
   }

   public CompletableFutureBreadthFirstStrategy(String text) {
      super(text);
   }

   private CompletableFuture resolveCompletableFuture(Function mapper) {
      CompletableFuture completableFuture = new CompletableFuture();

      try {
         completableFuture.complete(mapper.apply(this));
      } catch (RuntimeExceptionAdapter exception) {
         completableFuture.completeExceptionally(exception);
      }

      return completableFuture;
   }

   public CompletableFuture getCompletableFuture2() {
      return this.resolveCompletableFuture((Function)(strategy -> ((BreadthFirstStrategy)strategy).getListAsList()));
   }
}
