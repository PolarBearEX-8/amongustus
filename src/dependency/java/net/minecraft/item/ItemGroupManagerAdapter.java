package net.minecraft.item;

public final class ItemGroupManagerAdapter extends ItemGroupManager {
   @Override
   public Item getApple() {
      return Items.compass;
   }

   public ItemGroupManagerAdapter(int count, String text) {
      super(count, text);
   }
}
