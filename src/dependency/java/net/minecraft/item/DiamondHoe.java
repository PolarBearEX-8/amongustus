package net.minecraft.item;

import client.util.DecayableManager;

public class DiamondHoe extends ItemBlock {
   public final DecayableManager decayableManager;

   @Override
   public int processAction2(int count) {
      return count | 4;
   }

   @Override
   public String getText(ItemStack stack) {
      return super.getString() + "." + this.decayableManager.resolveMovementProperty(stack.getIndexOffset()).getLabelText();
   }

   @Override
   public int processAction(ItemStack stack, int count) {
      return this.decayableManager.processAction2(this.decayableManager.processAction(stack.getIndexOffset()));
   }

   public DiamondHoe(DecayableManager decayableManager2) {
      super(decayableManager2);
      this.decayableManager = decayableManager2;
      this.processAction3(0);
      this.processAction(true);
   }
}
