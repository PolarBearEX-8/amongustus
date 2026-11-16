package net.minecraft.inventory;

import client.util.TertiaryContainerAdapter;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.item.ItemStack;

public class EntityHorseSlot extends Slot {
   public final EntityHorse entityHorse;
   public final TertiaryContainerAdapter tertiaryContainerAdapter;

   public EntityHorseSlot(TertiaryContainerAdapter tertiaryContainerAdapter2, IInventory iInventory, int count, int count2, int count3, EntityHorse entityHorse2) {
      super(iInventory, count, count2, count3);
      this.tertiaryContainerAdapter = tertiaryContainerAdapter2;
      this.entityHorse = entityHorse2;
   }

   @Override
   public boolean getHasStack() {
      return this.entityHorse.isActive44();
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return super.isItemValid(stack) && this.entityHorse.isActive44() && EntityHorse.matchesItem(stack.getItem());
   }
}
