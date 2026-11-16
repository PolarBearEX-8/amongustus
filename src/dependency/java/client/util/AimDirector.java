package client.util;

public class AimDirector extends InternalExceptionAdapter {
   public AimDirector(String text, Object[] entryArray) {
      super(text, entryArray);
   }

   public AimDirector() {
      this("commands.generic.num.invalid", new Object[0]);
   }
}
