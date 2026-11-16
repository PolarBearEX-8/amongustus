package client.util;

public class AimEngine extends InternalExceptionAdapter {
   public AimEngine(String text, Object[] entryArray) {
      super(text, entryArray);
   }

   public AimEngine() {
      this("commands.generic.snytax", new Object[0]);
   }
}
