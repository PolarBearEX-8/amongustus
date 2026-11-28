package net.augustus.modules.fun.helper;

public class GridCoord2I {
   public int x;
   public int z;

   public GridCoord2I(int n, int n2) {
      this.x = n;
      this.z = n2;
   }

   @Override
   public String toString() {
      return "(" + this.x + ", " + this.z + ")";
   }
}
