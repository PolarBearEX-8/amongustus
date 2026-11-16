package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.AuxiliaryImmutableSetEntry;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import client.util.VectorRanker;
import java.util.Random;
import net.minecraft.block.properties.IProperty;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPoweredRepeater extends VectorRanker {
   public static final InternalImmutableSetEntry internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry("locked");
   public static final AuxiliaryImmutableSetEntry auxiliaryImmutableSetEntry = AuxiliaryImmutableSetEntry.resolveAuxiliaryImmutableSetEntry("delay", 1, 4);

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      return player.invulnerableManager.state1;
   }

   @Override
   public boolean matchesBlockByBlock(Block block) {
      return matchesBlock3(block);
   }

   @Override
   public IBlockState processAction(IBlockState blockState, IBlockAccess iBlockAccess, int count, int count2, int count3) {
      return blockState.resolveIBlockState(internalImmutableSetEntry, this.matchesIBlockAccess(iBlockAccess, count, count2, count3, blockState));
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return Items.repeater;
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, EnumFacing.resolveEnumFacing(count))
         .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
         .resolveIBlockState(auxiliaryImmutableSetEntry, 1 + (count >> 2));
   }

   @Override
   public IBlockState resolveIBlockState(IBlockState blockState) {
      Integer comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
      Boolean comparable2 = (Boolean)blockState.getComparable(internalImmutableSetEntry);
      EnumFacing comparable3 = (EnumFacing)blockState.getComparable(autoWalkPath);
      return Blocks.blockPoweredRepeater
         .getDefaultState()
         .resolveIBlockState(autoWalkPath, comparable3)
         .resolveIBlockState(auxiliaryImmutableSetEntry, comparable)
         .resolveIBlockState(internalImmutableSetEntry, comparable2);
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      super.onBlockAdded(world, blockPos, blockState);
      this.applyWorld7(world, blockPos, blockState);
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return Items.repeater;
   }

   @Override
   public int computeCount(IBlockState blockState) {
      return (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) * 2;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, IBlockState blockState) {
      return this.computeCount(iBlockAccess, count, count2, count3, blockState) > 0;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos blockPos, IBlockState blockState, EntityPlayer player, EnumFacing enumFacing, float posX, float posY, float posZ) {
      if (!player.invulnerableManager.state1) {
         return false;
      } else {
         world.matchesBlockPos(blockPos, blockState.resolveIBlockState(auxiliaryImmutableSetEntry), 3);
         return true;
      }
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, auxiliaryImmutableSetEntry, internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      return 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset() | (Integer)blockState.getComparable(auxiliaryImmutableSetEntry) - 1 << 2;
   }

   @Override
   public String getText() {
      return I18n.getString2("item.diode.name");
   }

   @Override
   public IBlockState resolveIBlockStateByIBlockState(IBlockState blockState) {
      Integer comparable = (Integer)blockState.getComparable(auxiliaryImmutableSetEntry);
      Boolean comparable2 = (Boolean)blockState.getComparable(internalImmutableSetEntry);
      EnumFacing comparable3 = (EnumFacing)blockState.getComparable(autoWalkPath);
      return Blocks.blockPoweredRepeater2
         .getDefaultState()
         .resolveIBlockState(autoWalkPath, comparable3)
         .resolveIBlockState(auxiliaryImmutableSetEntry, comparable)
         .resolveIBlockState(internalImmutableSetEntry, comparable2);
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      if (this.enabled) {
         EnumFacing comparable = (EnumFacing)blockState.getComparable(autoWalkPath);
         double offset = (double)((float)blockPos.getX() + 0.5F) + (double)(random.nextFloat() - 0.5F) * 0.2;
         double offset2 = (double)((float)blockPos.getY() + 0.4F) + (double)(random.nextFloat() - 0.5F) * 0.2;
         double offset3 = (double)((float)blockPos.getZ() + 0.5F) + (double)(random.nextFloat() - 0.5F) * 0.2;
         float factor = -5.0F;
         if (random.nextBoolean()) {
            factor = (float)((Integer)blockState.getComparable(auxiliaryImmutableSetEntry) * 2 - 1);
         }

         float factor2 = factor / 16.0F;
         double offset4 = (double)(factor2 * (float)comparable.getInt2());
         double offset5 = (double)(factor2 * (float)comparable.getInt());
         world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.REDSTONE, offset + offset4, offset2, offset3 + offset5, 0.0, 0.0, 0.0, new int[0]);
      }
   }

   public BlockPoweredRepeater(boolean flag) {
      super(flag);
      this.setIBlockState(
         this.blockManager
            .get()
            .resolveIBlockState(autoWalkPath, EnumFacing.NORTH)
            .resolveIBlockState(auxiliaryImmutableSetEntry, 1)
            .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
      );
   }
}
