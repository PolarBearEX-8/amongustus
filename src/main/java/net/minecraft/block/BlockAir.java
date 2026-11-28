package net.minecraft.block;

import java.util.IdentityHashMap;
import java.util.Map;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockAir extends Block {
   private static Map mapOriginalOpacity = new IdentityHashMap();

   @Override
   public boolean canCollideCheck(IBlockState state, boolean flag) {
      return false;
   }

   public static void restoreLightOpacity(Block block) {
      if (mapOriginalOpacity.containsKey(block)) {
         int n = (Integer)mapOriginalOpacity.get(block);
         setLightOpacity(block, n);
      }
   }

   public static void setLightOpacity(Block block, int n) {
      if (!mapOriginalOpacity.containsKey(block)) {
         mapOriginalOpacity.put(block, block.lightOpacity);
      }

      block.lightOpacity = n;
   }

   @Override
   public boolean canCollideCheck(IBlockState state, boolean flag, boolean flag2) {
      return false;
   }

   @Override
   public void dropBlockAsItemWithChance(World world, BlockPos pos, IBlockState state, float f, int n) {
   }

   @Override
   public boolean hasCollisionBox() {
      return false;
   }

   @Override
   public int getRenderType() {
      return -1;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public boolean isReplaceable(World world, BlockPos pos) {
      return true;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World world, IBlockState state, int n, int n2, int n3) {
      return null;
   }

   public BlockAir() {
      super(Material.air);
   }
}
