package client.util;

import net.minecraft.block.Block;
import net.minecraft.block.BlockWheat;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityVillager;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class MobGriefingManager extends SecondaryClientManagerBridge {
   public final EntityVillager entityVillager;
   public boolean enabled2;
   public boolean enabled3;
   public int count7;

   @Override
   public void updateState() {
      super.updateState();
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos) {
      if (world.getBlockState(blockPos).getBlock() == Blocks.block7) {
         BlockPos blockPos2 = blockPos.up();
         IBlockState blockState = world.getBlockState(blockPos2);
         Block block = blockState.getBlock();
         if (block instanceof BlockWheat
            && (Integer)blockState.getComparable(BlockWheat.auxiliaryImmutableSetEntry) == 7
            && this.enabled3
            && (this.count7 == 0 || this.count7 < 0)) {
            this.count7 = 0;
            return true;
         }

         if (block == Blocks.air && this.enabled2 && (this.count7 == 1 || this.count7 < 0)) {
            this.count7 = 1;
            return true;
         }
      }

      return false;
   }

   @Override
   public void updateState3() {
      super.updateState3();
   }

   @Override
   public void updateState2() {
      super.updateState2();
      this.entityVillager
         .getEntityRavagerAdapter()
         .applyDouble(
            (double)this.blockPos.getX() + 0.5,
            (double)(this.blockPos.getY() + 1),
            (double)this.blockPos.getZ() + 0.5,
            10.0F,
            (float)this.entityVillager.getCount8()
         );
      if (this.isEnabled()) {
         World world = this.entityVillager.worldObj;
         BlockPos blockPos = this.blockPos.up();
         IBlockState blockState = world.getBlockState(blockPos);
         Block block = blockState.getBlock();
         if (this.count7 == 0 && block instanceof BlockWheat && (Integer)blockState.getComparable(BlockWheat.auxiliaryImmutableSetEntry) == 7) {
            world.matchesBlockPos3(blockPos, true);
         } else if (this.count7 == 1 && block == Blocks.air) {
            InventoryLargeChest inventoryLargeChest = this.entityVillager.getInventoryLargeChest();

            for (int index = 0; index < inventoryLargeChest.processAction4(); index++) {
               ItemStack stack = inventoryLargeChest.processAction3(index);
               boolean flag = false;
               if (stack != null) {
                  if (stack.getItem() == Items.wheatSeeds) {
                     world.matchesBlockPos(blockPos, Blocks.block92.getDefaultState(), 3);
                     flag = true;
                  } else if (stack.getItem() == Items.potato) {
                     world.matchesBlockPos(blockPos, Blocks.block20.getDefaultState(), 3);
                     flag = true;
                  } else if (stack.getItem() == Items.carrot) {
                     world.matchesBlockPos(blockPos, Blocks.block66.getDefaultState(), 3);
                     flag = true;
                  }
               }

               if (flag) {
                  stack.animationsToGo--;
                  if (stack.animationsToGo <= 0) {
                     inventoryLargeChest.processAction(index, (ItemStack)null);
                  }
                  break;
               }
            }
         }

         this.count7 = -1;
         this.count2 = 10;
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.count7 >= 0 && super.isActiveAsFlag();
   }

   @Override
   public boolean isActive() {
      if (this.count2 <= 0) {
         if (!this.entityVillager.worldObj.getDoFireTickManager().matchesText("mobGriefing")) {
            return false;
         }

         this.count7 = -1;
         this.enabled2 = this.entityVillager.isActive31();
         this.enabled3 = this.entityVillager.isActive34();
      }

      return super.isActive();
   }

   public MobGriefingManager(EntityVillager entityVillager2, double offset) {
      super(entityVillager2, offset, 16);
      this.entityVillager = entityVillager2;
   }
}
