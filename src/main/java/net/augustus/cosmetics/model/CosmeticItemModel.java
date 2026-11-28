package net.augustus.cosmetics.model;

public class CosmeticItemModel extends AbstractCosmeticPart {
   private int itemId;

   public int getItemId() {
      return this.itemId;
   }

   public CosmeticItemModel(int n) {
      super(CosmeticBodyPart.FREE);
      this.itemId = n;
   }

   public void setItemId(int n) {
      this.itemId = n;
   }
}
