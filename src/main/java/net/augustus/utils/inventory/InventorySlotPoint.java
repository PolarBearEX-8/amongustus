package net.augustus.utils.inventory;

public class InventorySlotPoint {
   public double x;
   public double y;

   public double distanceTo(InventorySlotPoint inventorySlotPoint) {
      double d = inventorySlotPoint.x - this.x;
      double d2 = inventorySlotPoint.y - this.y;
      return Math.sqrt(d * d + d2 * d2);
   }

   public InventorySlotPoint(double d, double d2) {
      this.x = d;
      this.y = d2;
   }
}
