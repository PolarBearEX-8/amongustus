package net.optifine.expr;

public class ParseException extends Exception {
   public ParseException(String str) {
      super(str);
   }

   public ParseException(String str, Throwable t) {
      super(str, t);
   }
}
