package client.util;

public class AimStack extends InternalExceptionAdapter {
   public AimStack() {
      this("commands.generic.entity.notFound", new Object[0]);
   }

   public AimStack(String text, Object[] entryArray) {
      super(text, entryArray);
   }
}
