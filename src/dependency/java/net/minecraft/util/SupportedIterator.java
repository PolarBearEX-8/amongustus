package net.minecraft.util;

import client.util.RuntimeComponentHandler;
import java.util.Iterator;
import net.minecraft.block.ExtendedBlockPosAdapter;

public class SupportedIterator implements Iterator {
   public RuntimeComponentHandler runtimeComponentHandler;
   public ExtendedBlockPosAdapter extendedBlockPosAdapter = new ExtendedBlockPosAdapter(0, 0, 0);
   public int count = 0;
   public int count2;
   public int count3;
   public int count4;
   public static final int count5 = 0;
   public static final int count6 = 1;
   public static final int count7 = 2;

   public SupportedIterator(BlockPos blockPos, BlockPos blockPos2, int count8, int count9) {
      boolean flag = blockPos.getX() > blockPos2.getX();
      boolean flag2 = blockPos.getY() > blockPos2.getY();
      boolean flag3 = blockPos.getZ() > blockPos2.getZ();
      blockPos = this.resolveBlockPos(blockPos, flag, flag2, flag3);
      blockPos2 = this.resolveBlockPos(blockPos2, flag, flag2, flag3);
      this.count2 = flag ? -1 : 1;
      this.count3 = flag2 ? -1 : 1;
      this.count4 = flag3 ? -1 : 1;
      Vec3 vec = new Vec3((double)(blockPos2.getX() - blockPos.getX()), (double)(blockPos2.getY() - blockPos.getY()), (double)(blockPos2.getZ() - blockPos.getZ())).normalize();
      Vec3 vec2 = new Vec3(1.0, 0.0, 0.0);
      double offset = Math.abs(vec.distanceTo(vec2));
      Vec3 vec3 = new Vec3(0.0, 1.0, 0.0);
      double offset2 = Math.abs(vec.distanceTo(vec3));
      Vec3 vec4 = new Vec3(0.0, 0.0, 1.0);
      double offset3 = Math.abs(vec.distanceTo(vec4));
      if (offset3 >= offset2 && offset3 >= offset) {
         this.count = 2;
         BlockPos blockPos3 = new BlockPos(blockPos.getZ(), blockPos.getY() - count8, blockPos.getX() - count9);
         BlockPos blockPos4 = new BlockPos(blockPos2.getZ(), blockPos.getY() + count8 + 1, blockPos.getX() + count9 + 1);
         int count10 = blockPos2.getZ() - blockPos.getZ();
         double offset4 = (double)(blockPos2.getY() - blockPos.getY()) / (1.0 * (double)count10);
         double offset5 = (double)(blockPos2.getX() - blockPos.getX()) / (1.0 * (double)count10);
         this.runtimeComponentHandler = new RuntimeComponentHandler(blockPos3, blockPos4, offset4, offset5);
      } else if (offset2 >= offset && offset2 >= offset3) {
         this.count = 1;
         BlockPos blockPos5 = new BlockPos(blockPos.getY(), blockPos.getX() - count8, blockPos.getZ() - count9);
         BlockPos blockPos6 = new BlockPos(blockPos2.getY(), blockPos.getX() + count8 + 1, blockPos.getZ() + count9 + 1);
         int count11 = blockPos2.getY() - blockPos.getY();
         double offset6 = (double)(blockPos2.getX() - blockPos.getX()) / (1.0 * (double)count11);
         double offset7 = (double)(blockPos2.getZ() - blockPos.getZ()) / (1.0 * (double)count11);
         this.runtimeComponentHandler = new RuntimeComponentHandler(blockPos5, blockPos6, offset6, offset7);
      } else {
         this.count = 0;
         BlockPos blockPos7 = new BlockPos(blockPos.getX(), blockPos.getY() - count8, blockPos.getZ() - count9);
         BlockPos blockPos8 = new BlockPos(blockPos2.getX(), blockPos.getY() + count8 + 1, blockPos.getZ() + count9 + 1);
         int count12 = blockPos2.getX() - blockPos.getX();
         double offset8 = (double)(blockPos2.getY() - blockPos.getY()) / (1.0 * (double)count12);
         double offset9 = (double)(blockPos2.getZ() - blockPos.getZ()) / (1.0 * (double)count12);
         this.runtimeComponentHandler = new RuntimeComponentHandler(blockPos7, blockPos8, offset8, offset9);
      }
   }

   @Override
   public void remove() {
      throw new RuntimeException("Not supported");
   }

   @Override
   public boolean hasNext() {
      return this.runtimeComponentHandler.hasNext();
   }

   @Override
   public Object next() {
      return this.getBlockPos();
   }

   public BlockPos getBlockPos() {
      BlockPos blockPos = this.runtimeComponentHandler.getBlockPos();
      switch (this.count) {
         case 1:
            this.extendedBlockPosAdapter.applyIndex(blockPos.getY() * this.count2, blockPos.getX() * this.count3, blockPos.getZ() * this.count4);
            return this.extendedBlockPosAdapter;
         case 2:
            this.extendedBlockPosAdapter.applyIndex(blockPos.getZ() * this.count2, blockPos.getY() * this.count3, blockPos.getX() * this.count4);
            return this.extendedBlockPosAdapter;
         default:
            this.extendedBlockPosAdapter.applyIndex(blockPos.getX() * this.count2, blockPos.getY() * this.count3, blockPos.getZ() * this.count4);
            return this.extendedBlockPosAdapter;
      }
   }

   public static void main(String[] textArray) {
      BlockPos blockPos = new BlockPos(10, 20, 30);
      BlockPos blockPos2 = new BlockPos(30, 40, 20);
      SupportedIterator supportedIterator = new SupportedIterator(blockPos, blockPos2, 1, 1);

      while (supportedIterator.hasNext()) {
         BlockPos blockPos3 = supportedIterator.getBlockPos();
         System.out.println("" + blockPos3);
      }
   }

   private BlockPos resolveBlockPos(BlockPos blockPos, boolean flag, boolean flag2, boolean flag3) {
      BlockPos blockPos2 = blockPos;
      if (flag) {
         blockPos2 = new BlockPos(-blockPos.getX(), blockPos.getY(), blockPos.getZ());
      }

      if (flag2) {
         blockPos2 = new BlockPos(blockPos2.getX(), -blockPos2.getY(), blockPos2.getZ());
      }

      if (flag3) {
         blockPos2 = new BlockPos(blockPos2.getX(), blockPos2.getY(), -blockPos2.getZ());
      }

      return blockPos2;
   }
}
