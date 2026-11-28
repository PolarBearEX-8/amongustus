package net.augustus.cosmetics.model;

public enum CosmeticEffectType {
   BOUNCE(1, CosmeticAxis.Y, "Bounce Effect", ""),
   ROTATE(2, CosmeticAxis.Y, "Rotation", ""),
   FORWARD(3, CosmeticAxis.X, "Movement Forward", ""),
   BACKWARD(4, CosmeticAxis.Z, "Movement Backward", ""),
   RESIZE(5, (CosmeticAxis)null, "Resize Effect", ""),
   MOTION(6, CosmeticAxis.Z, "Random Motion", "");

   public static final CosmeticEffectType[] VALUES;
   private final CosmeticAxis defaultAxis;
   private final int id;
   private final String displayName;
   private final String description;

   public int getId() {
      return this.id;
   }

   private CosmeticEffectType(int n, CosmeticAxis cosmeticAxis, String str, String str2) {
      this.id = n;
      this.defaultAxis = cosmeticAxis;
      this.displayName = str;
      this.description = str2;
   }

   public String getDescription() {
      return this.description;
   }

   public static CosmeticEffectType byId(int n) {
      CosmeticEffectType[] values = values();
      int length = values.length;

      for (int i = 0; i < length; i++) {
         CosmeticEffectType cosmeticEffectType = values[i];
         if (cosmeticEffectType.getId() == n) {
            return cosmeticEffectType;
         }
      }

      return null;
   }

   static {

      VALUES = values();
   }

   public CosmeticAxis getDefaultAxis() {
      return this.defaultAxis;
   }

   public String getDisplayName() {
      return this.displayName;
   }


}
