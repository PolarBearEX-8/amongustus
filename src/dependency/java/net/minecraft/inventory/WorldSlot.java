package net.minecraft.inventory;

import client.util.IBlockState;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldSlot extends Slot {
   public final World world;
   public final BlockPos blockPos;
   public final RepairContainer repairContainer;

   @Override
   public void processAction(EntityPlayer player, ItemStack stack) {
      if (!player.invulnerableManager.enabled2) {
         player.applyIndex8(-this.repairContainer.count);
      }

      RepairContainer.resolveIInventory(this.repairContainer).processAction(0, (ItemStack)null);
      if (RepairContainer.computeCount(this.repairContainer) > 0) {
         ItemStack stack2 = RepairContainer.resolveIInventory(this.repairContainer).processAction3(1);
         if (stack2 != null && stack2.animationsToGo > RepairContainer.computeCount(this.repairContainer)) {
            stack2.animationsToGo = stack2.animationsToGo - RepairContainer.computeCount(this.repairContainer);
            RepairContainer.resolveIInventory(this.repairContainer).processAction(1, stack2);
         } else {
            RepairContainer.resolveIInventory(this.repairContainer).processAction(1, (ItemStack)null);
         }
      } else {
         RepairContainer.resolveIInventory(this.repairContainer).processAction(1, (ItemStack)null);
      }

      this.repairContainer.count = 0;
      IBlockState blockState = this.world.getBlockState(this.blockPos);
      if (!player.invulnerableManager.enabled2 && !this.world.state && blockState.getBlock() == Blocks.block51 && player.getRandom().nextFloat() < 0.12F) {
         int comparable = (Integer)blockState.getComparable(BlockAnvil.auxiliaryImmutableSetEntry);
         if (++comparable > 2) {
            this.world.matchesBlockPos8(this.blockPos);
            this.world.applyBlockPosByBlockPos(1020, this.blockPos, 0);
         } else {
            this.world.matchesBlockPos(this.blockPos, blockState.resolveIBlockState(BlockAnvil.auxiliaryImmutableSetEntry, comparable), 2);
            this.world.applyBlockPosByBlockPos(1021, this.blockPos, 0);
         }
      } else if (!this.world.state) {
         this.world.applyBlockPosByBlockPos(1021, this.blockPos, 0);
      }
   }

   @Override
   public boolean processAction(EntityPlayer player) {
      return (player.invulnerableManager.enabled2 || player.count25 >= this.repairContainer.count) && this.repairContainer.count > 0 && this.isActive();
   }

   public WorldSlot(RepairContainer repairContainer2, IInventory iInventory, int count, int count2, int count3, World world2, BlockPos blockPos2) {
      super(iInventory, count, count2, count3);
      this.repairContainer = repairContainer2;
      this.world = world2;
      this.blockPos = blockPos2;
   }

   @Override
   public boolean isItemValid(ItemStack stack) {
      return false;
   }
}
