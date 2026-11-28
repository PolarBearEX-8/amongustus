package net.augustus.cosmetics.model;


public class GeckoLibException extends RuntimeException {

   public GeckoLibException(String str, long l, String str2, Throwable t) {
       super(str + ": " + str2, t);
   }

   public GeckoLibException(String str, String str2, long l) {
       super(str + ": " + str2);
   }



}
