package net.minecraft.block;

import client.event.ServiceListener;
import client.util.IBlockState;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class AdvancedBlockAdapter extends Block implements ServiceListener {
   public boolean matchesWorld3(World world, BlockPos blockPos) {
      return this.matchesWorld2(world, blockPos, EnumFacing.NORTH)
         || this.matchesWorld2(world, blockPos, EnumFacing.SOUTH)
         || this.matchesWorld2(world, blockPos, EnumFacing.WEST)
         || this.matchesWorld2(world, blockPos, EnumFacing.EAST);
   }

   @Override
   public void onBlockAdded(World world, BlockPos blockPos, IBlockState blockState) {
      super.onBlockAdded(world, blockPos, blockState);
      world.applyBlockPosByBlockPos(blockPos);
   }

   public boolean matchesWorld2(World world, BlockPos blockPos, EnumFacing enumFacing) {
      return world.getBlockState(blockPos.offset(enumFacing)).getBlock().getMaterial() == Material.material21;
   }

   @Override
   public int processAction13() {
      return -1;
   }

   @Override
   public boolean processAction(World world, BlockPos blockPos, IBlockState blockState, int count, int count2) {
      super.processAction(world, blockPos, blockState, count, count2);
      TileEntity tileEntity = world.resolveTileEntity(blockPos);
      return tileEntity == null ? false : tileEntity.checkIndex(count, count2);
   }

   public AdvancedBlockAdapter(Material material) {
      this(material, material.getMapColor());
   }

   public AdvancedBlockAdapter(Material material, MapColor mapColor) {
      super(material, mapColor);
      this.state8 = true;
   }
}
