package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class BlockContainer extends Block implements ITileEntityProvider {
   @Override
   public int getRenderType() {
      return -1;
   }

   @Override
   public void breakBlock(World world, BlockPos pos, IBlockState state) {
      super.breakBlock(world, pos, state);
      world.removeTileEntity(pos);
   }

   @Override
   public boolean onBlockEventReceived(World world, BlockPos pos, IBlockState state, int n, int n2) {
      super.onBlockEventReceived(world, pos, state, n, n2);
      TileEntity tileEntity = world.getTileEntity(pos);
      return tileEntity == null ? false : tileEntity.receiveClientEvent(n, n2);
   }

   public boolean isInvalidNeighbor(World world, BlockPos pos, EnumFacing facing) {
      return world.getBlockState(pos.offset(facing)).getBlock().getMaterial() == Material.cactus;
   }

   public BlockContainer(Material material) {
      this(material, material.getMaterialMapColor());
   }

   public BlockContainer(Material material, MapColor mapColor) {
      super(material, mapColor);
      this.isBlockContainer = true;
   }

   public boolean hasInvalidNeighbor(World world, BlockPos pos) {
      return this.isInvalidNeighbor(world, pos, EnumFacing.NORTH)
         || this.isInvalidNeighbor(world, pos, EnumFacing.SOUTH)
         || this.isInvalidNeighbor(world, pos, EnumFacing.WEST)
         || this.isInvalidNeighbor(world, pos, EnumFacing.EAST);
   }
}
