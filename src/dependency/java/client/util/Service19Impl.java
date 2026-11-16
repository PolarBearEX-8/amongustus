package client.util;

public class Service19Impl implements Service19 {
   public final String labelText;

   public Service19Impl(String text) {
      this.labelText = text;
   }

   @Override
   public int getCount() {
      return 0;
   }

   @Override
   public void applyIndex(int count, int count2, int count3, int count4) {
   }

   @Override
   public String getLabelText() {
      return this.labelText;
   }
}
