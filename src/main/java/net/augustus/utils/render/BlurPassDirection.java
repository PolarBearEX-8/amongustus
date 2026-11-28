package net.augustus.utils.render;

public enum BlurPassDirection {
   HORIZONTAL(1, 0),

   VERTICAL(0, 1);

public final int x;
   public final int y;

   private BlurPassDirection(int n, int n2) {
      this.x = n;
      this.y = n2;
   }
}
