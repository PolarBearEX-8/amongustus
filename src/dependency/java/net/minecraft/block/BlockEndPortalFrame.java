package net.minecraft.block;

import client.setting.MovementFactorProperty;
import client.util.AutoWalkPath;
import client.util.IBlockState;
import client.util.InternalImmutableSetEntry;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockEndPortalFrame extends Block {
   public static final AutoWalkPath autoWalkPath;
   public static final InternalImmutableSetEntry internalImmutableSetEntry;

   @Override
   public void processAction2() {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
   }

   @Override
   public IBlockState processAction(int count) {
      return this.getDefaultState()
         .resolveIBlockState(internalImmutableSetEntry, (count & 4) != 0)
         .resolveIBlockState(autoWalkPath, EnumFacing.resolveEnumFacing(count & 3));
   }

   @Override
   public int processAction5(World world, BlockPos blockPos) {
      return ((Boolean)world.getBlockState(blockPos).getComparable(internalImmutableSetEntry)) ? 15 : 0;
   }

   @Override
   public Item processAction(IBlockState blockState, Random random, int count) {
      return null;
   }

   @Override
   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.8125F, 1.0F);
      super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      if ((Boolean)world.getBlockState(blockPos).getComparable(internalImmutableSetEntry)) {
         this.setBlockBounds(0.3125F, 0.8125F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
         super.applyWorld(world, blockPos, blockState, boundingBox, list, entity);
      }

      this.processAction2();
   }

   @Override
   public IBlockState onBlockPlaced(World world, BlockPos blockPos, EnumFacing enumFacing, float posX, float posY, float posZ, int count, EntityLivingBase livingEntity) {
      return this.getDefaultState()
         .resolveIBlockState(autoWalkPath, livingEntity.getEnumFacing2().getEnumFacing5())
         .resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE);
   }

   @Override
   public BlockManager processAction11() {
      return new BlockManager(this, new IProperty[]{autoWalkPath, internalImmutableSetEntry});
   }

   @Override
   public int getIndex(IBlockState blockState) {
      int count = 0 | ((EnumFacing)blockState.getComparable(autoWalkPath)).getIndexOffset();
      if ((Boolean)blockState.getComparable(internalImmutableSetEntry)) {
         count |= 4;
      }

      return count;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean processAction16() {
      return true;
   }

   public BlockEndPortalFrame() {
      super(Material.material4, MapColor.mapColor25);
      this.setIBlockState(
         this.blockManager.get().resolveIBlockState(autoWalkPath, EnumFacing.NORTH).resolveIBlockState(internalImmutableSetEntry, Boolean.FALSE)
      );
   }

   static {
      String[] textArray = new String[]{"eye", "facing"};
      autoWalkPath = AutoWalkPath.resolveAutoWalkPath(textArray[1], MovementFactorProperty.HORIZONTAL);
      internalImmutableSetEntry = InternalImmutableSetEntry.resolveInternalImmutableSetEntry(textArray[0]);
   }
}
