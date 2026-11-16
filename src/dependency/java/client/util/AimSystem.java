package client.util;

public class AimSystem extends InternalExceptionAdapter {
   public AimSystem(String text, Object[] entryArray) {
      super(text, entryArray);
   }

   public AimSystem() {
      this("commands.generic.notFound", new Object[0]);
   }
}
