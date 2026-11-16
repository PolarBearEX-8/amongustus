package client.util;

public class Controller20Impl extends Controller20 {
   public final boolean enabled2;

   public boolean isEnabled2() {
      return this.enabled2;
   }

   public Controller20Impl(int count, String text, boolean flag, boolean flag2) {
      super(count, text, flag);
      this.enabled2 = flag2;
   }
}
