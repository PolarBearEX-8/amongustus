package net.minecraft.block;

import client.util.IBlockState;
import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockAir extends Block {
   public static Map map = new IdentityHashMap();

   @Override
   public void applyWorld3(World world, BlockPos blockPos, IBlockState blockState, float factor, int count) {
   }

   @Override
   public boolean processAction(IBlockState blockState, boolean flag, boolean flag2) {
      return false;
   }

   @Override
   public boolean processAction6(World world, BlockPos blockPos) {
      return true;
   }

   @Override
   public boolean processAction(IBlockState blockState, boolean flag) {
      return false;
   }

   @Override
   public int processAction13() {
      return -1;
   }

   @Override
   public AxisAlignedBB processAction(World world, IBlockState blockState, int count, int count2, int count3) {
      return null;
   }

   public static void applyBlock(Block block, int count) {
      if (!map.containsKey(block)) {
         map.put(block, block.indexOffset);
      }

      block.indexOffset = count;
   }

   public static void applyBlock(Block block) {
      if (map.containsKey(block)) {
         int count = (Integer)map.get(block);
         applyBlock(block, count);
      }
   }

   @Override
   public boolean processAction15() {
      return false;
   }

   @Override
   public boolean processAction17() {
      return false;
   }

   public BlockAir() {
      super(Material.coreItemEntry);
   }
}
