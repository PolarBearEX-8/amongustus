package com.thealtening.api;

public final class TheAlteningException extends RuntimeException {
   public TheAlteningException(String str, Throwable t) {
      super(str, t);
   }

   public TheAlteningException(String str, String str2) {
      super(String.format("[%s]: %s", str, str2));
   }
}
