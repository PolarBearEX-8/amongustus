package net.lenni0451.commons.httpclient;

public class RetryHandler {
   private int maxConnectRetries = 0;
   private int maxHeaderRetries = 0;

   public RetryHandler() {
   }

   public RetryHandler(int maxConnectRetries, int maxHeaderRetries) {
      this.maxConnectRetries = maxConnectRetries;
      this.maxHeaderRetries = maxHeaderRetries;
   }

   public int getMaxConnectRetries() {
      return this.maxConnectRetries;
   }

   public RetryHandler setMaxConnectRetries(int maxConnectRetries) {
      if (maxConnectRetries < 0) {
         throw new IllegalArgumentException("maxConnectRetries must be >= 0");
      } else {
         this.maxConnectRetries = maxConnectRetries;
         return this;
      }
   }

   public int getMaxHeaderRetries() {
      return this.maxHeaderRetries;
   }

   public RetryHandler setMaxHeaderRetries(int maxHeaderRetries) {
      if (maxHeaderRetries < 0) {
         throw new IllegalArgumentException("maxHeaderRetries must be >= 0");
      } else {
         this.maxHeaderRetries = maxHeaderRetries;
         return this;
      }
   }
}
