package client.setting;

public enum SimpleOptionType {
    VERTICAL(0, 1),
    HORIZONTAL(1, 0);

   public final int x;
   public final int y;

   private SimpleOptionType(int count, int count2) {
      this.x = count;
      this.y = count2;
   }


}
