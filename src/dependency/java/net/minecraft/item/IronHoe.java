package net.minecraft.item;

import client.util.IBlockState;
import client.util.SecondaryClientManagerBridge;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCarrots;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityRabbit;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class IronHoe extends SecondaryClientManagerBridge {
   public final EntityRabbit entityRabbit;
   public boolean enabled2;
   public boolean enabled3 = false;

   @Override
   public void updateState3() {
      super.updateState3();
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos) {
      if (world.getBlockState(blockPos).getBlock() == Blocks.block7) {
         BlockPos blockPos2 = blockPos.up();
         IBlockState blockState = world.getBlockState(blockPos2);
         if (blockState.getBlock() instanceof BlockCarrots
            && (Integer)blockState.getComparable(BlockCarrots.auxiliaryImmutableSetEntry) == 7
            && this.enabled2
            && !this.enabled3) {
            this.enabled3 = true;
            return true;
         }
      }

      return false;
   }

   @Override
   public void updateState() {
      super.updateState();
   }

   @Override
   public boolean isActive() {
      if (this.count2 <= 0) {
         if (!this.entityRabbit.worldObj.getDoFireTickManager().matchesText("mobGriefing")) {
            return false;
         }

         this.enabled3 = false;
         this.enabled2 = EntityRabbit.matchesEntityRabbit(this.entityRabbit);
      }

      return super.isActive();
   }

   @Override
   public void updateState2() {
      super.updateState2();
      this.entityRabbit
         .getEntityRavagerAdapter()
         .applyDouble(
            (double)this.blockPos.getX() + 0.5,
            (double)(this.blockPos.getY() + 1),
            (double)this.blockPos.getZ() + 0.5,
            10.0F,
            (float)this.entityRabbit.getCount8()
         );
      if (this.isEnabled()) {
         World world = this.entityRabbit.worldObj;
         BlockPos blockPos = this.blockPos.up();
         IBlockState blockState = world.getBlockState(blockPos);
         Block block = blockState.getBlock();
         if (this.enabled3 && block instanceof BlockCarrots && (Integer)blockState.getComparable(BlockCarrots.auxiliaryImmutableSetEntry) == 7) {
            world.matchesBlockPos(blockPos, Blocks.air.getDefaultState(), 2);
            world.matchesBlockPos3(blockPos, true);
            this.entityRabbit.updateState50();
         }

         this.enabled3 = false;
         this.count2 = 10;
      }
   }

   @Override
   public boolean isActiveAsFlag() {
      return this.enabled3 && super.isActiveAsFlag();
   }

   public IronHoe(EntityRabbit entityRabbit2) {
      super(entityRabbit2, 0.7F, 16);
      this.entityRabbit = entityRabbit2;
   }
}
