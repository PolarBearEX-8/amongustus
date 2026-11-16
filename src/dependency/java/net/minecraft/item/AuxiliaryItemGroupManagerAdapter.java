package net.minecraft.item;

public final class AuxiliaryItemGroupManagerAdapter extends ItemGroupManager {
   @Override
   public Item getApple() {
      return Items.ironAxe;
   }

   public AuxiliaryItemGroupManagerAdapter(int count, String text) {
      super(count, text);
   }
}
