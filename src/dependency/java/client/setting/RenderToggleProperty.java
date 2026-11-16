package client.setting;

public enum RenderToggleProperty {
    GROWING(4259712),
    SHRINKING(16724016),
    STATIONARY(2138367);

   public final int count;

   public int getCount() {
      return this.count;
   }

   private RenderToggleProperty(int count2) {
      this.count = count2;
   }


}
