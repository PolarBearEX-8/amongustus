package net.minecraft.block;

import client.setting.MovementOffsetProperty;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import client.util.SmoothParser;
import client.via.ViaLoadingBase;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockFarmland extends Block {
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("moisture", 0, 7);

   @Override
   public void updateTick(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      int comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
      if (!this.matchesWorld3(world, blockPos) && !world.matchesBlockPos(blockPos.up())) {
         if (comparable > 0) {
            world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry, comparable - 1), 2);
         } else if (!this.matchesWorld5(world, blockPos)) {
            world.matchesBlockPos(blockPos, Blocks.block115.getDefaultState());
         }
      } else if (comparable < 7) {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry, 7), 2);
      }
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Item.processAction2(Blocks.block115);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState().resolveIBlockState(auxiliaryImmutableSetEntry, count & 7);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, IBlockState blockState, Block block) {
      super.processAction(world, blockPos, blockState, block);
      if (world.getBlockState(blockPos.up()).getBlock().getMaterial().canExecute2()) {
         world.matchesBlockPos(blockPos, Blocks.block115.getDefaultState());
      }
   }

   private boolean matchesWorld3(World world, BlockPos blockPos) {
      for (AuxiliaryBlockPosAdapter auxiliaryBlockPosAdapter : (Iterable<AuxiliaryBlockPosAdapter>)(Iterable<?>)BlockPos.resolveIterableByBlockPos(blockPos.add(-4, 0, -4), blockPos.add(4, 1, 4))) {
         if (world.getBlockState(auxiliaryBlockPosAdapter).getBlock().getMaterial() == Material.coreItemEntry1) {
            return true;
         }
      }

      return false;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Blocks.block115
         .processAction(Blocks.block115.getDefaultState().resolveIBlockState(BlockDirt.immutableSetEntry, MovementOffsetProperty.DIRT), random, count);
   }

   @Override
   public void processAction(World world, BlockPos blockPos, Entity entity, float factor) {
      if (entity instanceof EntityLivingBase) {
         if (!world.state && world.random.nextFloat() < factor - 0.5F) {
            if (!(entity instanceof EntityPlayer) && !world.getDoFireTickManager().matchesText("mobGriefing")) {
               return;
            }

            world.matchesBlockPos(blockPos, Blocks.block115.getDefaultState());
         }

         super.processAction(world, blockPos, entity, factor);
      }
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      switch (SmoothParser.countArray[enumFacing.ordinal()]) {
         case 1:
            return true;
         case 2:
         case 3:
         case 4:
         case 5:
            Block block = iBlockAccess.getIBlockState(count, count2, count3).getBlock();
            return !block.processAction15() && block != Blocks.block7;
         default:
            return super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing);
      }
   }

   private boolean matchesWorld5(World world, BlockPos blockPos) {
      Block block = world.getBlockState(blockPos.up()).getBlock();
      return block instanceof BlockWheat || block instanceof BlockMelonStem;
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{auxiliaryImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return ViaLoadingBase.protocolVersion.newerThanOrEqualTo(ProtocolVersion.v1_10)
         ? new AxisAlignedBB((double)count, (double)count2, (double)count3, (double)count + 1.0, (double)count2 + 0.9375, (double)count3 + 1.0)
         : new AxisAlignedBB((double)count, (double)count2, (double)count3, (double)(count + 1), (double)(count2 + 1), (double)(count3 + 1));
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockFarmland() {
      super(Material.material32);
      this.setIBlockState(this.blockManager.get().resolveIBlockState(auxiliaryImmutableSetEntry, 0));
      this.processAction(true);
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.9375F, 1.0F);
      this.getBlockById(255);
   }
}
