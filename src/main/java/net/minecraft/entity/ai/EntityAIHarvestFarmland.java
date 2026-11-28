package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityAIHarvestFarmland extends EntityAIMoveToBlock {
   private final EntityVillager theVillager;
   private boolean hasFarmItem;
   private boolean wantsToReapStuff;
   private int currentTask;

   @Override
   public void startExecuting() {
      super.startExecuting();
   }

   @Override
   public void resetTask() {
      super.resetTask();
   }

   @Override
   public void updateTask() {
      super.updateTask();
      this.theVillager
         .getLookHelper()
         .setLookPosition(
            (double)this.destinationBlock.getX() + 0.5,
            (double)(this.destinationBlock.getY() + 1),
            (double)this.destinationBlock.getZ() + 0.5,
            10.0F,
            (float)this.theVillager.getVerticalFaceSpeed()
         );
      if (this.getIsAboveDestination()) {
         World entityWorld = this.theVillager.entityWorld;
         BlockPos pos = this.destinationBlock.up();
         IBlockState blockState = entityWorld.getBlockState(pos);
         Block block = blockState.getBlock();
         if (this.currentTask == 0 && block instanceof BlockCrops && (Integer)blockState.getValue(BlockCrops.AGE) == 7) {
            entityWorld.destroyBlock(pos, true);
         } else if (this.currentTask == 1 && block == Blocks.air) {
            InventoryBasic villagerInventory = this.theVillager.getVillagerInventory();

            for (int i = 0; i < villagerInventory.getSizeInventory(); i++) {
               ItemStack stackInSlot = villagerInventory.getStackInSlot(i);
               boolean flag = false;
               if (stackInSlot != null) {
                  if (stackInSlot.getItem() == Items.wheat_seeds) {
                     entityWorld.setBlockState(pos, Blocks.wheat.getDefaultState(), 3);
                     flag = true;
                  } else if (stackInSlot.getItem() == Items.potato) {
                     entityWorld.setBlockState(pos, Blocks.potatoes.getDefaultState(), 3);
                     flag = true;
                  } else if (stackInSlot.getItem() == Items.carrot) {
                     entityWorld.setBlockState(pos, Blocks.carrots.getDefaultState(), 3);
                     flag = true;
                  }
               }

               if (flag) {
                  stackInSlot.stackSize--;
                  if (stackInSlot.stackSize <= 0) {
                     villagerInventory.setInventorySlotContents(i, (ItemStack)null);
                  }
                  break;
               }
            }
         }

         this.currentTask = -1;
         this.runDelay = 10;
      }
   }

   @Override
   public boolean continueExecuting() {
      return this.currentTask >= 0 && super.continueExecuting();
   }

   public EntityAIHarvestFarmland(EntityVillager entityVillager, double d) {
      super(entityVillager, d, 16);
      this.theVillager = entityVillager;
   }

   @Override
   public boolean shouldExecute() {
      if (this.runDelay <= 0) {
         if (!this.theVillager.entityWorld.getGameRules().getBoolean("mobGriefing")) {
            return false;
         }

         this.currentTask = -1;
         this.hasFarmItem = this.theVillager.isFarmItemInInventory();
         this.wantsToReapStuff = this.theVillager.wantsMoreFood();
      }

      return super.shouldExecute();
   }

   @Override
   public boolean shouldMoveTo(World world, BlockPos pos) {
      Block block = world.getBlockState(pos).getBlock();
      if (block == Blocks.farmland) {
         pos = pos.up();
         IBlockState blockState = world.getBlockState(pos);
         block = blockState.getBlock();
         if (block instanceof BlockCrops && (Integer)blockState.getValue(BlockCrops.AGE) == 7 && this.wantsToReapStuff && (this.currentTask == 0 || this.currentTask < 0)) {
            this.currentTask = 0;
            return true;
         }

         if (block == Blocks.air && this.hasFarmItem && (this.currentTask == 1 || this.currentTask < 0)) {
            this.currentTask = 1;
            return true;
         }
      }

      return false;
   }
}
