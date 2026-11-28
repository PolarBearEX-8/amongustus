package net.augustus.cosmetics;

public enum CosmeticCategory {
   SHIELD(4, "Shield"),
   LOCAL(0, "Local"),
   WINGS(5, "Wings"),
   HAT(1, "Hat"),
   BODY(3, "Body"),
   PETS(6, "Pets"),
   HEAD(2, "Head");

   private int id;
   private String displayName;
   public static final CosmeticCategory[] VALUES = values();

   public String getDisplayName() {
      return this.displayName;
   }

   private CosmeticCategory(int n, String str) {
      this.id = n;
      this.displayName = str;
   }

   public static CosmeticCategory byId(int n) {
      CosmeticCategory[] cosmeticCategorys = VALUES;
      int length = cosmeticCategorys.length;

      for (int i = 0; i < length; i++) {
         CosmeticCategory cosmeticCategory = cosmeticCategorys[i];
         if (cosmeticCategory.getId() == n) {
            return cosmeticCategory;
         }
      }

      return LOCAL;
   }

   public int getId() {
      return this.id;
   }

   public String getLowerName() {
      return this.displayName.toLowerCase();
   }
}
