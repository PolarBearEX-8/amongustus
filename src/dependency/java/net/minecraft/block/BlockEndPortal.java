package net.minecraft.block;

import client.setting.AttackEffectsExplosionNormalType;
import client.util.IBlockState;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityAirportal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEndPortal extends AdvancedBlockAdapter {
   @Override
   public void processAction(IBlockAccess iBlockAccess, int count, int count2, int count3) {
      float factor = 0.0625F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, factor, 1.0F);
   }

   @Override
   public Item processAction4(World world, BlockPos blockPos) {
      return null;
   }

   @Override
   public MapColor processAction3(IBlockState blockState) {
      return MapColor.mapColor29;
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos blockPos, IBlockState blockState, Entity entity) {
      if (entity.ridingEntity == null && entity.entity == null && !world.state) {
         entity.applyIndex2(1);
      }
   }

   @Override
   public void applyWorld(World world, BlockPos blockPos, IBlockState blockState, AxisAlignedBB boundingBox, List list, Entity entity) {
   }

   @Override
   public TileEntity resolveTileEntityByWorld(World world, int count) {
      return new TileEntityAirportal();
   }

   @Override
   public int processAction(Random random) {
      return 0;
   }

   @Override
   public boolean matchesIBlockAccess(IBlockAccess iBlockAccess, int count, int count2, int count3, EnumFacing enumFacing) {
      return enumFacing == EnumFacing.DOWN && super.matchesIBlockAccess(iBlockAccess, count, count2, count3, enumFacing);
   }

   @Override
   public void processAction3(World world, BlockPos blockPos, IBlockState blockState, Random random) {
      double offset = (double)((float)blockPos.getX() + random.nextFloat());
      double offset2 = (double)((float)blockPos.getY() + 0.8F);
      double offset3 = (double)((float)blockPos.getZ() + random.nextFloat());
      double offset4 = 0.0;
      double offset5 = 0.0;
      double offset6 = 0.0;
      world.applyAttackEffectsExplosionNorm(AttackEffectsExplosionNormalType.SMOKE_NORMAL, offset, offset2, offset3, offset4, offset5, offset6, new int[0]);
   }

   @Override
   public boolean processAction14() {
      return false;
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   public BlockEndPortal(Material material) {
      super(material);
      this.processAction(1.0F);
   }
}
