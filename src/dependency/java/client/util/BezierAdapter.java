package client.util;

public enum BezierAdapter {
    WINGS(5, "Wings"),
    HEAD(2, "Head"),
    LOCAL(0, "Local"),
    SHIELD(4, "Shield"),
    HAT(1, "Hat"),
    BODY(3, "Body"),
    PETS(6, "Pets");

   public int count;
   public static final BezierAdapter[] VALUES = values();
   public String labelText;

   public static BezierAdapter resolveBezierAdapter(int count2) {
      for (BezierAdapter bezierAdapter : VALUES) {
         if (bezierAdapter.getCount() == count2) {
            return bezierAdapter;
         }
      }

      return LOCAL;
   }

   public String getLabelText() {
      return this.labelText;
   }

   public String toLowerCase() {
      return this.labelText.toLowerCase();
   }

   public int getCount() {
      return this.count;
   }

   private BezierAdapter(int count2, String text) {
      this.count = count2;
      this.labelText = text;
   }
}
