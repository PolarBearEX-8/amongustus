package net.minecraft.inventory;

public class InventoryLargeChestImpl extends InventoryLargeChest {
   public final RepairContainer repairContainer;

   @Override
   public void processAction5() {
      super.processAction5();
      this.repairContainer.processAction(this);
   }

   public InventoryLargeChestImpl(RepairContainer repairContainer2, String text, boolean flag, int count) {
      super(text, flag, count);
      this.repairContainer = repairContainer2;
   }
}
