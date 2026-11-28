package net.augustus.cosmetics.model;

public enum GeoAxisDirection {
   POSITIVE(1, "Towards positive"),
   NEGATIVE(-1, "Towards negative");

   private final int offset;
   private final String description;
public int getOffset() {
      return this.offset;
   }

   @Override
   public String toString() {
      return this.description;
   }

   private GeoAxisDirection(int n, String str) {
      this.offset = n;
      this.description = str;
   }
}
