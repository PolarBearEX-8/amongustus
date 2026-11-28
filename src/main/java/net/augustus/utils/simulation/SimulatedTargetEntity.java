package net.augustus.utils.simulation;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SimulatedTargetEntity extends EntityLivingBase {
   public SimulatedTargetEntity(World world) {
      super(world);
   }

   @Override
   public ItemStack[] getInventory() {
      return new ItemStack[0];
   }

   @Override
   public ItemStack getHeldItem() {
      return null;
   }

   @Override
   public ItemStack getEquipmentInSlot(int n) {
      return null;
   }

   @Override
   public void setCurrentItemOrArmor(int n, ItemStack stack) {
   }

   @Override
   public ItemStack getCurrentArmor(int n) {
      return null;
   }
}
