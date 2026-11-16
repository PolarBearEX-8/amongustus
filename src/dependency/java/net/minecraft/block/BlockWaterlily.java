package net.minecraft.block;

import client.util.IBlockState;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityBoat;
import net.minecraft.item.ItemGroupManager;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockWaterlily extends SecondaryBlockAdapter {
   @Override
   public int processAction2(IBlockState blockState) {
      return 7455580;
   }

   @Override
   public int processAction6() {
      return 7455580;
   }

   @Override
   public boolean matchesBlockByBlock(Block block) {
      return block == Blocks.blockLava2;
   }

   @Override
   public int processAction(IBlockAccess iBlockAccess, BlockPos blockPos, int count) {
      return 2129968;
   }

   @Override
   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
      if (ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)) {
         this.processAction(0.0625, 0.0, 0.0625, 0.9375, 0.09375, 0.9375);
      } else {
         float factor = 0.5F;
         float factor2 = 0.015625F;
         this.setBlockBounds(0.5F - factor, 0.0F, 0.5F - factor, 0.5F + factor, factor2, 0.5F + factor);
      }

      if (!(entity instanceof EntityBoat)) {
         super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      }
   }

   @Override
   public boolean matchesWorld(World world, BlockPos blockPos, IBlockState blockState) {
      if (blockPos.getY() >= 0 && blockPos.getY() < 256) {
         IBlockState blockState2 = world.getBlockState(blockPos.down());
         return blockState2.getBlock().getMaterial() == Material.coreItemEntry1 && (Integer)blockState2.getComparable(BlockLiquid.auxiliaryImmutableSetEntry) == 0;
      } else {
         return false;
      }
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return 0;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_9)
         ? new AxisAlignedBB(
            (double)count + 0.0625, (double)count2 + 0.0, (double)count3 + 0.0625, (double)count + 0.9375, (double)count2 + 0.09375, (double)count3 + 0.9375
         )
         : new AxisAlignedBB(
            (double)count + this.coordOffset,
            (double)count2 + this.coordOffset11,
            (double)count3 + this.coordOffset2,
            (double)count + this.coordOffset9,
            (double)count2 + this.coordOffset6,
            (double)count3 + this.coordOffset15
         );
   }

   public BlockWaterlily() {
      this.processAction(ItemGroupManager.itemGroupManager8);
   }
}
