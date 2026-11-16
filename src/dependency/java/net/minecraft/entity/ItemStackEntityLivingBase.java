package net.minecraft.entity;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemStackEntityLivingBase extends EntityLivingBase {
   @Override
   public ItemStack resolveItemStack(int count) {
      return null;
   }

   @Override
   public ItemStack getItemStack() {
      return null;
   }

   @Override
   public ItemStack[] getItemStackArray2() {
      return new ItemStack[0];
   }

   public ItemStackEntityLivingBase(World world) {
      super(world);
   }

   @Override
   public ItemStack getItemStack3(int count) {
      return null;
   }

   @Override
   public void applyItemStack(int count, ItemStack stack) {
   }
}
