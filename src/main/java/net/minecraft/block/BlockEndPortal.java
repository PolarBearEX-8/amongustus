package net.minecraft.block;

import java.util.List;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockEndPortal extends BlockContainer {
   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public MapColor getMapColor(IBlockState state) {
      return MapColor.blackColor;
   }

   @Override
   public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB box, List list, Entity entity) {
   }

   public BlockEndPortal(Material material) {
      super(material);
      this.setLightLevel(1.0F);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess world, int n, int n2, int n3) {
      float f = 0.0625F;
      this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, f, 1.0F);
   }

   @Override
   public int quantityDropped(Random rand) {
      return 0;
   }

   @Override
   public Item getItem(World world, BlockPos pos) {
      return null;
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess world, int n, int n2, int n3, EnumFacing facing) {
      return facing == EnumFacing.DOWN && super.shouldSideBeRendered(world, n, n2, n3, facing);
   }

   @Override
   public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
      if (entity.ridingEntity == null && entity.riddenByEntity == null && !world.isRemote) {
         entity.travelToDimension(1);
      }
   }

   @Override
   public void randomDisplayTick(World world, BlockPos pos, IBlockState state, Random rand) {
      double d = (double)((float)pos.getX() + rand.nextFloat());
      double d2 = (double)((float)pos.getY() + 0.8F);
      double d3 = (double)((float)pos.getZ() + rand.nextFloat());
      double d4 = 0.0;
      double d5 = 0.0;
      double d6 = 0.0;
      world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d, d2, d3, d4, d5, d6, new int[0]);
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntityEndPortal();
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }
}
