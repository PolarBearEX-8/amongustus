package net.minecraft.block;

import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSign extends BlockContainer {
   @Override
   public Item getItem(World world, BlockPos pos) {
      return Items.sign;
   }

   @Override
   public boolean isPassable(IBlockAccess world, BlockPos pos) {
      return true;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   @Override
   public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (world.isRemote) {
         return true;
      } else {
         TileEntity tileEntity = world.getTileEntity(pos);
         return tileEntity instanceof TileEntitySign ? ((TileEntitySign)tileEntity).executeCommand(player) : false;
      }
   }

   public BlockSign() {
      super(Material.wood);
      float f = 0.25F;
      float f2 = 1.0F;
      this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f2, 0.5F + f);
   }

   @Override
   public boolean canSpawnInBlock() {
      return true;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean canPlaceBlockAt(World world, BlockPos pos) {
      return !this.hasInvalidNeighbor(world, pos) && super.canPlaceBlockAt(world, pos);
   }

   @Override
   public boolean canBlockBeActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumFacing facing, float f, float f2, float f3) {
      if (world.isRemote) {
         return true;
      } else {
         return world.getTileEntity(pos) instanceof TileEntitySign;
      }
   }

   @Override
   public TileEntity createNewTileEntity(World world, int n) {
      return new TileEntitySign();
   }

   @Override
   public AxisAlignedBB getSelectedBoundingBox(World world, BlockPos pos) {
      this.setBlockBoundsBasedOnState(world, pos.getX(), pos.getY(), pos.getZ());
      return super.getSelectedBoundingBox(world, pos);
   }

   @Override
   public boolean isPassable(IBlockAccess world, int n, int n2, int n3) {
      return true;
   }

   @Override
   public Item getItemDropped(IBlockState state, Random rand, int n) {
      return Items.sign;
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }
}
