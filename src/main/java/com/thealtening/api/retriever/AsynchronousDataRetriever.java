package com.thealtening.api.retriever;

import com.thealtening.api.TheAlteningException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class AsynchronousDataRetriever extends BasicDataRetriever {

   public CompletableFuture getFavouriteAccountInfosAsync() {
      return this.completeTask(BasicDataRetriever::getFavouriteAccountInfos);
   }

   private static Boolean applyIsFavorite(String str, BasicDataRetriever basicDataRetriever) {
      return basicDataRetriever.isFavorite(str);
   }

   private CompletableFuture completeTask(Function<BasicDataRetriever, ?> function) {
      CompletableFuture completableFuture = new CompletableFuture();

      try {
         completableFuture.complete(function.apply(this));
      } catch (TheAlteningException e) {
         completableFuture.completeExceptionally(e);
      }

      return completableFuture;
   }

   public CompletableFuture getPrivatedAccountInfosAsync() {
      return this.completeTask(BasicDataRetriever::getPrivatedAccountInfos);
   }

   public CompletableFuture getAccountDataAsync() {
      return this.completeTask(BasicDataRetriever::getAccount);
   }

   public CompletableFuture isFavoriteAsync(String str) {
      return this.completeTask(basicDataRetriever -> applyIsFavorite(str, (BasicDataRetriever)basicDataRetriever));
   }

   public AsynchronousDataRetriever(String str) {
      super(str);
   }

   public CompletableFuture isPrivateAsync(String str) {
      return this.completeTask(basicDataRetriever -> {
         return basicDataRetriever.isPrivate(str);
      });
   }

   public CompletableFuture getLicenseDataAsync() {
      return this.completeTask(BasicDataRetriever::getLicense);
   }
}
