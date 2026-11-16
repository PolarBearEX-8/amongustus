package client.util;

import client.setting.CombatStateProperty;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityOzelot;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class HitBoxDispatcher extends SecondaryClientManagerBridge {
   public final EntityOzelot entityOzelot;

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos) {
      if (!world.isAirBlock(blockPos.up())) {
         return false;
      } else {
         IBlockState blockState = world.getBlockState(blockPos);
         Block block = blockState.getBlock();
         if (block == Blocks.blockTrappedChest) {
            TileEntity tileEntity = world.resolveTileEntity(blockPos);
            if (tileEntity instanceof TileEntityChest && ((TileEntityChest)tileEntity).count3 < 1) {
               return true;
            }
         } else {
            if (block == Blocks.block88) {
               return true;
            }

            if (block == Blocks.bed && blockState.getComparable(BlockBed.immutableSetEntry) != CombatStateProperty.HEAD) {
               return true;
            }
         }

         return false;
      }
   }

   @Override
   public void updateState3() {
      super.updateState3();
      this.entityOzelot.getHitBoxCoordinator().setEnabled(false);
   }

   @Override
   public void updateState() {
      super.updateState();
      this.entityOzelot.setEnabled8(false);
   }

   @Override
   public boolean isActive() {
      return this.entityOzelot.isActive32() && !this.entityOzelot.isActive33() && super.isActive();
   }

   @Override
   public boolean isActiveAsFlag() {
      return super.isActiveAsFlag();
   }

   public HitBoxDispatcher(EntityOzelot entityOzelot2, double offset) {
      super(entityOzelot2, offset, 8);
      this.entityOzelot = entityOzelot2;
   }

   @Override
   public void updateState2() {
      super.updateState2();
      this.entityOzelot.getHitBoxCoordinator().setEnabled(false);
      if (!this.isEnabled()) {
         this.entityOzelot.setEnabled8(false);
      } else if (!this.entityOzelot.isActive33()) {
         this.entityOzelot.setEnabled8(true);
      }
   }
}
