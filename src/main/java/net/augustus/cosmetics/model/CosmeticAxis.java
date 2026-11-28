package net.augustus.cosmetics.model;

public enum CosmeticAxis {
   Z(3, 0, 0, 1),
   Y(2, 0, 1, 0),

   X(1, 1, 0, 0);

public final int x;
   public final int z;
   private final int id;
   public final int y;

   private CosmeticAxis(int n, int n2, int n3, int n4) {
      this.id = n;
      this.x = n2;
      this.y = n3;
      this.z = n4;
   }

   public static CosmeticAxis byId(int n) {

      for (CosmeticAxis cosmeticAxis : values()) {
         if (cosmeticAxis.getId() == n) {
            return cosmeticAxis;
         }
      }

      return null;
   }

   public int getId() {
      return this.id;
   }
}
