package client.util;

public class AimProvider extends InternalExceptionAdapter {
   public AimProvider(String text, Object[] entryArray) {
      super(text, entryArray);
   }

   public AimProvider() {
      this("commands.generic.player.notFound", new Object[0]);
   }
}
